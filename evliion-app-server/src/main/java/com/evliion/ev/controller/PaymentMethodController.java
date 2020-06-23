package com.evliion.ev.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.evliion.ev.model.Card;
import com.evliion.ev.payload.ApiResponse;
import com.evliion.ev.payload.CardRequest;
import com.evliion.ev.payload.CardResponse;
import com.evliion.ev.security.CurrentUser;
import com.evliion.ev.security.UserPrincipal;
import com.evliion.ev.service.CardService;
import com.evliion.ev.util.ModelMapper;

@RestController
@RequestMapping("/api/v1")
public class PaymentMethodController {

	@Autowired
	private CardService cardService;


	private static final Logger logger = LoggerFactory.getLogger(PaymentMethodController.class);


	@PostMapping("/payment-method")
    @PreAuthorize("hasRole('USER')")
	public ResponseEntity<?> createPaymentMethod(@RequestBody CardRequest cardRequest, @CurrentUser UserPrincipal currentUser){
        Card card = cardService.addCard(currentUser, cardRequest);
	
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{paymentMethodId}")
                .buildAndExpand(card.getId()).toUri();

        return ResponseEntity.created(location)
                .body(new ApiResponse(true, "Payment method Created Successfully",card.getId()));
	}

	@GetMapping("/payment-method/{paymentMethodId}")
    @PreAuthorize("hasRole('USER')")
	public CardResponse getPaymentMethodById(@PathVariable Long paymentMethodId){
		return cardService.getCardById(paymentMethodId);			
	}
	
	@GetMapping("/payment-methods/{userId}")
    @PreAuthorize("hasRole('USER')")
	public List<CardResponse> getPaymentMethodsByUserId(@PathVariable Long userId){
		return cardService.getPaymentMethodsByUserId(userId);
	}
	
	@DeleteMapping("/payment-method/{paymentMethodId}")	
    @PreAuthorize("hasRole('USER')")
	public ApiResponse deletePaymentMethod(@PathVariable Long paymentMethodId){
		boolean success = cardService.deletePaymentMethod(paymentMethodId);
        if(!success)
            return new ApiResponse(true, "Card deleted successfully");
        return new ApiResponse(false, "Failed deleting card. Please try after some time");
	}
}
