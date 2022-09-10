package com.coopr.hq.repositories;

import com.coopr.hq.core.models.Character;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CharacterRepository extends MongoRepository<Character, String> { }
