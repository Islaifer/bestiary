package com.islaifer.bestiary.model.dto;

import com.islaifer.bestiary.model.entity.Creature;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * DTO class for creatures
 * @version 0.5.0
 * */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreatureDTO {
    private Long id;

    private String name;

    private BreedDTO breed;

    private CreatureStatusDTO status;

    private List<CreatureSkillDTO> skills;

    private CreatureDescriptionDTO description;

    private String urlImg;

    public CreatureDTO(Creature data){
        skills = new LinkedList<>();
        if(data != null)clone(data);
    }

    private void clone(Creature creature) {
        this.id = creature.getId();
        this.name = creature.getName();
        this.breed = new BreedDTO(creature.getBreed());
        this.status = new CreatureStatusDTO(creature.getStatus());
        this.description = new CreatureDescriptionDTO(creature.getDescription());
        this.skills = creature.getSkills().stream().map(CreatureSkillDTO::new)
                    .collect(Collectors.toList());
        this.urlImg = creature.getUrlImg();
    }
}
