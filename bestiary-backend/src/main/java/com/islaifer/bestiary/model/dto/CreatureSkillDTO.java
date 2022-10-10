package com.islaifer.bestiary.model.dto;

import com.islaifer.bestiary.model.entity.CreatureSkill;
import lombok.Data;

/**
 * DTO class for creature skills
 * @version 0.4.0
 * */
@Data
public class CreatureSkillDTO{

    private Long id;

    private String name;

    private String description;

    public CreatureSkillDTO(CreatureSkill data){
        if(data != null)clone(data);
    }

    private void clone(CreatureSkill creatureSkill) {
        this.id = creatureSkill.getId();
        this.name = creatureSkill.getName();
        this.description = creatureSkill.getDescription();
    }
}
