package com.islaifer.bestiary.dao.repository;

import com.islaifer.bestiary.model.entity.Creature;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Breed repository
 * @version 0.2.0
 * */
@Repository
public interface CreatureRepository extends CrudRepository<Creature, Long> {
    Creature findByName(String name);
}
