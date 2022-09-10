package com.coopr.hq.endpoints;

import com.coopr.hq.core.models.Character;
import com.coopr.hq.core.models.Player;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@Log
public class HqEndpoint {
    private final String API_VERSION = "/api/v0.1/";
    private final String CHARACTER = "character/";
    private final String PLAYER = "player/";
    private final String PLAYER_LIST = "players/";
    private final String CHARACTER_LIST = "characters/";
    private final String METHOD_SAVE = "save";
    private final String METHOD_FETCH = "fetch";

    private MongoTemplate mongoTemplate;

    @Autowired
    public HqEndpoint(MongoTemplate mongoTemplate) {
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
        log.info("Character with UID " + character.getCharacterId() + " has been saved");
    }

    @PostMapping(value = API_VERSION + PLAYER + METHOD_SAVE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public CreationResponse createPlayer(@RequestBody Player player) {
        if (mongoTemplate.findById(player.getUid(), Player.class) != null) {
            log.info("Player already exists");
            return new CreationResponse(false, "Player ID already exists");
        }
        mongoTemplate.save(player);
        return new CreationResponse(true, "Player has been created");
    }

    // Queries
    @GetMapping(API_VERSION + CHARACTER_LIST + METHOD_FETCH)
    public List<Character> fetchAllCharacters() {
        return mongoTemplate.findAll(Character.class);
    }

    @GetMapping(API_VERSION + CHARACTER + METHOD_FETCH + "/{characterId:^[0-9]*$}")
    public Character fetchCharacter(@PathVariable("characterId") String characterId) {
        return mongoTemplate.findById(characterId, Character.class);
    }

    @GetMapping(API_VERSION + PLAYER + METHOD_FETCH + "/{uid:^[0-9]*$}")
    public Player fetchPlayer(@PathVariable("uid") String uid) {
        return mongoTemplate.findById(uid, Player.class);
    }

    @GetMapping(API_VERSION + PLAYER_LIST + METHOD_FETCH)
    public List<Player> fetchAllPlayers() {
        return mongoTemplate.findAll(Player.class);
    }

    @GetMapping(API_VERSION + PLAYER + CHARACTER_LIST + METHOD_FETCH + "/{uid:^[0-9]*$}")
    public List<Character> fetchCharactersOfPlayer(@PathVariable("uid") String uid) {
        Player player = mongoTemplate.findById(uid, Player.class);
        List<String> characterIDs = Objects.requireNonNull(player, "Player was null").getCharacters();
        return characterIDs.stream()
                .map(characterId -> mongoTemplate.findById(characterId, Character.class))
                .collect(Collectors.toList());
    }
}
