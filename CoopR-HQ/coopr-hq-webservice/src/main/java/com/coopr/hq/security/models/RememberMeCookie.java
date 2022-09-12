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
    private String id;
    private String steamID;
    private String cookieType;
    private String cookieHash;

    public RememberMeCookie(String uid, String remember_me_cookie, String hash) {
    }
}
