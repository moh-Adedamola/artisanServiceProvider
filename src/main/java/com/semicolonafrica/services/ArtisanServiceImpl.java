package com.semicolonafrica.services;

import com.semicolonafrica.data.model.Artisan;
import com.semicolonafrica.data.model.Category;
import com.semicolonafrica.data.model.User;
import com.semicolonafrica.data.repository.ArtisanRepository;
import com.semicolonafrica.dtos.request.RegisterUserRequest;
import com.semicolonafrica.exception.ArtisanDoesNotExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service

public class ArtisanServiceImpl implements ArtisanService{

    @Autowired
    private ArtisanRepository artisanRepository;

//    @Override
//    public Artisan createArtisan(User user, RegisterUserRequest request) {
//        Artisan artisan = new Artisan();
//        artisan.setUser(user);
//        artisan.setAvailability(true);
//        artisan.setCategory();
//        return artisanRepository.save(artisan);
//    }

//    @Override
//    public List<Artisan> findAllAvailableArtisan() {
//        List<Artisan> availableArtisans = new ArrayList<>();
//        for (Artisan artisan : artisanRepository.findAll()) {
//            if (artisan.isAvailability()){
//                availableArtisans.add(artisan);
//            }
//        }
//        return availableArtisans;
//    }

    @Override
    public List<Artisan> findAllAvailableArtisan() {
        return List.of();
    }

    @Override
    public List<Artisan> findAllArtisans() {
        return artisanRepository.findAll();
    }

    @Override
    public Artisan findById(Long artisanId) {
        return artisanRepository.findById(artisanId)
                .orElseThrow(()-> new ArtisanDoesNotExistException("Artisan does not exist"));
    }

    @Override
    public void delete(long id) {
        artisanRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        artisanRepository.deleteAll();
    }

//    @Override
//    public List<Artisan> findArtisanByCategory(Category category) {
////        List<Artisan> allArtisan = findAllArtisans();
////        List<Artisan> artisanByCategory = new ArrayList<>();
////
////        for (Artisan artisan : allArtisan) {
////            if (artisan.getCategory() != null && artisan.getCategory().equals(category)) {
////                artisanByCategory.add(artisan);
////            }
////        }
////        return artisanByCategory;
//        return null;
//    }
}
