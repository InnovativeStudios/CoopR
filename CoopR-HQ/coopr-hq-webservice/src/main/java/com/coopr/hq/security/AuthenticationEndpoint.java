package com.coopr.hq.security;

import com.coopr.hq.core.models.Player;
import com.coopr.hq.security.models.RememberMeCookie;
import lombok.extern.java.Log;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@RestController
@Log
public class AuthenticationEndpoint {
    private final String LOGIN = "login";
    private final String REMEMBER_ME_COOKIE = "COOPR_REMEMBER_ME";

    private MongoTemplate mongoTemplate;

    @Autowired
    public AuthenticationEndpoint(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @PostMapping(value = LOGIN, consumes = MediaType.APPLICATION_JSON_VALUE)
    public AuthResponse loginUser(@RequestBody UserCredentials userCredentials,
                                  @CookieValue(value = REMEMBER_ME_COOKIE, required = false) String cookieValue,
                                  HttpServletResponse response) {
        String uid = userCredentials.getSteamID();
        String password = userCredentials.getPassword();

        if (cookieValue != null) {
            Query query = new Query().addCriteria(Criteria.where("cookieHash").is(cookieValue));
            RememberMeCookie rememberMeCookies = mongoTemplate.findOne(query, RememberMeCookie.class);
            if (rememberMeCookies.getCookieType().equals(REMEMBER_ME_COOKIE) && rememberMeCookies.getCookieHash().equals(cookieValue)) {
                return new AuthResponse(true, rememberMeCookies.getSteamID(), "Remember Me Token is Still Active");
            }
        }

        if (uid == null) {
            return new AuthResponse(false, uid, "No UID Defined");
        }
        if (password == null) {
            return new AuthResponse(false, uid, "No Password Defined");
        }

        log.info("Trying to authenticate for " + uid);
        Query query = new Query().addCriteria(Criteria.where("_id").is(uid));
        Player player = mongoTemplate.findOne(query, Player.class);

        if (player == null) {
            return new AuthResponse(false, uid, "Given SteamID '" + uid + "' Could Not be Found in Database");
        }
        if (!player.getPassword().equals(password)) {
            return new AuthResponse(false, uid, "Given Password Does Not Match User");
        }

        saveRememberMeToken(player, response);
        return new AuthResponse(true, uid, "Login Successful");
    }

    private void saveRememberMeToken(Player player, HttpServletResponse response) {
        String hash = DigestUtils.sha256Hex(player.toString());
        response.addCookie(new Cookie(REMEMBER_ME_COOKIE, hash));
        RememberMeCookie rememberMeCookie = new RememberMeCookie(player.getUid(), REMEMBER_ME_COOKIE, hash);
        mongoTemplate.save(rememberMeCookie);
    }
}
