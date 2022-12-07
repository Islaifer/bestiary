package com.islaifer.bestiary.model.dto;

import com.islaifer.bestiary.model.entity.CreatureDescription;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO class for creatures description
 * @version 0.6.0
 * */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreatureDescriptionDTO{

    private Long id;

    private String appearance;

    private String behavior;

    private String habitat;

    private String warning;

    private String obs;

    public CreatureDescriptionDTO(CreatureDescription creatureDescription){
        clone(creatureDescription);
    }

    private void clone(CreatureDescription creatureDescription){
        this.id = creatureDescription.getId();
        this.appearance = creatureDescription.getAppearance();
        this.behavior = creatureDescription.getBehavior();
        this.habitat = creatureDescription.getHabitat();
        this.warning = creatureDescription.getWarning();
        this.obs = creatureDescription.getObs();
    }
}
