package com.semicolonafrica.dtos.response;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class CreateJobResponse {
    private String message;
    private JobResponse jobResponse;

    public CreateJobResponse() {
    }
//    private CreateJob createJob;


//    public CreateJobResponse(String jobCreatedSuccessfully, Job savedJob) {
//    }
}
