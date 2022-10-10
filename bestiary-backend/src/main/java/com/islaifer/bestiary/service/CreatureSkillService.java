package com.islaifer.bestiary.service;

import com.islaifer.bestiary.dao.repository.CreatureSkillRepository;
import com.islaifer.bestiary.model.dto.CreatureSkillDTO;
import com.islaifer.bestiary.model.entity.CreatureSkill;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

/**
 * Service class to administrate the service rules for the CreatureSkills
 * @version 0.1.0
 * */
@Service
public class CreatureSkillService {

    private final CreatureSkillRepository creatureSkillRepository;

    private final Logger logger = LoggerFactory.getLogger(CreatureSkillService.class);

    @Autowired
    public CreatureSkillService(CreatureSkillRepository creatureSkillRepository){
        this.creatureSkillRepository = creatureSkillRepository;
    }

    /**
     * Method to catch all CreatureSkills
     * @return List<CreatureSkillDTO>
     * */
    public List<CreatureSkillDTO> getAll(){
        List<CreatureSkillDTO> results = new LinkedList<>();
        creatureSkillRepository.findAll().iterator()
                .forEachRemaining(e -> results.add(new CreatureSkillDTO(e)));
        logger.info("Search finished successfully");
        return results;
    }

    /**
     * Method used to save new CreatureSkill or update exist breed
     * @param data CreatureSkillDTO object that was saved or updated
     * */
    private void save(CreatureSkillDTO data){
        logger.debug("Start saving data!");
        CreatureSkill creatureSkill = get(data.getId());
        if(creatureSkill == null){
            logger.debug("Is a new Data, creating a new register!");
            creatureSkill = new CreatureSkill(data);
        }else{
            logger.debug("Data exists, updating data");
            creatureSkill.clone(data);
        }
        creatureSkillRepository.save(creatureSkill);
        logger.debug("Data was saved");
    }

    /**
     * Method to find exist data with id
     * @param id Long that will be used to find exist data
     * */
    private CreatureSkill get(Long id){
        return creatureSkillRepository.findById(id).orElse(null);
    }
}
