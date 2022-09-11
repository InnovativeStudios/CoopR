package com.coopr.hq.repositories;

import com.coopr.hq.core.models.Player;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PlayerRepository extends MongoRepository<Player, String> {
}
