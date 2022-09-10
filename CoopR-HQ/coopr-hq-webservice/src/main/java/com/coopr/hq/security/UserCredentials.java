package com.coopr.hq.security;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserCredentials {
    private String steamid;
    private String password;
}
