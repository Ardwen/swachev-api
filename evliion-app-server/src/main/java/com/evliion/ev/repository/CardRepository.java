package com.evliion.ev.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.evliion.ev.model.Card;
import com.evliion.ev.model.Vote;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {
	
    Optional<Card> findById(Long cardId);
    
    List<Card> findByUserId(Long userId);
        
}
