package com.islaifer.bestiary.model.entity;

import lombok.Data;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;

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
}
