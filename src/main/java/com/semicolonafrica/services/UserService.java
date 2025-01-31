package com.semicolonafrica.services;

import com.semicolonafrica.data.model.Artisan;
import com.semicolonafrica.data.model.Category;
import com.semicolonafrica.data.model.Role;
import com.semicolonafrica.data.model.User;
import com.semicolonafrica.dtos.request.*;
import com.semicolonafrica.dtos.response.*;

import java.util.List;

public interface UserService {
    RegisterUserResponse registerUser(RegisterUserRequest request);
    UpdateUserProfileResponse updateUserProfile(Long userId, UpdateUserProfileRequest updateUserRequest);
    LoginResponse login(LoginRequest loginRequest);
    List<UsersResponse> findAllUsers();
    List<ArtisanResponse> getAllArtisans();
    List<ArtisanResponse> findArtisanByCategory(ArtisanByCategoryRequest artisanByCategoryRequest);
    List<UserRoleResponse> findUsersByRole(UserRoleRequest userRoleRequest);
    UsersResponse findUserById(Long id);
    DeleteUserResponse deleteUser(Long id);
    LogoutResponse logout(LogoutRequest logoutRequest);


    DeleteUserResponse deleteById(Long id);


    User getUserProfile(Long userId);
    void resetPassword(ResetPasswordRequest request);
    void changePassword(Long id, ChangePasswordRequest request);
}
