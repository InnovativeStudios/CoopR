package com.coopr.hq.security.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "coopr_hq_cookies")
public class RememberMeCookie {
    @Id
    private String steamID;
    private String cookieType;
    private String cookieHash;
}
