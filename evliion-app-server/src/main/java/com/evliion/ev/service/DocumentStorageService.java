package com.evliion.ev.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.security.core.context.SecurityContextHolder;

import com.evliion.ev.controller.DocumentController;
import com.evliion.ev.exception.FileStoreageException;
import com.evliion.ev.model.Documents;
import com.evliion.ev.repository.DocumentStorageRepository;
import com.evliion.ev.security.UserPrincipal;
import com.evliion.ev.util.AppConstants;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class DocumentStorageService implements FileStorageService {
	private final Logger logger = LoggerFactory.getLogger(DocumentController.class);
	private static Path fileStorageLocation;
	
	@Autowired
	private DocumentStorageRepository documentStorageRepository;

	@Autowired
	public DocumentStorageService(Documents fileStorageProperties) {
		this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir()).toAbsolutePath().normalize();
		try {
			Files.createDirectories(this.fileStorageLocation);
		} catch (Exception ex) {
			throw new FileStoreageException(AppConstants.FILE_STORAGE_EXCEPTION_PATH_NOT_FOUND, ex);
		}
	}

	@Override
	public String storeFile(MultipartFile file, String countryName, String docType) {
		String originalFileName = StringUtils.cleanPath(file.getOriginalFilename());
		String fileName = "";
		String fileExtension = "";
		Long userId = null;
		String username = null;
		try {
			Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			if (principal instanceof UserPrincipal) {
				userId = ((UserPrincipal) principal).getId();
				username = ((UserPrincipal) principal).getName();
			}
			if (originalFileName.contains("...,,,")) {
				logger.error(AppConstants.INVALID_FILE_PATH_NAME + "UserID: "+userId);
				throw new FileStoreageException(AppConstants.INVALID_FILE_PATH_NAME + originalFileName);
			}

			fileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));

			fileName = userId + "_" + docType + fileExtension;

			Path targetLocation = this.fileStorageLocation.resolve(fileName);

			Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

			Documents doc = documentStorageRepository.checkDocumentByUserId(userId, docType);

			if (doc != null) {
				doc.setDocumentFormat(file.getContentType());
				doc.setFileName(fileName);
				documentStorageRepository.save(doc);
			} else {
				Documents newDocument = new Documents();
				newDocument.setUserId(userId);
				newDocument.setDocumentFormat(file.getContentType());
				newDocument.setFileName(fileName);
				newDocument.setCountryName(countryName);
				newDocument.setFileRef(username);
				newDocument.setDocumentType(docType);
				newDocument.setUploadDir(targetLocation.toString());
				documentStorageRepository.save(newDocument);
			}
			return fileName;
		} catch (IOException ex) {
			throw new FileStoreageException(AppConstants.FILE_NOT_FOUND + fileName + ". Please try again!", ex);
		}
	}

	@Override
	public Resource loadFileAsResource(String fileName) throws Exception {
		try {
			Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
			Resource resource = new UrlResource(filePath.toUri());
			if (resource.exists()) {
				return resource;
			} else {
				throw new FileNotFoundException("File not found " + fileName);
			}
		} catch (MalformedURLException e) {
			throw new FileNotFoundException("File not found " + fileName);
		}
	}

	@Override
	public String getDocumentName(String docType) {
		long userId = getUserId();
		return documentStorageRepository.getUploadDocumnetPath(userId, docType);
	}

	private long getUserId() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		long userId = 0;
		if (principal instanceof UserPrincipal) {
			userId = ((UserPrincipal) principal).getId();
		}
		return userId;
	}
}
