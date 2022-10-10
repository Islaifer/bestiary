package com.islaifer.bestiary.model.entity;

import com.islaifer.bestiary.model.dto.CreatureSkillDTO;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Entity class for creature skills
 * @version 0.3.0
 * */
@Data
@Entity
public class CreatureSkill {
    @Id @GeneratedValue
    private Long id;

    private String name;

    private String description;

    public CreatureSkill(CreatureSkillDTO data){
        clone(data);
    }

    public void clone(CreatureSkillDTO creatureSkillDTO) {
        this.name = creatureSkillDTO.getName();
        this.description = creatureSkillDTO.getDescription();
    }
}
