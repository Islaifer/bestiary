package com.islaifer.bestiary.model.entity;

import com.islaifer.bestiary.model.dto.CreatureDTO;
import lombok.Data;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Entity class for creature
 * @version 0.2.0
 * */
@Data
@Entity
public class Creature {
    @Id @GeneratedValue
    private Long id;

    private String name;

    @ManyToOne(cascade = CascadeType.ALL)
    private Breed breed;

    @OneToOne @JoinColumn
    private CreatureStatus status;

    @ManyToMany(cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<CreatureSkill> skills;

    @OneToOne @JoinColumn
    private CreatureDescription description;

    private String urlImg;

    public Creature(CreatureDTO data){
        clone(data);
    }

    public void clone(CreatureDTO creatureDTO) {
        this.id = creatureDTO.getId();
        this.name = creatureDTO.getName();
        this.breed = new Breed(creatureDTO.getBreed());
        this.status = new CreatureStatus(creatureDTO.getStatus());
        this.skills = creatureDTO.getSkills().stream().map(CreatureSkill::new)
                .collect(Collectors.toList());
    }
}
