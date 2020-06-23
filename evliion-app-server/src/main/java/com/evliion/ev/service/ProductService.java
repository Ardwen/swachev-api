package com.evliion.ev.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.evliion.ev.exception.ResourceNotFoundException;
import com.evliion.ev.model.Address;
import com.evliion.ev.model.Choice;
import com.evliion.ev.model.CustomAttribute;
import com.evliion.ev.model.Product;
import com.evliion.ev.model.Station;
import com.evliion.ev.payload.AddProductRequest;
import com.evliion.ev.payload.AddressRequest;
import com.evliion.ev.payload.StationRequest;
import com.evliion.ev.repository.AddressRepository;
import com.evliion.ev.repository.ProductRepository;
import com.evliion.ev.repository.StationRepository;
import com.evliion.ev.security.UserPrincipal;

@Service
public class ProductService {
	
	@Autowired
    private StationRepository stationRepository;

    @Autowired
    private ProductRepository productRepository;
    
    public Product addProduct(UserPrincipal currentUser, AddProductRequest addProductRequest) {
        Long storeId = addProductRequest.getStoreId();
    	Station station = stationRepository.findById(storeId).orElseThrow(
                () -> new ResourceNotFoundException("Station", "id", storeId));
        
    	Product product = new Product();
    	product.setStation(station);
    	product.setCategory(addProductRequest.getCategory());
    	product.setCurrency(addProductRequest.getCurrency());
    	product.setName(addProductRequest.getName());
    	product.setQuantity(addProductRequest.getQuantity());
    	product.setRate(addProductRequest.getRate());
    	product.setUnit(addProductRequest.getUnit());
    	product.setDescription(addProductRequest.getDescription());
    	
    	addProductRequest.getCustomAttributes().forEach(customAttribute -> {
    		CustomAttribute attribute = new CustomAttribute();
    		attribute.setName(customAttribute.getName());
    		attribute.setValue(customAttribute.getValue());
    		product.addCustomAttribute(attribute);
         });
        return productRepository.save(product);
    }
    
    public Product getProductById(Long productId, UserPrincipal currentUser) {
    	Product product = productRepository.findById(productId).orElseThrow(
                () -> new ResourceNotFoundException("Product", "id", productId));
        return product;
    }
    
    public boolean deleteProduct(Long productId) {
    	Product product = productRepository.findById(productId).orElseThrow(
				()-> new ResourceNotFoundException("Product", "id", productId));
    	productRepository.delete(product);
		return productRepository.existsById(productId);		
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
    
    public List<Product> getAllInventoryByStoreId(Long storeId){
		List<Product> inventoryList = productRepository.findByStationId(storeId);
		return inventoryList;
		/*
		return storeList.stream()
				.map(store -> ModelMapper.mapChargePointToChargePointResponse(store))
				.collect(Collectors.toList());
				*/
	}
}
