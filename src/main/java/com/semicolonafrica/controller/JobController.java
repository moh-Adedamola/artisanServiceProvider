package com.semicolonafrica.controller;

import com.semicolonafrica.dtos.request.CreateJobRequest;
import com.semicolonafrica.dtos.request.JobStatusRequest;
import com.semicolonafrica.dtos.request.UpdateJobRequest;
import com.semicolonafrica.dtos.request.UserRoleRequest;
import com.semicolonafrica.dtos.response.ApiResponse;
import com.semicolonafrica.dtos.response.CreateJobResponse;
import com.semicolonafrica.dtos.response.JobStatusResponse;
import com.semicolonafrica.dtos.response.UserRoleResponse;
import com.semicolonafrica.services.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/job")

public class JobController {
    private final JobService jobService;

    @Autowired
    public JobController(JobService jobService) {
        this.jobService = jobService;
    }
    @PostMapping("/createJob")
    public ResponseEntity<?> createJob(@RequestBody CreateJobRequest createJobRequest) {
        try {
            CreateJobResponse response = jobService.createJob(createJobRequest);
            return new ResponseEntity<>(new ApiResponse(true,response), HttpStatus.CREATED);
        } catch (Exception exception) {
            return new ResponseEntity<>(new ApiResponse(false,exception.getMessage()), HttpStatus.BAD_REQUEST);

        }
    }

    @GetMapping("/findAllJobs")
    public ResponseEntity<?> getAllJobs() {
        try {
            return new ResponseEntity<>(new ApiResponse(true, jobService.getAllJobs()), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/findJobById/{id}")
    public ResponseEntity<?> getJobById(@PathVariable("id") Long id) {
        try {
            return new ResponseEntity<>(new ApiResponse(true, jobService.getJobById(id)), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/updateJob/{id}")
    public ResponseEntity<?> updateJob(@PathVariable("id") Long jobId, @RequestBody UpdateJobRequest updateJobRequest){
        try{
            return new ResponseEntity<>(new ApiResponse(true, jobService.updateJob( jobId, updateJobRequest)), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/deleteJob/{id}")
    public ResponseEntity<?> deleteJob(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(new ApiResponse(true, jobService.deleteJob(id)), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/findJobByStatus")
    public ResponseEntity<?> getJobsByStatus(@RequestBody JobStatusRequest jobStatusRequest){
        try {
            List<JobStatusResponse> jobs = jobService.getJobsByStatus(jobStatusRequest);
            return new ResponseEntity<>(new ApiResponse(true, jobs), HttpStatus.CREATED);
        } catch (Exception exception) {
            return new ResponseEntity<>(new ApiResponse(false, exception.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

}
