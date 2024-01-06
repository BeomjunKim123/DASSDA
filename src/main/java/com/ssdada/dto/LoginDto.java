package com.ssdada.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginDto {

    private String accessToken;
    private String refreshToken;
    private String grantType;
    private Long expiresIn;
}
