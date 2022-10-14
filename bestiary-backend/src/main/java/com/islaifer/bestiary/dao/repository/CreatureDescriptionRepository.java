package com.islaifer.bestiary.dao.repository;

import com.islaifer.bestiary.model.entity.CreatureDescription;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * CreatureDescription repository
 * @version 0.1.0
 * */
@Repository
public interface CreatureDescriptionRepository extends CrudRepository<CreatureDescription, Long> {
}
