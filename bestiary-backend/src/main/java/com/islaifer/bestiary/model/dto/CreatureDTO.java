package com.islaifer.bestiary.model.dto;

import com.islaifer.bestiary.model.entity.Creature;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

/**
 * DTO class for creatures
 * @version 0.1.0
 * */
@Data
public class CreatureDTO {
    private Long id;

    private String name;

    private BreedDTO breed;

    private CreatureStatusDTO status;

    private List<CreatureSkillDTO> skills;

    private CreatureDescriptionDTO description;

    private String urlImg;

    public CreatureDTO(Creature data){
        clone(data);
    }

    public void clone(Object data) {
        if(data instanceof Creature creature){
            this.id = creature.getId();
            this.name = creature.getName();
            this.breed = new BreedDTO(creature.getBreed());
            this.status = new CreatureStatusDTO(creature.getStatus());
            this.skills = creature.getSkills().stream().map(CreatureSkillDTO::new)
                    .collect(Collectors.toList());
        }
    }
}
