package com.islaifer.bestiary.model.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Entity class for creature description
 * @version 1.0.0
 * */
@Data
@Entity
public class CreatureDescription {
    @Id @GeneratedValue
    private Long id;

    private String appearance;

    private String behavior;

    private String habitat;

    private String warning;

    private String obs;
}
