package com.semicolonafrica.dtos.request;

import com.semicolonafrica.data.model.JobStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class JobStatusRequest {
    private JobStatus status;
}
