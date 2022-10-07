package com.islaifer.bestiary.dao.repository;

import com.islaifer.bestiary.model.entity.CreatureStatus;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Breed repository
 * @version 1.0.0
 * */
@Repository
public interface CreatureStatusRepository extends CrudRepository<CreatureStatus, Long> {
}
