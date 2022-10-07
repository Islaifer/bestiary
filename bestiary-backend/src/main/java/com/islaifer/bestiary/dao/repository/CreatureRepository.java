package com.islaifer.bestiary.dao.repository;

import com.islaifer.bestiary.model.entity.Creature;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreatureRepository extends CrudRepository<Creature, Long> {
}
