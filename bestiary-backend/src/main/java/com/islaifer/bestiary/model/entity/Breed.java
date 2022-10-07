package com.islaifer.bestiary.model.entity;

import lombok.Data;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;

/**
 * Entity class for creature races
 * @version 1.0.0
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
}
