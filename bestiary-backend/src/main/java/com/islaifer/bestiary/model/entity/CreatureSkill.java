package com.islaifer.bestiary.model.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Entity class for creature skills
 * @version 1.0.0
 * */
@Data
@Entity
public class CreatureSkill {
    @Id @GeneratedValue
    private Long id;

    private String name;

    private String description;
}
