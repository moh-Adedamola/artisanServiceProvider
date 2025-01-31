package com.semicolonafrica.controller;

import com.semicolonafrica.data.model.Artisan;
import com.semicolonafrica.data.model.Category;
import com.semicolonafrica.data.model.User;
import com.semicolonafrica.dtos.request.LoginRequest;
import com.semicolonafrica.dtos.request.RegisterUserRequest;
import com.semicolonafrica.dtos.response.ApiResponse;
import com.semicolonafrica.services.ArtisanService;
import com.semicolonafrica.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/artisan")
public class ArtisanController {
    @Autowired
    private UserService userService;
    @Autowired
    private ArtisanService artisanService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterUserRequest registerUserRequest){
        try {
            return new ResponseEntity<>(new ApiResponse(true, userService.registerUser(registerUserRequest)), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest){
        try {
            return new ResponseEntity<>(new ApiResponse(true, userService.login(loginRequest)), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage()), HttpStatus.UNAUTHORIZED);
        }
    }



//    @GetMapping("/findByCategory/category")
//    public ResponseEntity<?> findArtisanByCategory(@RequestBody FindArtisanByCategory findArtisanByCategory) {
//        try{
//            List<User> artisans = userService.findArtisanByCategory(findArtisanByCategory.getCategory());
//            if (artisans.isEmpty()) {
//                return new ResponseEntity<>(HttpStatus.NO_CONTENT); // No specialists found
//            }
//            return new ResponseEntity<>(new ApiResponse(true, artisans), HttpStatus.OK);
//        } catch (Exception e){
//            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
}
