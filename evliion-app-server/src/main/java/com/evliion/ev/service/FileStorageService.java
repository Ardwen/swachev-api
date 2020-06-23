package com.evliion.ev.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface FileStorageService {
	public String storeFile(MultipartFile file, String countryName, String docType);
	public Resource loadFileAsResource(String fileName) throws Exception;
	public String getDocumentName(String docType);
}
