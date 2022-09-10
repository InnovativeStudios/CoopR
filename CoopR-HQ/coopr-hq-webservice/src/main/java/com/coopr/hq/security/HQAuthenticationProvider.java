package com.coopr.hq.security;

import com.coopr.hq.core.models.Player;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
@Log
public class HQAuthenticationProvider implements AuthenticationProvider {
    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String uid = authentication.getName();
        String password = authentication.getCredentials().toString();

        log.info("Trying to Authenticate " + uid);
        Query query = new Query().addCriteria(Criteria.where("_id").is(uid));
        Player player = mongoTemplate.findOne(query, Player.class);

        if (player == null) {
            throw new AuthenticationCredentialsNotFoundException("Username Could Not be Found");
        }

        log.info("found " + player);

        return new UsernamePasswordAuthenticationToken(uid, password, new ArrayList<>());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
