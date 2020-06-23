package com.evliion.ev.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.evliion.ev.model.Product;
import com.evliion.ev.model.Station;
import com.evliion.ev.payload.AddProductRequest;
import com.evliion.ev.payload.ApiResponse;
import com.evliion.ev.security.CurrentUser;
import com.evliion.ev.security.UserPrincipal;
import com.evliion.ev.service.ProductService;

@RestController
@RequestMapping("/api/v1/product")
public class ProductController {
	
	@Autowired
    private ProductService productService;

	
	@PostMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> addProduct(@Valid @RequestBody AddProductRequest addProductRequest, @CurrentUser UserPrincipal currentUser) {
		Product product = productService.addProduct(currentUser, addProductRequest);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{productId}")
                .buildAndExpand(product.getId()).toUri();
 
        return ResponseEntity.created(location)
                .body(new ApiResponse(true, "Product added successfully"));
    }
    
    @GetMapping("/{productId}")
    public Product getInventoryById(@CurrentUser UserPrincipal currentUser,
                                    @PathVariable Long productId) {
        return productService.getProductById(productId, currentUser);
    }
    
    @DeleteMapping("/{productId}")	
    @PreAuthorize("hasRole('USER')")
	public ApiResponse deleteStation(@PathVariable Long productId){
		boolean success = productService.deleteProduct(productId);
        if(!success)
            return new ApiResponse(true, "Product deleted successfully");
        return new ApiResponse(false, "Failed deleting Product. Please try after some time");
	}
    
    @GetMapping
	public List<Station> getAllStores(@RequestParam double geoLng, 
			@RequestParam double geoLat, @RequestParam int radius){
		return productService.getAllStores(geoLat, geoLng, radius);
	}
}

