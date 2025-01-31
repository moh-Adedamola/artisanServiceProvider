package com.semicolonafrica.dtos.request;

import com.semicolonafrica.data.model.JobStatus;
import com.semicolonafrica.services.JobService;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter

public class CreateJobRequest {
    private long clientId;
    private String title;
    private String description;
    private JobStatus status;
    private Double budget;


}
