package com.semicolonafrica.dtos.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class SendEmailResponse {
    private boolean success;
    private String message;
}
