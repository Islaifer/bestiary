package com.islaifer.bestiary.model.dto;

import com.islaifer.bestiary.model.entity.CreatureSkill;
import lombok.Data;

/**
 * DTO class for creature skills
 * @version 0.1.0
 * */
@Data
public class CreatureSkillDTO{

    private Long id;

    private String name;

    private String description;

    public CreatureSkillDTO(CreatureSkill data){
        clone(data);
    }

    public void clone(Object data) {
        if(data instanceof  CreatureSkill creatureSkill){
            this.id = creatureSkill.getId();
            this.name = creatureSkill.getName();
            this.description = creatureSkill.getDescription();
        }
    }
}
