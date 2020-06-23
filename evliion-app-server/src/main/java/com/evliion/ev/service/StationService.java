package com.evliion.ev.service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.evliion.ev.exception.BadRequestException;
import com.evliion.ev.exception.ResourceNotFoundException;
import com.evliion.ev.model.Address;
import com.evliion.ev.model.BatterySwappingType;
import com.evliion.ev.model.ChargePoint;
import com.evliion.ev.model.ChargingStationType;
import com.evliion.ev.model.Poll;
import com.evliion.ev.model.Station;
import com.evliion.ev.model.User;
import com.evliion.ev.model.Vehicle;
import com.evliion.ev.payload.AddressRequest;
import com.evliion.ev.payload.ChargePointResponse;
import com.evliion.ev.payload.PagedResponse;
import com.evliion.ev.payload.PollResponse;
import com.evliion.ev.payload.StationRequest;
import com.evliion.ev.payload.StoreResponse;
import com.evliion.ev.repository.AddressRepository;
import com.evliion.ev.repository.StationRepository;
import com.evliion.ev.security.UserPrincipal;
import com.evliion.ev.util.AppConstants;
import com.evliion.ev.util.ModelMapper;

@Service
public class StationService {
	
    @Autowired
    private StationRepository stationRepository;

    @Autowired
    private AddressRepository addressRepository;
    
    public Station addStation(UserPrincipal currentUser, StationRequest stationRequest) {
    	AddressRequest addressRequest = stationRequest.getAddress();
    	Address address = new Address();
    	address.setCity(addressRequest.getCity());
    	address.setCountry(addressRequest.getCountry());
    	address.setLine1(addressRequest.getLine1());
    	address.setLine2(addressRequest.getLine2());
    	address.setState(addressRequest.getState());
    	address.setZipCode(addressRequest.getZipCode());
    	address.setLattitude(stationRequest.getAddress().getLattitude());
    	address.setLongitude(stationRequest.getAddress().getLongitude());
    	addressRepository.save(address);
    	
    	Station station = new Station();
    	station.setName(stationRequest.getName());
    	
    	//TODO - Uncomment it
    	//station.setStationType(ChargingStationType.valueOf(stationRequest.getStationType().toUpperCase()));
    	//station.setSwapType(BatterySwappingType.valueOf(stationRequest.getSwapType().toUpperCase()));
    	
    	station.setCategory(stationRequest.getCategory().toUpperCase());
    	station.setSubCategory(stationRequest.getSubCategory().toUpperCase());
    	
    	station.setAddress(address);
    	
    	station.setActive(true);
        return stationRepository.save(station);
    }
    
    public Station getStationById(Long stationId, UserPrincipal currentUser) {
    	Station station = stationRepository.findById(stationId).orElseThrow(
                () -> new ResourceNotFoundException("Station", "id", stationId));
        return station;
    }
    
    public boolean deactivateStore(Long stationId) {
    	Station station = stationRepository.findById(stationId).orElseThrow(
				()-> new ResourceNotFoundException("Station", "id", stationId));
    	stationRepository.deactivateStore(station.getId());
		return stationRepository.existsById(stationId);		
	}
    
    public Station getAlStationById(Long stationId, UserPrincipal currentUser) {
    	Station station = stationRepository.findById(stationId).orElseThrow(
                () -> new ResourceNotFoundException("Station", "id", stationId));
        return station;
    }
    
    public List<Station> getAllStores(double geoLat, double geoLng, int radius ){
		List<Station> storeList = stationRepository.findByGeoLocation(geoLat, geoLng, radius);
		return storeList;
		/*
		return storeList.stream()
				.map(store -> ModelMapper.mapChargePointToChargePointResponse(store))
				.collect(Collectors.toList());
				*/
	}
    /*
    public List<Station> (){
		List<Station> storeList = stationRepository.findAll();
		return storeList;
		
		return storeList.stream()
				.map(store -> ModelMapper.mapChargePointToChargePointResponse(store))
				.collect(Collectors.toList());
				
	}
    */
    private void validatePageNumberAndSize(int page, int size) {
        if(page < 0) {
            throw new BadRequestException("Page number cannot be less than zero.");
        }

        if(size > AppConstants.MAX_PAGE_SIZE) {
            throw new BadRequestException("Page size must not be greater than " + AppConstants.MAX_PAGE_SIZE);
        }
    }
    
    public PagedResponse<StoreResponse> getAllStoresWithoutGeoLocation(int page, int size) {
        validatePageNumberAndSize(page, size);

        // Retrieve Polls
        Pageable pageable = PageRequest.of(page, size, Sort.Direction.DESC, "createdAt");
        Page<Station> stores = stationRepository.findAll(pageable);

        if(stores.getNumberOfElements() == 0) {
            return new PagedResponse<>(Collections.emptyList(), stores.getNumber(),
            		stores.getSize(), stores.getTotalElements(), stores.getTotalPages(), stores.isLast());
        }

        List<StoreResponse> storeResponses = stores.map(poll -> {
            return ModelMapper.mapStoreToStoreResponse(poll);                               
        }).getContent();

        return new PagedResponse<>(storeResponses, stores.getNumber(),
        		stores.getSize(), stores.getTotalElements(), stores.getTotalPages(), stores.isLast());
    }

}