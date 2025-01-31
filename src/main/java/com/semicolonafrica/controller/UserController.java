package com.semicolonafrica.controller;

import com.semicolonafrica.data.model.User;
import com.semicolonafrica.dtos.request.*;
import com.semicolonafrica.dtos.response.*;
import com.semicolonafrica.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/registerUser")
    public ResponseEntity<?> registerUser(@RequestBody RegisterUserRequest registerUserRequest) {
        try{
            RegisterUserResponse registerUserResponse = userService.registerUser(registerUserRequest);
            return new ResponseEntity<>(new ApiResponse(true,registerUserResponse), HttpStatus.CREATED);
        } catch (Exception exception) {
            return new ResponseEntity<>(new ApiResponse(false,exception.getMessage()), HttpStatus.BAD_REQUEST);

        }
    }

    @PostMapping("/loginUser")
    public ResponseEntity<?> loginUser(@RequestBody LoginRequest loginUserRequest) {
        try{
            LoginResponse loginUserResponse = userService.login(loginUserRequest);
            return new ResponseEntity<>(new ApiResponse(true,loginUserResponse), HttpStatus.CREATED);
        } catch (Exception exception) {
            return new ResponseEntity<>(new ApiResponse(false,exception.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/findAllUsers")
    public ResponseEntity<?> findAllUsers() {
        try {
            return new ResponseEntity<>(new ApiResponse(true, userService.findAllUsers()), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/findAllArtisans")
    public ResponseEntity<?> getAllArtisans() {
        try {
            return new ResponseEntity<>(new ApiResponse(true, userService.getAllArtisans()), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/logoutUser")
    public ResponseEntity<?> logoutUser(@RequestBody LogoutRequest logoutRequest) {
        try{
            LogoutResponse logoutResponse = userService.logout(logoutRequest);
            return new ResponseEntity<>(new ApiResponse(true,logoutResponse), HttpStatus.CREATED);
        } catch (Exception exception) {
            return new ResponseEntity<>(new ApiResponse(false,exception.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/updateUser/{id}")
    public ResponseEntity<?> updateUser(@PathVariable("id") Long userId, @RequestBody UpdateUserProfileRequest updateUserRequest){
        try{
            return new ResponseEntity<>(new ApiResponse(true, userService.updateUserProfile(userId, updateUserRequest)), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping("/findArtisanByCategory")
     public ResponseEntity<?> findArtisansByCategory(@RequestBody ArtisanByCategoryRequest artisanByCategoryRequest){
        try {
            List<ArtisanResponse> artisans = userService.findArtisanByCategory(artisanByCategoryRequest);
            return new ResponseEntity<>(new ApiResponse(true, artisans), HttpStatus.CREATED);
        } catch (Exception exception) {
            return new ResponseEntity<>(new ApiResponse(false, exception.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/findUsersByRole")
    public ResponseEntity<?> findUsersByRole(@RequestBody UserRoleRequest userRoleRequest){
        try {
            List<UserRoleResponse> users = userService.findUsersByRole(userRoleRequest);
            return new ResponseEntity<>(new ApiResponse(true, users), HttpStatus.CREATED);
        } catch (Exception exception) {
            return new ResponseEntity<>(new ApiResponse(false, exception.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/findUserById/{id}")
    public ResponseEntity<?> findUserById(@PathVariable("id") Long id){
        try {
            UsersResponse userResponse = userService.findUserById(id);
            return new ResponseEntity<>(new ApiResponse(true, userResponse), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

//    @DeleteMapping("/deleteById/{id}")
//    public ResponseEntity<?> deleteUser(@PathVariable Long id){
////        try {
////            userService.deleteUser(id);
////            return new ResponseEntity<>(new ApiResponse(true,), HttpStatus.OK);
////        } catch (Exception e) {
////            return new ResponseEntity<>(new ApiResponse(false, e.getMessage()), HttpStatus.BAD_REQUEST);
////        }
//    }
@DeleteMapping("/deleteUser/{id}")
public ResponseEntity<?> deleteUser(@PathVariable Long id) {
    try {
        return new ResponseEntity<>(new ApiResponse(true, userService.deleteUser(id)), HttpStatus.OK);
    } catch (Exception e) {
        return new ResponseEntity<>(new ApiResponse(false, e.getMessage()), HttpStatus.BAD_REQUEST);
    }
}


}
