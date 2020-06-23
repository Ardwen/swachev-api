package com.evliion.ev.controller;

import com.evliion.ev.exception.ResourceNotFoundException;
import com.evliion.ev.model.User;
import com.evliion.ev.model.Vehicle;
import com.evliion.ev.payload.*;
import com.evliion.ev.repository.PollRepository;
import com.evliion.ev.repository.UserRepository;
import com.evliion.ev.repository.VoteRepository;
import com.evliion.ev.security.UserPrincipal;
import com.evliion.ev.service.PollService;
import com.evliion.ev.security.CurrentUser;
import com.evliion.ev.util.AppConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PollRepository pollRepository;

    @Autowired
    private VoteRepository voteRepository;

    @Autowired
    private PollService pollService;


    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @GetMapping("/user/me")
    @PreAuthorize("hasRole('USER')")
    public UserSummary getCurrentUser(@CurrentUser UserPrincipal currentUser) {
        //UserSummary userSummary = new UserSummary(currentUser.getId(), currentUser.getUsername(), currentUser.getName());
    	UserSummary userSummary = new UserSummary(currentUser.getId(), currentUser.getUsername(), currentUser.getName(), currentUser.getAuthorities());
        return userSummary;
    }

    @GetMapping("/user/checkUsernameAvailability")
    public UserIdentityAvailability checkUsernameAvailability(@RequestParam(value = "username") String username) {
        Boolean isAvailable = !userRepository.existsByUsername(username);
        return new UserIdentityAvailability(isAvailable);
    }

    @GetMapping("/user/checkEmailAvailability")
    public UserIdentityAvailability checkEmailAvailability(@RequestParam(value = "email") String email) {
        Boolean isAvailable = !userRepository.existsByEmail(email);
        return new UserIdentityAvailability(isAvailable);
    }

    @GetMapping("/users/{username}")
    public UserProfile getUserProfile(@PathVariable(value = "username") String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User", "username", username));

        long pollCount = pollRepository.countByCreatedBy(user.getId());
        long voteCount = voteRepository.countByUserId(user.getId());

        UserProfile userProfile = new UserProfile(user.getId(), user.getUsername(), user.getName(), user.getCreatedAt(), pollCount, voteCount);

        return userProfile;
    }

    @GetMapping("/users/{username}/polls")
    public PagedResponse<PollResponse> getPollsCreatedBy(@PathVariable(value = "username") String username,
                                                         @CurrentUser UserPrincipal currentUser,
                                                         @RequestParam(value = "page", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int page,
                                                         @RequestParam(value = "size", defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int size) {
        return pollService.getPollsCreatedBy(username, currentUser, page, size);
    }


    @GetMapping("/users/{username}/votes")
    public PagedResponse<PollResponse> getPollsVotedBy(@PathVariable(value = "username") String username,
                                                       @CurrentUser UserPrincipal currentUser,
                                                       @RequestParam(value = "page", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int page,
                                                       @RequestParam(value = "size", defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int size) {
        return pollService.getPollsVotedBy(username, currentUser, page, size);
    }

    @PostMapping("/vehicle/")
    public ApiResponse postVehicle(@RequestBody VehicleRequest request) {
        boolean success = pollService.createVehicle(request);
        if(success)
            return new ApiResponse(true, "Vehicle added successfully");
        return new ApiResponse(false, "Failed adding vehicle. Please try after some time");
    }

    @PostMapping("/vehicle/{vehicle_id}")
    public VehicleResponse getVehicle(@PathVariable(value = "vehicle_id") Long vehicle_id) {
        VehicleResponse response = new VehicleResponse();

        try {
            Vehicle vehicle = pollService.getVehicle(vehicle_id);
            response.setId(vehicle.getId());
            response.setMake(vehicle.getMake());
            response.setModel(vehicle.getModel());
            response.setVehicleType(vehicle.getVehicleType());
            response.setId(vehicle_id);
        }catch(Exception e){
            response.setSuccess(false);
        }
        return response;

    }
    
/*    
    @PostMapping("/vehicles/{user_id}")
    public UserVehicleResponse getVehicles(@PathVariable(value = "user_id") Long user_id) {
        UserVehicleResponse response = new UserVehicleResponse();
        try {
            List<Vehicle> vehicles = pollService.getVehiclesByUser(user_id);
            response.setUser_id(user_id);
            List<VehicleRequest> vehiclesRequests =new ArrayList<>();
            for(Vehicle vehicle:vehicles){
                VehicleRequest res = new VehicleRequest();
                res.setId(vehicle.getId());
                res.setMake(vehicle.getMake());
                res.setModel(vehicle.getModel());
                res.setModel_type(vehicle.getVehicle_type());
                res.setId(vehicle.getId());
                vehiclesRequests.add(res);
            }
            response.setVehicles(vehiclesRequests);
        }catch (Exception e){
            response.setSuccess(false);
        }

        return response;
    }
*/
}
