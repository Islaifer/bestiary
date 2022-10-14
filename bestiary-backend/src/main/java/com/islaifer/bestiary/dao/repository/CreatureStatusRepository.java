package com.islaifer.bestiary.dao.repository;

import com.islaifer.bestiary.model.entity.CreatureStatus;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * CreatureStatus repository
 * @version 0.1.0
 * */
@Repository
public interface CreatureStatusRepository extends CrudRepository<CreatureStatus, Long> {
}
