package com.islaifer.bestiary.model.entity;

import com.islaifer.bestiary.model.dto.CreatureSkillDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Entity class for creature skills
 * @version 0.5.0
 * */
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class CreatureSkill {
    @Id @GeneratedValue
    private Long id;

    private String name;

    private String description;

    public CreatureSkill(CreatureSkillDTO data){
        clone(data);
    }

    public void clone(CreatureSkillDTO creatureSkillDTO) {
        if(creatureSkillDTO.getName() != null)this.name = creatureSkillDTO.getName();
        if(creatureSkillDTO.getDescription() != null)this.description = creatureSkillDTO.getDescription();
    }
}
