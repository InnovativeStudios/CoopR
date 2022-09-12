package com.coopr.hq.api.character;

import com.coopr.hq.core.models.Character;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Log
public class CharacterController {
    private final String API_VERSION = "/api/v0.1/";
    private final String CHARACTER = "character/";
    private final String CHARACTER_LIST = "characters/";
    private final String METHOD_SAVE = "save";
    private final String METHOD_FETCH = "fetch";

    private MongoTemplate mongoTemplate;

    @Autowired
    public CharacterController(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    // Posts
    @PostMapping(value = API_VERSION + CHARACTER_LIST + METHOD_SAVE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updateCharacters(@RequestBody List<Character> characters) {
        characters.forEach(this::updateCharacter);
    }
    @PostMapping(value = API_VERSION + CHARACTER + METHOD_SAVE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updateCharacter(@RequestBody Character character) {
        mongoTemplate.save(character);
        log.info("Character with UID " + character.getCharacterID() + " has been Saved");
    }

    // Queries
    @GetMapping(API_VERSION + CHARACTER_LIST + METHOD_FETCH)
    public List<Character> fetchAllCharacters() {
        return mongoTemplate.findAll(Character.class);
    }
    @GetMapping(API_VERSION + CHARACTER + METHOD_FETCH + "/{characterID:^[0-9]*$}")
    public Character fetchCharacter(@PathVariable("characterID") String characterID) {
        return mongoTemplate.findById(characterID, Character.class);
    }
}
