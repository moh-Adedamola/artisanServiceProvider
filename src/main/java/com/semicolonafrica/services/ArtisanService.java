package com.semicolonafrica.services;

import com.semicolonafrica.data.model.Artisan;
import com.semicolonafrica.data.model.Category;
import com.semicolonafrica.data.model.User;
import com.semicolonafrica.dtos.request.RegisterUserRequest;

import java.util.List;

public interface ArtisanService {
//    Artisan createArtisan(User user, RegisterUserRequest request);
    List<Artisan> findAllAvailableArtisan();

    List<Artisan> findAllArtisans();

    Artisan findById(Long ArtisanId);

    void delete(long id);

    void deleteAll();

//    List<Artisan> findArtisanByCategory(Category category);

}
