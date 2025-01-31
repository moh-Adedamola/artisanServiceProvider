package com.semicolonafrica.data.repository;

import com.semicolonafrica.data.model.Job;
import com.semicolonafrica.data.model.JobStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JobRepository extends JpaRepository<Job, Long> {

    List<Job> findByStatus(JobStatus status);
//    List<Job> findByClientId(Long clientId);
//    List<Job> findByArtisanId(Long artisanId);

}
