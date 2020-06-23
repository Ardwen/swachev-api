package com.evliion.ev.util;


import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.evliion.ev.model.Card;
import com.evliion.ev.model.ChargePoint;
import com.evliion.ev.model.Poll;
import com.evliion.ev.model.Station;
import com.evliion.ev.model.User;
import com.evliion.ev.model.Vehicle;
import com.evliion.ev.payload.AddProductRequest;
import com.evliion.ev.payload.Address;
import com.evliion.ev.payload.AddressRequest;
import com.evliion.ev.payload.CardResponse;
import com.evliion.ev.payload.ChargePointResponse;
import com.evliion.ev.payload.ChoiceResponse;
import com.evliion.ev.payload.CustomAttribute;
import com.evliion.ev.payload.PollResponse;
import com.evliion.ev.payload.StoreResponse;
import com.evliion.ev.payload.UserSummary;
import com.evliion.ev.payload.VehicleResponse;
import com.evliion.ev.payload.response.ProductResponse;

public class ModelMapper {

	public static PollResponse mapPollToPollResponse(Poll poll, Map<Long, Long> choiceVotesMap, User creator, Long userVote) {
		PollResponse pollResponse = new PollResponse();
		pollResponse.setId(poll.getId());
		pollResponse.setQuestion(poll.getQuestion());
		pollResponse.setCreationDateTime(poll.getCreatedAt());
		pollResponse.setExpirationDateTime(poll.getExpirationDateTime());
		Instant now = Instant.now();
		pollResponse.setExpired(poll.getExpirationDateTime().isBefore(now));

		List<ChoiceResponse> choiceResponses = poll.getChoices().stream().map(choice -> {
			ChoiceResponse choiceResponse = new ChoiceResponse();
			choiceResponse.setId(choice.getId());
			choiceResponse.setText(choice.getText());

			if (choiceVotesMap.containsKey(choice.getId())) {
				choiceResponse.setVoteCount(choiceVotesMap.get(choice.getId()));
			} else {
				choiceResponse.setVoteCount(0);
			}
			return choiceResponse;
		}).collect(Collectors.toList());

		pollResponse.setChoices(choiceResponses);
		UserSummary creatorSummary = new UserSummary(creator.getId(), creator.getUsername(), creator.getName());
		pollResponse.setCreatedBy(creatorSummary);

		if (userVote != null) {
			pollResponse.setSelectedChoice(userVote);
		}

		long totalVotes = pollResponse.getChoices().stream().mapToLong(ChoiceResponse::getVoteCount).sum();
		pollResponse.setTotalVotes(totalVotes);

		return pollResponse;
	}

	public static ChargePointResponse mapChargePointToChargePointResponse(ChargePoint chargePoint) {
		ChargePointResponse chargePointResponse = new ChargePointResponse();

		chargePointResponse.setStationId(chargePoint.getStation().getId());
		chargePointResponse.setChargePointId(chargePoint.getId());
		chargePointResponse.setChargePointIdentifier(chargePoint.getChargePointIdentifier());
		chargePointResponse.setCapacity(chargePoint.getCapacity());
		chargePointResponse.setType(chargePoint.getType().toString());
		chargePointResponse.setStatus(chargePoint.getStatus().toString());

		return chargePointResponse;
	}

	public static CardResponse mapCardToCardResponse(Card card) {
		CardResponse cardResponse = new CardResponse();
		cardResponse.setPaymentMethodId(card.getId());
		cardResponse.setCardNumber(card.getNumber());
		cardResponse.setNameOnCard(card.getName());
		cardResponse.setUserId(card.getUser().getId());
		cardResponse.setExpiryYear(card.getExpYear());
		cardResponse.setExpiryMonth(AppConstants.SHORT_MONTHS[card.getExpMonth()-1]);

		return cardResponse;
	}
	
	public static VehicleResponse mapVehicleToVehicleResponse(Vehicle vehicle, User creator) {
    	VehicleResponse vehicleResponse = new VehicleResponse();
    	vehicleResponse.setId(vehicle.getId());
    	vehicleResponse.setMake(vehicle.getMake());
    	vehicleResponse.setModel(vehicle.getModel());
    	vehicleResponse.setVehicleType(vehicle.getVehicleType());
    	vehicleResponse.setBatteryCapacity(vehicle.getBatteryCapacity());
    	UserSummary creatorSummary = new UserSummary(creator.getId(), creator.getUsername(), creator.getName());
    	//vehicleResponse.setCreatedBy(creatorSummary);
    	return vehicleResponse;
    }
	
	public static StoreResponse mapStoreToStoreResponse(Station store) {
		StoreResponse storeResponse = new StoreResponse();
		storeResponse.setId(store.getId());
		storeResponse.setCategory(store.getCategory());
		storeResponse.setName(store.getName());
		storeResponse.setSubCategory(store.getSubCategory());
		
		List<ProductResponse> productResponses = store.getProducts().stream().map(product -> {
			ProductResponse productResponse = new ProductResponse();
			productResponse.setCategory(product.getCategory());
			productResponse.setCurrency(product.getCurrency());
			productResponse.setName(product.getName());
			productResponse.setRate(product.getRate());
			productResponse.setUnit(product.getUnit());
			productResponse.setDescription(product.getDescription());
			List<CustomAttribute> customAttributes = product.getAttributes().stream().map(attribute -> {
				CustomAttribute customAttribute = new CustomAttribute();
				customAttribute.setName(attribute.getName());
				customAttribute.setValue(attribute.getValue());
				return customAttribute;
			}).collect(Collectors.toList());
			productResponse.setAttributes(customAttributes);
			
			return productResponse;
		}).collect(Collectors.toList());

		storeResponse.setProducts(productResponses);
		AddressRequest address = new AddressRequest();
		com.evliion.ev.model.Address add = store.getAddress();
		address.setCity(add.getCity());
		address.setCountry(add.getCountry());
		address.setLattitude(add.getLattitude());
		address.setLine1(add.getLine1());
		address.setLine2(add.getLine2());
		address.setLongitude(add.getLongitude());
		address.setState(add.getState());
		address.setZipCode(add.getZipCode());
		storeResponse.setAddress(address);
		
		return storeResponse;
	}
}