package com.semicolonafrica.dtos.response;

import com.semicolonafrica.data.model.JobStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class JobStatusResponse {

    private String title;
    private String description;
    private JobStatus status;

}
