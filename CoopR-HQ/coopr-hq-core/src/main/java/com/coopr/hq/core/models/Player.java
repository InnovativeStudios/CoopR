package com.coopr.hq.core.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "coopr_hq_players")
public class Player {
    @Id
    private String uid;
    private String password;
    private List<String> characters;
}
