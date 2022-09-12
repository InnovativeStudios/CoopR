package com.coopr.hq.core.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "coopr_hq_characters")
public class Character {
    @Id
    private String characterID;
    private String name;
    private CharacterRole role;
    private CharacterState state;
    private String position;
    private double timestampWIA;
    private String loadout;
    private int reputation;
    private int legacy;
    private AceRole aceRole;
}
