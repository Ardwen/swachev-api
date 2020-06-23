package com.evliion.ev.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import com.evliion.ev.model.Documents;

@Repository
public interface  DocumentStorageRepository extends JpaRepository<Documents, Long> {
	// @Query("SELECT v FROM Vote v where v.user.id = :userId and v.poll.id in :pollIds")
	@Query("SELECT a from Documents a where user_id = ?1 and document_type = ?2")
	 public Documents checkDocumentByUserId(Long userId, String docType);
	 
	 @Query("Select fileName from Documents a where user_id = ?1 and document_type = ?2")
	 public String getUploadDocumnetPath(Long userId, String docType);
}
