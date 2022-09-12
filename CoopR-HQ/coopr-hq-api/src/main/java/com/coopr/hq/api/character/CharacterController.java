package com.coopr.hq.api.character;

import com.coopr.hq.core.models.Character;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private CharacterRepository characterRepository;

    // Posts
    @PostMapping(value = API_VERSION + CHARACTER_LIST + METHOD_SAVE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updateCharacters(@RequestBody List<Character> characters) {
        characterRepository.saveAll(characters);
    }
    @PostMapping(value = API_VERSION + CHARACTER + METHOD_SAVE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updateCharacter(@RequestBody Character character) {
        characterRepository.save(character);
        log.info("Character with UID " + character.getCharacterID() + " has been saved");
    }

    // Queries
    @GetMapping(API_VERSION + CHARACTER_LIST + METHOD_FETCH)
    public List<Character> fetchAllCharacters() {
        return characterRepository.findAll();
    }
    @GetMapping(API_VERSION + CHARACTER + METHOD_FETCH + "/{characterID:^[0-9]*$}")
    public Character fetchCharacter(@PathVariable("characterID") String characterID) {
        return characterRepository.findById(characterID).orElseGet(Character::new);
    }
}
