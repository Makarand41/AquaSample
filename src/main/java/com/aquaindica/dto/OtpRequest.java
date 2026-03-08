package com.aquaindica.dto;

import lombok.Data;

@Data
public class OtpRequest {
    private String username;
    private String otp;
}
