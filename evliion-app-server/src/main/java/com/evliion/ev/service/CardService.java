package com.evliion.ev.service;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.evliion.ev.exception.ResourceNotFoundException;
import com.evliion.ev.model.Card;
import com.evliion.ev.model.User;
import com.evliion.ev.payload.CardRequest;
import com.evliion.ev.payload.CardResponse;
import com.evliion.ev.repository.CardRepository;
import com.evliion.ev.repository.UserRepository;
import com.evliion.ev.security.UserPrincipal;
import com.evliion.ev.util.ModelMapper;

@Service
public class CardService {

	@Autowired
	private CardRepository cardRepository;

	@Autowired
	private UserRepository userRepository;

	private static final Logger logger = LoggerFactory.getLogger(CardService.class);

	public Card addCard(UserPrincipal currentUser, CardRequest cardRequest) {
		Card card = new Card();
		card.setExpMonth(cardRequest.getExpMonth());
		card.setExpYear(cardRequest.getExpYear());
		card.setName(cardRequest.getName());
		card.setNumber(cardRequest.getNumber());

		User user = userRepository.findById(currentUser.getId())
				.orElseThrow(() -> new ResourceNotFoundException("User", "userId", currentUser.getId()));

		card.setUser(user);
		return cardRepository.save(card);
	}

	public CardResponse getCardById(Long cardId) {
		Card card = cardRepository.findById(cardId).orElseThrow(
				()-> new ResourceNotFoundException("Card", "id", cardId));
		 
		return ModelMapper.mapCardToCardResponse(card);		
	}

	public List<CardResponse> getPaymentMethodsByUserId(Long userId) {
		
        List<Card> cards = cardRepository.findByUserId(userId);
		
		return cards.stream()
				.map(card -> ModelMapper.mapCardToCardResponse(card))
				.collect(Collectors.toList());
				
	}
	
	public boolean deletePaymentMethod(Long cardId) {
		Card card = cardRepository.findById(cardId).orElseThrow(
				()-> new ResourceNotFoundException("Card", "id", cardId));
		cardRepository.delete(card);
		return cardRepository.existsById(cardId);		
	}

}
