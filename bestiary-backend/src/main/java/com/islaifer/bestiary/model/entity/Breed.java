package com.islaifer.bestiary.model.entity;

import com.islaifer.bestiary.model.dto.BreedDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Entity class for creature races
 * @version 0.5.0
 * */
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Breed {

    @Id @GeneratedValue
    private Long id;

    private String name;

    private String description;

    @OneToMany(mappedBy = "breed")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Creature> creatures;

    public Breed(BreedDTO data){
        clone(data);
    }
    public void clone(BreedDTO breedDTO){
        if(breedDTO.getName() != null) this.name = breedDTO.getName();
        if(breedDTO.getDescription() != null) this.description = breedDTO.getDescription();
    }

    @Override
    public String toString(){
        return this.name;
    }
}
