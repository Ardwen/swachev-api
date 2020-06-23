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

import com.evliion.ev.model.Station;
import com.evliion.ev.payload.ApiResponse;
import com.evliion.ev.payload.PagedResponse;
import com.evliion.ev.payload.PollResponse;
import com.evliion.ev.payload.StationRequest;
import com.evliion.ev.payload.StoreResponse;
import com.evliion.ev.security.CurrentUser;
import com.evliion.ev.security.UserPrincipal;
import com.evliion.ev.service.StationService;
import com.evliion.ev.util.AppConstants;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/api/v1/store")
@Api(value="Store Management System", description="Operations pertaining to store in Store Management System")
public class StationController {
	
	@Autowired
    private StationService stationService;
	
	/*
	 {
 "name":"Nageshwar Station",
 "address":{
 	"line1":"Near Dehu road",
    "line2":"Opp Priyadarshani",
    "zipCode":1,
    "city":"Pune",
    "state":"Delhi",
    "country":"India"
 	},
 "stationType":"Charging",
 "swapType":"ONSTORE",
 "lattitude":1311313131313,
 "longitude":1421414124141
}
	 */
	@ApiOperation(value = "Add a store")
	@PostMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> addStation(@ApiParam(value = "Details of store to be added", required = true) @Valid @RequestBody StationRequest stationRequest, @CurrentUser UserPrincipal currentUser) {
    	Station station = stationService.addStation(currentUser, stationRequest);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{stationId}")
                .buildAndExpand(station.getId()).toUri();
 
        return ResponseEntity.created(location)
                .body(new ApiResponse(true, "Store added successfully"));
    }
    
	@ApiOperation(value = "Get a store by Id")
    @GetMapping("/{stationId}")
    public Station getStationById(@CurrentUser UserPrincipal currentUser,
    		@ApiParam(value = "Store id from which store object will retrieve", required = true) @PathVariable Long stationId) {
        return stationService.getStationById(stationId, currentUser);
    }
    
	@ApiOperation(value = "Delete an store")
    @DeleteMapping("/{stationId}")	
    @PreAuthorize("hasRole('USER')")
	public ApiResponse deactivateStore(@ApiParam(value = "Store Id from which store object will delete from database table", required = true) @PathVariable Long stationId){
		boolean success = stationService.deactivateStore(stationId);
        if(!success)
            return new ApiResponse(true, "Store deleted successfully");
        return new ApiResponse(false, "Failed deleting Store. Please try after some time");
	}
    
	@ApiOperation(value = "View a list of available stores", response = List.class)
	@GetMapping
	public List<Station> getAllStores(@RequestParam double geoLng, 
			@RequestParam double geoLat, @RequestParam int radius){
		return stationService.getAllStores(geoLat, geoLng, radius);
	}
	/*
	public List<Station> getAllStoresWithoutGeoLocation(){
		return stationService.getAllStoresWithoutGeoLocation();
	}
	*/
	@ApiOperation(value = "View a list of available stores", response = List.class)
	@GetMapping("/all")
    public PagedResponse<StoreResponse> getAllStoresWithoutGeoLocation(@CurrentUser UserPrincipal currentUser,
                                                @RequestParam(value = "page", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int page,
                                                @RequestParam(value = "size", defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int size) {
        return stationService.getAllStoresWithoutGeoLocation(page, size);
    }
}