package com.islaifer.bestiary.model.entity;

import com.islaifer.bestiary.model.dto.CreatureStatusDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Entity class for creature status
 * @version 0.5.0
 * */
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
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
        if(creatureStatusDTO.getHealthPoints() >= 0)this.healthPoints = creatureStatusDTO.getHealthPoints();
        if(creatureStatusDTO.getDifficultyLevel() >= 0)
            this.difficultyLevel = creatureStatusDTO.getDifficultyLevel();
        if(creatureStatusDTO.getMaxAge() >= 0)this.maxAge = creatureStatusDTO.getMaxAge();
        if(creatureStatusDTO.getSize() >= 0)this.size = creatureStatusDTO.getSize();
        if(creatureStatusDTO.getStrength() >= 0)this.strength = creatureStatusDTO.getStrength();
        if(creatureStatusDTO.getDexterity() >= 0)this.dexterity = creatureStatusDTO.getDexterity();
        if(creatureStatusDTO.getConstitution() >= 0)this.constitution = creatureStatusDTO.getConstitution();
        if(creatureStatusDTO.getIntelligence() >= 0)this.intelligence = creatureStatusDTO.getIntelligence();
        if(creatureStatusDTO.getWisdom() >= 0)this.wisdom = creatureStatusDTO.getWisdom();
        if(creatureStatusDTO.getCharisma() >= 0)this.charisma = creatureStatusDTO.getCharisma();
        if(creatureStatusDTO.getTrend() != null)this.trend = creatureStatusDTO.getTrend();
    }
}
