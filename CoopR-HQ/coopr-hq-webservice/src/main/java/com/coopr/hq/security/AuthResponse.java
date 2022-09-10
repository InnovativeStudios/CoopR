package com.coopr.hq.security;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthResponse {
    private boolean authenticated;
    private String steamid;
    private String reason;
}
