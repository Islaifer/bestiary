package com.islaifer.bestiary.model.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Entity class for creature status
 * @version 1.0.0
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
}
