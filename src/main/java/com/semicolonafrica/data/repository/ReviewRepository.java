package com.semicolonafrica.data.repository;

import com.semicolonafrica.data.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
//    List<Review> findByJobId(Long jobId);

}
