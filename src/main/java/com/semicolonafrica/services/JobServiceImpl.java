package com.semicolonafrica.services;

import com.semicolonafrica.data.model.*;
import com.semicolonafrica.data.repository.ClientRepository;
import com.semicolonafrica.data.repository.CreateJobRepository;
import com.semicolonafrica.data.repository.JobRepository;
import com.semicolonafrica.data.repository.UserRepository;
import com.semicolonafrica.dtos.request.CreateJobRequest;
import com.semicolonafrica.dtos.request.JobStatusRequest;
import com.semicolonafrica.dtos.request.UpdateJobRequest;
import com.semicolonafrica.dtos.response.*;
import com.semicolonafrica.exception.JobNotFoundException;
import com.semicolonafrica.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service

public class JobServiceImpl implements JobService {
    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;
    @Autowired
    private CreateJobRepository createJobRepository;

    @Autowired
    private ClientRepository clientRepository;



    @Override
    public CreateJobResponse createJob(CreateJobRequest createJobRequest) {
        validateCreateJobRequest(createJobRequest);

        Client client = clientRepository.findById(createJobRequest.getClientId())
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + createJobRequest.getClientId()));

        Job jobRequest = new Job();
        jobRequest.setClient(client);
        jobRequest.setTitle(createJobRequest.getTitle());
        jobRequest.setDescription(createJobRequest.getDescription());
        jobRequest.setStatus(JobStatus.OPEN);
        jobRequest.setCreatedAt(LocalDateTime.now());
        Job savedJob = jobRepository.save(jobRequest);

        CreateJobResponse response = new CreateJobResponse();
        response.setMessage("Job created successfully");

        return response;


    }

    @Override
    public List<Job> getAllJobs() {
        return jobRepository.findAll();
    }

    @Override
    public JobResponse getJobById(Long id) {
        Job job = jobRepository.findById(id).orElseThrow(() -> new JobNotFoundException("Job not found"));
        return new JobResponse(job);
    }

    @Override
    public UpdateJobResponse updateJob(Long jobId, UpdateJobRequest updateJobRequest) {
        validateUpdateJobRequest(updateJobRequest);
        Job existingJob = jobRepository.findById(jobId)
                .orElseThrow(() -> new JobNotFoundException("Job not found with id: " + jobId));

        if (updateJobRequest.getTitle() != null && !updateJobRequest.getTitle().isEmpty()) {
            existingJob.setTitle(updateJobRequest.getTitle());
        }

        if (updateJobRequest.getDescription() != null && !updateJobRequest.getDescription().isEmpty()) {
            existingJob.setDescription(updateJobRequest.getDescription());
        }

        if (updateJobRequest.getStatus() != null) {
            existingJob.setStatus(updateJobRequest.getStatus());
        }

        existingJob.setUpdatedAt(LocalDateTime.now());
        Job updatedJob = jobRepository.save(existingJob);

        UpdateJobResponse response = new UpdateJobResponse();
        response.setMessage("Job updated successfully");

        return response;
    }

    @Override
    public DeleteJobResponse deleteJob(Long jobId) {

        Job job = jobRepository.findById(jobId).orElseThrow(() -> new JobNotFoundException("Job not found"));
        jobRepository.delete(job);

        DeleteJobResponse response = new DeleteJobResponse();
        response.setMessage("Job deleted successfully");
        return response;
    }



    @Override
    public List<JobStatusResponse> getJobsByStatus(JobStatusRequest jobStatusRequest) {
        JobStatus status = jobStatusRequest.getStatus();

        if (status == null) {
            throw new JobNotFoundException("No jobs found with the specified status.");
        }

        List<Job> jobs = jobRepository.findByStatus(status);

        return jobs.stream().map(job -> {
            JobStatusResponse response = new JobStatusResponse();
            response.setTitle(job.getTitle());
            response.setDescription(job.getDescription());
            response.setStatus(job.getStatus());
            return response;
        }).toList();
    }

//    @Override
//    public JobResponse assignArtisan(Long jobId, Long artisanId) {
//        Job job = jobRepository.findById(jobId).orElseThrow(() -> new ResourceNotFoundException("Job not found"));
//        Artisan artisan = artisanRepository.findById(artisanId).orElseThrow(() -> new ResourceNotFoundException("Artisan not found"));
//
//        job.setArtisan(artisan);
//        jobRepository.save(job);
//        return new JobResponse(job);
//    }


    private void validateUpdateJobRequest(UpdateJobRequest updateJobRequest) {
        if (updateJobRequest.getTitle() == null || updateJobRequest.getTitle().trim().isEmpty()) {
            throw new IllegalArgumentException("Title cannot be null or empty");
        }
        if (updateJobRequest.getDescription() == null || updateJobRequest.getDescription().trim().isEmpty()) {
            throw new IllegalArgumentException("Description cannot be null or empty");
        }
    }

    private void validateCreateJobRequest(CreateJobRequest createJobRequest) {
        if (createJobRequest.getTitle() == null || createJobRequest.getTitle().trim().isEmpty()) {
            throw new IllegalArgumentException("Title cannot be null or empty");
        }
        if (createJobRequest.getDescription() == null || createJobRequest.getDescription().trim().isEmpty()) {
            throw new IllegalArgumentException("Description cannot be null or empty");
        }

    }



}
