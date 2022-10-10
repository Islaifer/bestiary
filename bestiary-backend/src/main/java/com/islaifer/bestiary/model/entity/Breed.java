package com.islaifer.bestiary.model.entity;

import com.islaifer.bestiary.model.dto.BreedDTO;
import lombok.Data;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Entity class for creature races
 * @version 0.3.0
 * */
@Data
@Entity
public class Breed {

    @Id @GeneratedValue
    private Long id;

    private String name;

    private String description;

    @OneToMany(mappedBy = "breed", cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Creature> creatures;

    public Breed(BreedDTO data){
        clone(data);
    }
    public void clone(BreedDTO breedDTO){
        this.name = breedDTO.getName();
        this.description = breedDTO.getDescription();
        this.creatures = breedDTO.getCreatures().stream().map(Creature::new)
                .collect(Collectors.toList());
    }
}
