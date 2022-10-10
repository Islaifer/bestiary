package com.islaifer.bestiary.model.dto;

import com.islaifer.bestiary.model.entity.CreatureStatus;
import lombok.Data;

/**
 * DTO class for creature status
 * @version 0.4.0
 * */
@Data
public class CreatureStatusDTO {

    private Long id;

    private int healthPoints;

    private int difficultyLevel;

    private int maxAge;

    private int size;

    private int strength;

    private int dexterity;

    private int constitution;

    private int intelligence;

    private int wisdom;

    private int charisma;

    private String trend;

    public CreatureStatusDTO(CreatureStatus data){
        if(data != null)clone(data);
    }

    private void clone(CreatureStatus creatureStatus) {
        this.id = creatureStatus.getId();
        this.healthPoints = creatureStatus.getHealthPoints();
        this.difficultyLevel = creatureStatus.getDifficultyLevel();
        this.maxAge = creatureStatus.getMaxAge();
        this.size = creatureStatus.getSize();
        this.strength = creatureStatus.getStrength();
        this.dexterity = creatureStatus.getDexterity();
        this.constitution = creatureStatus.getConstitution();
        this.intelligence = creatureStatus.getIntelligence();
        this.wisdom = creatureStatus.getWisdom();
        this.charisma = creatureStatus.getCharisma();
        this.trend = creatureStatus.getTrend();
    }
}
