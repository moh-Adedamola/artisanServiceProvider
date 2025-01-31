package com.semicolonafrica.services;

import com.semicolonafrica.data.model.Job;
import com.semicolonafrica.data.model.JobStatus;
import com.semicolonafrica.dtos.request.CreateJobRequest;
import com.semicolonafrica.dtos.request.JobStatusRequest;
import com.semicolonafrica.dtos.request.UpdateJobRequest;
import com.semicolonafrica.dtos.response.*;

import java.util.List;

public interface JobService {

    CreateJobResponse createJob(CreateJobRequest createJobRequest);
    List<Job> getAllJobs();
    JobResponse getJobById(Long id);
    UpdateJobResponse updateJob(Long jobId, UpdateJobRequest updateJobRequest);
    DeleteJobResponse deleteJob(Long jobId);
    List<JobStatusResponse> getJobsByStatus(JobStatusRequest jobStatusRequest);
//    JobResponse assignArtisan(Long jobId, Long artisanId);
//
////    List<JobResponse> getJobsByClientId(Long clientId);
////    List<JobResponse> getJobsByArtisanId(Long artisanId);
//    CreateJobResponse updateJobStatus(Long id, JobStatus status);
}
