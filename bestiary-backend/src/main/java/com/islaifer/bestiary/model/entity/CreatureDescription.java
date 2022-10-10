package com.islaifer.bestiary.model.entity;

import com.islaifer.bestiary.model.dto.CreatureDescriptionDTO;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Entity class for creature description
 * @version 0.2.0
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

    public CreatureDescription(CreatureDescriptionDTO data){
        clone(data);
    }

    public void clone(CreatureDescriptionDTO creatureDescriptionDTO) {
        this.id = creatureDescriptionDTO.getId();
        this.appearance = creatureDescriptionDTO.getAppearance();
        this.behavior = creatureDescriptionDTO.getBehavior();
        this.habitat = creatureDescriptionDTO.getHabitat();
        this.warning = creatureDescriptionDTO.getWarning();
        this.obs = creatureDescriptionDTO.getObs();
    }
}
