package com.islaifer.bestiary.model.dto;

import com.islaifer.bestiary.model.entity.Breed;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * DTO class for creature races
 * @version 0.4.0
 * */
@Data
@AllArgsConstructor
@NoArgsConstructor
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
    }
}
