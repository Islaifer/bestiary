package com.islaifer.bestiary.model.entity;

import com.islaifer.bestiary.model.dto.CreatureDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Entity class for creature
 * @version 0.6.0
 * */
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Creature {
    @Id @GeneratedValue
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "breed_id", nullable = false)
    private Breed breed;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "status_id")
    private CreatureStatus status;

    @ManyToMany(cascade = CascadeType.MERGE)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<CreatureSkill> skills;

    @OneToOne(cascade = CascadeType.MERGE) @JoinColumn
    private CreatureDescription description;

    private String urlImg;

    public Creature(CreatureDTO data){
        clone(data);
    }

    public Creature(String name, String urlImg){
        this.name = name;
        this.urlImg = urlImg;
    }

    public void clone(CreatureDTO creatureDTO) {
        if(creatureDTO.getName() != null)this.name = creatureDTO.getName();
        if(creatureDTO.getBreed() != null)this.breed = new Breed(creatureDTO.getBreed());
        if(creatureDTO.getStatus() != null)this.status = new CreatureStatus(creatureDTO.getStatus());
        if(creatureDTO.getDescription() != null)this.description = new CreatureDescription(creatureDTO.getDescription());
        if(creatureDTO.getSkills() != null)
            this.skills = creatureDTO.getSkills().stream().map(CreatureSkill::new)
                .collect(Collectors.toList());
    }

    @Override
    public String toString(){
        return this.name;
    }
}
