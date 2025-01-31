package com.semicolonafrica.dtos.request;

import com.semicolonafrica.data.model.JobStatus;
import jakarta.persistence.PrePersist;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter

public class UpdateJobRequest {
    private long jobId;
    private String title;
    private String description;
    private JobStatus status;


}
