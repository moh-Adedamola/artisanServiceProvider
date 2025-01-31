package com.semicolonafrica.dtos.response;

import com.semicolonafrica.data.model.Job;
import com.semicolonafrica.data.model.JobStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class JobResponse {
    private Long id;
    private String title;
    private String description;
    private JobStatus status;
    private Double budget;
    private Long clientId;
    private LocalDateTime createdAt;

    public JobResponse(Job job) {
    }
}
