package com.semicolonafrica.data.repository;

import com.semicolonafrica.data.model.Artisan;
import com.semicolonafrica.data.model.Category;
import com.semicolonafrica.data.model.Role;
import com.semicolonafrica.data.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

//    List<User> findByRoleAndCategory(Role role, String category);

    Optional<User> findByEmailIgnoreCase(String email);

    List<User> findByRole(Role role);


//    List<User> findByCategoryIgnoreCase(Category category);

}
