package com.islaifer.bestiary.dao.repository;

import com.islaifer.bestiary.model.entity.Creature;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Creature repository
 * @version 0.3.0
 * */
@Repository
public interface CreatureRepository extends CrudRepository<Creature, Long> {
    Optional<Creature> findByName(String name);
}
