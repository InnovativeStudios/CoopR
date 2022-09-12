package com.coopr.hq.api.player;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreationResponse {
    private boolean created;
    private String reason;
}
