package com.coopr.hq.endpoints;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreationResponse {
    private boolean created;
    private String reason;
}
