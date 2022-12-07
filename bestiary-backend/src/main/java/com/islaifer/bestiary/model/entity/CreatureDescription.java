package com.islaifer.bestiary.model.entity;

import com.islaifer.bestiary.model.dto.CreatureDescriptionDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Entity class for creature description
 * @version 0.5.0
 * */
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
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
        if(creatureDescriptionDTO.getId() != null) this.id = creatureDescriptionDTO.getId();
        if(creatureDescriptionDTO.getAppearance() != null)
            this.appearance = creatureDescriptionDTO.getAppearance();
        if(creatureDescriptionDTO.getBehavior() != null)
            this.behavior = creatureDescriptionDTO.getBehavior();
        if(creatureDescriptionDTO.getHabitat() != null)
            this.habitat = creatureDescriptionDTO.getHabitat();
        if(creatureDescriptionDTO.getWarning() != null)
            this.warning = creatureDescriptionDTO.getWarning();
        if(creatureDescriptionDTO.getObs() != null)this.obs = creatureDescriptionDTO.getObs();
    }
}
