package com.semicolonafrica.data.repository;

import com.semicolonafrica.data.model.Artisan;
import com.semicolonafrica.data.model.Category;
import com.semicolonafrica.data.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArtisanRepository extends JpaRepository<Artisan, Long> {

      List<Artisan> findByRoleAndCategory(Role role, Category category);

}
