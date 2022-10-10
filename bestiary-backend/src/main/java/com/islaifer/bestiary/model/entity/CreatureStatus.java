package com.islaifer.bestiary.model.entity;

import com.islaifer.bestiary.model.dto.CreatureStatusDTO;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Entity class for creature status
 * @version 0.2.0
 * */
@Data
@Entity
public class CreatureStatus {
    @Id @GeneratedValue
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

    public CreatureStatus(CreatureStatusDTO data){
        clone(data);
    }

    public void clone(CreatureStatusDTO creatureStatusDTO) {
        this.id = creatureStatusDTO.getId();
        this.healthPoints = creatureStatusDTO.getHealthPoints();
        this.difficultyLevel = creatureStatusDTO.getDifficultyLevel();
        this.maxAge = creatureStatusDTO.getMaxAge();
        this.size = creatureStatusDTO.getSize();
        this.strength = creatureStatusDTO.getStrength();
        this.dexterity = creatureStatusDTO.getDexterity();
        this.constitution = creatureStatusDTO.getConstitution();
        this.intelligence = creatureStatusDTO.getIntelligence();
        this.wisdom = creatureStatusDTO.getWisdom();
        this.charisma = creatureStatusDTO.getCharisma();
        this.trend = creatureStatusDTO.getTrend();
    }
}
