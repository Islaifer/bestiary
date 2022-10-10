package com.islaifer.bestiary.model.dto;

import com.islaifer.bestiary.model.entity.CreatureDescription;
import lombok.Data;

/**
 * DTO class for creatures description
 * @version 0.2.0
 * */
@Data
public class CreatureDescriptionDTO{

    private Long id;

    private String appearance;

    private String behavior;

    private String habitat;

    private String warning;

    private String obs;

    public CreatureDescriptionDTO(CreatureDescription data){
        clone(data);
    }

    public void clone(CreatureDescription creatureDescription) {
        this.id = creatureDescription.getId();
        this.appearance = creatureDescription.getAppearance();
        this.behavior = creatureDescription.getBehavior();
        this.habitat = creatureDescription.getHabitat();
        this.warning = creatureDescription.getWarning();
        this.obs = creatureDescription.getObs();
    }
}
