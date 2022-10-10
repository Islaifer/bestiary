package com.islaifer.bestiary.model.dto;

import com.islaifer.bestiary.model.entity.Breed;
import lombok.Data;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * DTO class for creature races
 * @version 0.3.0
 * */
@Data
public class BreedDTO {
    private Long id;

    private String name;

    private String description;

    private List<CreatureDTO> creatures;
    public BreedDTO(Breed data){
        creatures = new LinkedList<>();
        if(data != null)clone(data);
    }

    private void clone(Breed breed) {
        this.id = breed.getId();
        this.name = breed.getName();
        this.description = breed.getDescription();
        this.creatures = breed.getCreatures().stream().map(CreatureDTO::new)
                    .collect(Collectors.toList());
    }
}
