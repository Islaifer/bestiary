package com.islaifer.bestiary.dao.repository;

import com.islaifer.bestiary.model.entity.CreatureSkill;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreatureSkillRepository extends CrudRepository<CreatureSkill, Long> {
}
