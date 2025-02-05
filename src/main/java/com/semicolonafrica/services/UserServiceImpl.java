package com.semicolonafrica.services;

import com.semicolonafrica.data.model.*;
import com.semicolonafrica.data.repository.ArtisanRepository;
import com.semicolonafrica.data.repository.UserRepository;
import com.semicolonafrica.dtos.request.*;
import com.semicolonafrica.dtos.response.*;
import com.semicolonafrica.exception.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service

public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ArtisanService artisanService;

    @Autowired
    private ArtisanRepository artisanRepository;

    @Autowired
    private EmailServiceImpl emailServiceImpl;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public RegisterUserResponse registerUser(RegisterUserRequest request) {
        String email = request.getEmail().trim().toLowerCase();

        validateUserEmail(email);
        validateRegistration(request);
        validateUser(request);

        userRepository.findByEmail(request.getEmail()).ifPresent(user -> {
            throw new IllegalArgumentException("Email already in use.");
        });

        User user = switch (request.getRole()) {
            case CLIENT -> new Client();
            case ARTISAN -> new Artisan();
            default -> throw new IllegalArgumentException("Invalid role selected.");
        };

        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(email);
        user.setPhoneNumber(request.getPhoneNumber());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(request.getRole());
        user.setAddress(request.getAddress());
        user.setLocation(request.getLocation());

        if (user instanceof Artisan artisan) {
            artisan.setCategory(request.getCategory());
            artisan.setDescription(request.getDescription());
        }

        userRepository.save(user);
        RegisterUserResponse registerUserResponse = new RegisterUserResponse();
        registerUserResponse.setMessage("User registered successfully");
        registerUserResponse.setFirstName(request.getFirstName());
        registerUserResponse.setLastName(request.getLastName());
        registerUserResponse.setRole(request.getRole());

//        emailServiceImpl.sendEmail(user.getEmail(), user.getFirstName());


        return registerUserResponse;


    }

    private void validateUserEmail(String email) {
        for (User user : userRepository.findAll()) {
            if (user.getEmail().equals(email.toLowerCase())) {
                throw new UserAlreadyExistException("email already exist");
            }
        }

    }

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        String email = loginRequest.getEmail().trim().toLowerCase();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("User not found with email: " + email));

        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("Invalid password");
        }
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setMessage("Login Successful");
        loginResponse.setFirstName(user.getFirstName());
        loginResponse.setLastName(user.getLastName());
        loginResponse.setRole(user.getRole());
        return loginResponse;
    }

    @Override
    public LogoutResponse logout(LogoutRequest logoutRequest) {
        String email = logoutRequest.getEmail().trim().toLowerCase();
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User with Email: " + email + " not found"));

        if (!user.isLoggedIn()) {
            throw new UserNotLoggedInException("User is not currently logged in");
        }

        user.setLoggedIn(false);
        userRepository.save(user);

        LogoutResponse logoutResponse = new LogoutResponse();
        logoutResponse.setMessage("Logout Successful");
        return logoutResponse;

    }



    @Override
    public List<UsersResponse> findAllUsers() {
        List<User> users = userRepository.findAll();

        return users.stream().map(user -> {
            UsersResponse response = new UsersResponse();
            response.setFirstName(user.getFirstName());
            response.setLastName(user.getLastName());
            response.setEmail(user.getEmail());
            response.setRole(user.getRole());
            return response;
        }).collect(Collectors.toList());
    }

    @Override
    public UsersResponse findUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User with ID " + id + " not found"));

        UsersResponse response = new UsersResponse();
        response.setFirstName(user.getFirstName());
        response.setLastName(user.getLastName());
        response.setEmail(user.getEmail());
        response.setRole(user.getRole());

        return response;
    }

    @Override
    public DeleteUserResponse deleteUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User with ID " + id + " not found"));

        userRepository.delete(user);


        return null;
    }

    @Override
    public DeleteUserResponse deleteById(Long id) {
        userRepository.findById(id)
                .ifPresentOrElse(
                        user -> userRepository.delete(user),
                        () -> {
                            throw new UserNotFoundException("User not found");
                        }
                );
            return new DeleteUserResponse();
    }


    @Override
    public List<ArtisanResponse> getAllArtisans() {
        List<User> users = userRepository.findAll();

        return users.stream()
                .filter(user -> user.getRole() == Role.ARTISAN)
                .map(user -> {
                    Artisan artisan = (Artisan) user;
                    ArtisanResponse response = new ArtisanResponse();

                    response.setFirstName(user.getFirstName());
                    response.setLastName(user.getLastName());
                    response.setEmail(user.getEmail());
                    response.setPhoneNumber(user.getPhoneNumber());
                    response.setCategory(artisan.getCategory());
                    response.setAddress(artisan.getAddress());

                    return response;
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<ArtisanResponse> findArtisanByCategory(ArtisanByCategoryRequest artisanByCategoryRequest) {
        Category category = artisanByCategoryRequest.getCategory();

        List<User> users = userRepository.findAll();

        return users.stream()
                .filter(user -> user.getRole() == Role.ARTISAN)
                .map(user -> (Artisan) user)
                .filter(artisan -> artisan.getCategory() == category)
                .map(artisan -> {
                    ArtisanResponse artisanResponse = new ArtisanResponse();

                    artisanResponse.setFirstName(artisan.getFirstName());
                    artisanResponse.setLastName(artisan.getLastName());
                    artisanResponse.setEmail(artisan.getEmail());
                    artisanResponse.setPhoneNumber(artisan.getPhoneNumber());
                    artisanResponse.setCategory(artisan.getCategory());
                    artisanResponse.setAddress(artisan.getAddress());

                    return artisanResponse;
                })
                .collect(Collectors.toList());

    }

    @Override
    public List<UserRoleResponse> findUsersByRole(UserRoleRequest userRoleRequest) {
        Role role = userRoleRequest.getRole();

        if (role == null) {
            throw new RoleNotFoundException("The specified role does not exist.");
        }

        List<User> users = userRepository.findByRole(role);

        return users.stream().map(user -> {
            UserRoleResponse response = new UserRoleResponse();
            response.setFirstName(user.getFirstName());
            response.setLastName(user.getLastName());
            response.setEmail(user.getEmail());
            response.setPhoneNumber(user.getPhoneNumber());
            response.setRole(user.getRole());
            return response;
        }).toList();
    }





//    @Override
//    public DeleteUserResponse deleteArtisanById(Long id) {
//        Artisan artisan = findArtisanById(id);
//        Long userId = artisan.getUser().getId();
//        User user = findUserById(userId);
//        artisanService.delete(id);
//        userRepository.deleteById(user.getId());
//
//        DeleteUserResponse response = new DeleteUserResponse();
//        response.setMessage("Artisan successfully deleted");
//        return response;
//    }



    @Override
    public UpdateUserProfileResponse updateUserProfile(Long userId, UpdateUserProfileRequest updateUserRequest) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + userId));
        if (updateUserRequest.getFirstName() != null) {
            user.setFirstName(updateUserRequest.getFirstName());
        }
        if (updateUserRequest.getLastName() != null) {
            user.setLastName(updateUserRequest.getLastName());
        }
        if (updateUserRequest.getEmail() != null) {
            user.setEmail(updateUserRequest.getEmail());
        }
        if (updateUserRequest.getPhoneNumber() != null) {
            user.setPhoneNumber(updateUserRequest.getPhoneNumber());
        }
        if (updateUserRequest.getAddress() != null) {
            user.setAddress(updateUserRequest.getAddress());
        }
        userRepository.save(user);

        UpdateUserProfileResponse response = new UpdateUserProfileResponse();
        response.setMessage("User updated successfully");
        return response;


    }

    @Override
    public User getUserProfile(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + userId));

    }

    @Override
    public void resetPassword(ResetPasswordRequest request) {

    }

    @Override
    public void changePassword(Long id, ChangePasswordRequest request) {

    }

    private static void validateRegistration (RegisterUserRequest request){
        if (!request.getEmail().matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$"))
            throw new UserAlreadyExistException("Invalid Input");
        if (request.getPassword().isEmpty())
            throw new IncorrectPasswordException("Invalid Password provide a Password");
    }




private void validateUser(RegisterUserRequest request) {
    if (request == null) {
        throw new NullPointerException("User DTO cannot be null");
    }

    if (request.getFirstName() == null || request.getFirstName().isBlank()) {
        throw new IllegalArgumentException("First name is required");
    }

    if (request.getLastName() == null || request.getLastName().isBlank()) {
        throw new IllegalArgumentException("Last name is required");
    }

    if (request.getEmail() == null || !request.getEmail().matches("^[A-Za-z0-9+_.-]+@([A-Za-z0-9-]+\\.)+[A-Za-z0-9-]+$")) {
        throw new IllegalArgumentException("Invalid email format");
    }

    if (request.getPassword() == null || request.getPassword().length() < 8) {
        throw new IllegalArgumentException("Password must be at least 8 characters long");
    }
    if (request.getPhoneNumber() == null || !request.getPhoneNumber().matches("^\\+?\\d{10,15}$")) {
        throw new IllegalArgumentException("Invalid phone number format");
    }
}



}

