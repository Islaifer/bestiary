package com.islaifer.bestiary.service;

import com.islaifer.bestiary.dao.repository.*;
import com.islaifer.bestiary.exception.CreatureException;
import com.islaifer.bestiary.model.dto.BreedDTO;
import com.islaifer.bestiary.model.dto.CreatureDTO;
import com.islaifer.bestiary.model.dto.CreatureSkillDTO;
import com.islaifer.bestiary.model.entity.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

/**
 * Service class to administrate the service rules for the Creatures
 * @version 0.1.0
 * */
@Service
public class CreatureService {
    private final Logger logger = LoggerFactory.getLogger(CreatureService.class);

    private final CreatureRepository creatureRepository;

    private final CreatureDescriptionRepository creatureDescriptionRepository;

    private final CreatureStatusRepository creatureStatusRepository;

    private final BreedRepository breedRepository;

    private final BreedService breedService;

    private final CreatureSkillRepository creatureSkillRepository;

    @Autowired
    public CreatureService(CreatureRepository creatureRepository,
                           CreatureDescriptionRepository creatureDescriptionRepository,
                           CreatureStatusRepository creatureStatusRepository,
                           BreedRepository breedRepository, BreedService breedService,
                           CreatureSkillRepository creatureSkillRepository){
        this.creatureRepository = creatureRepository;
        this.creatureDescriptionRepository = creatureDescriptionRepository;
        this.creatureStatusRepository = creatureStatusRepository;
        this.breedRepository = breedRepository;
        this.breedService = breedService;
        this.creatureSkillRepository = creatureSkillRepository;
    }

    /**
     * Method to get all creatures
     * @return List<CreatureDTO>
     * */
    public List<CreatureDTO> getAll(){
        List<CreatureDTO> list = new LinkedList<>();
        creatureRepository.findAll().iterator().forEachRemaining(e -> list.add(new CreatureDTO(e)));
        return list;
    }

    /**
     * Method to get a specific creature by id
     * @param id Long that will be used to find creature
     * @return CreatureDTO
     * */
    public CreatureDTO getById(Long id){
        return new CreatureDTO(get(id));
    }

    /**
     * Method to save new Creature
     * @param creatureDTO CreatureDTO object who will be saved
     * @throws CreatureException Exception if any error occurred
     * */
    public void post(CreatureDTO creatureDTO) throws CreatureException {
        Creature creature;
        verifyCreature(creatureDTO);
        creature = new Creature(creatureDTO.getName(), creatureDTO.getUrlImg());
        configCreature(creature, creatureDTO);
        save(creature);
    }

    /**
     * Method to update exist Creature
     * @param creatureDTO CreatureDTO object who will be updated
     * @throws CreatureException Exception if any error occurred
     * */
    public void update(CreatureDTO creatureDTO) throws CreatureException {
        Creature creature;
        verifyCreature(creatureDTO);
        creature = get(creatureDTO.getId());
        if(creature == null) throw new CreatureException("Creature doesn't exist");
        creature.clone(creatureDTO);
        configCreature(creature, creatureDTO);
        save(creature);
    }

    /**
     * Method to delete exist Creature
     * @param creatureDTO CreatureDTO object who will be removed
     * @throws CreatureException Exception if any error occurred
     * */
    public void delete(CreatureDTO creatureDTO) throws CreatureException {
        CreatureStatus creatureStatus;
        CreatureDescription creatureDescription;
        Creature creature = get(creatureDTO.getId());
        if(creature == null) throw new CreatureException("Creature doesn't exist");
        creatureDescription = creature.getDescription();
        creatureStatus = creature.getStatus();
        creatureRepository.delete(creature);
        creatureDescriptionRepository.delete(creatureDescription);
        creatureStatusRepository.delete(creatureStatus);
    }

    /**
     * Method to get or create new Breed
     * @param breedDTO BreedDTO object who will be used to search or create
     * @return Breed
     * @throws CreatureException Exception if any error occurred
     * */
    private Breed getBreed(BreedDTO breedDTO) throws CreatureException {
        Breed breed = getBreed(breedDTO.getId());
        if(breed == null){
            breed = createBreed(new Breed(breedDTO));
        }
        return breed;
    }

    /**
     * Method to get or create new Skills
     * @param listDTO A list contain the CreaturesSkills
     * @return List<CreatureSkill>
     * @throws CreatureException Exception if any error occurred
     * */
    private List<CreatureSkill> getSkills(List<CreatureSkillDTO> listDTO) throws CreatureException {
        List<CreatureSkill> list = new LinkedList<>();
        CreatureSkill creatureSkill;
        for(CreatureSkillDTO creatureSkillDTO : listDTO){
            creatureSkill = getCreatureSkill(creatureSkillDTO.getId());
            if(creatureSkill == null){
                creatureSkill = createCreatureSkill(new CreatureSkill(creatureSkillDTO));
            }
            list.add(creatureSkill);
        }
        return list;
    }

    /**
     * Method to get or create new creature status
     * @param creatureDTO CreatureDTO object that will be used into queries
     * @return CreatureStatus
     * @throws CreatureException Exception if any error occurred
     * */
    private CreatureStatus getCreatureStatus(CreatureDTO creatureDTO) throws CreatureException {
        CreatureStatus creatureStatus;
        Creature creature = get(creatureDTO.getId());
        if(creature == null) return createStatus(new CreatureStatus(creatureDTO.getStatus()));
        creatureStatus = creature.getStatus();
        creatureStatus.clone(creatureDTO.getStatus());
        save(creatureStatus);
        return creatureStatus;
    }

    /**
     * Method to create a new status for the creature
     * @param creatureStatus CreatureStatus object who will be created
     * @return CreatureStatus
     * @throws CreatureException Exception if any error occurred
     * */
    private CreatureStatus createStatus(CreatureStatus creatureStatus) throws CreatureException {
        if(creatureStatus.getTrend() == null || creatureStatus.getTrend().isEmpty())
            throw new CreatureException("Trend is empty");
        save(creatureStatus);
        return creatureStatus;
    }

    /**
     * Method to get or create a new creature description
     * @param creatureDTO CreatureDTO object that will ne used into queries
     * @return CreatureDescription
     * @throws CreatureException Exception if any error occurred
     * */
    private CreatureDescription getCreatureDescription(CreatureDTO creatureDTO) throws CreatureException {
        CreatureDescription creatureDescription;
        Creature creature = get(creatureDTO.getId());
        if(creature == null) return createDescription(new CreatureDescription(creatureDTO.getDescription()));
        creatureDescription = creature.getDescription();
        creatureDescription.clone(creatureDTO.getDescription());
        save(creatureDescription);
        return creatureDescription;
    }

    /**
     * Method to create a new description for the creature
     * @param creatureDescription CreatureDescription object who will be created
     * @return CreatureDescription
     * @throws CreatureException Exception if any error occurred
     * */
    private CreatureDescription createDescription(CreatureDescription creatureDescription) throws CreatureException {
        save(creatureDescription);
        return creatureDescription;
    }

    /**
     * Method to create new Breed
     * @param breed Breed object who will be created
     * @return Breed
     * @throws CreatureException Exception if any error occurred
     * */
    private Breed createBreed(Breed breed) throws CreatureException {
        try {
            breedService.verifyBreed(new BreedDTO(breed));
            save(breed);
            return breed;
        } catch (Exception e) {
            throw new CreatureException(e.getMessage());
        }
    }

    /**
     * Method to get exist breed by id
     * @param id Long that will be used into the query
     * @return Breed
     * */
    private Breed getBreed(Long id){
        return breedRepository.findById(id).orElse(null);
    }

    /**
     * Method to create new Skill
     * @param creatureSkill CreatureSkill object who will be created
     * @return CreatureSkill
     * @throws CreatureException Exception if any error occurred
     * */
    private CreatureSkill createCreatureSkill(CreatureSkill creatureSkill) throws CreatureException {
        save(creatureSkill);
        return creatureSkill;
    }

    /**
     * Method to get exist creature skill
     * @param id Long that will be used into the query
     * @return CreatureSkill
     * */
    private CreatureSkill getCreatureSkill(Long id){
        return creatureSkillRepository.findById(id).orElse(null);
    }

    /**
     * Method to save any object
     * @param data Object who will be saved
     * @throws CreatureException Exception if any error occurred
     * */
    private void save(Object data) throws CreatureException {
        try{
            if(data instanceof Creature creature) creatureRepository.save(creature);
            if(data instanceof CreatureStatus creatureStatus) creatureStatusRepository.save(creatureStatus);
            if(data instanceof CreatureDescription creatureDescription) creatureDescriptionRepository.save(creatureDescription);
            if(data instanceof Breed breed) breedRepository.save(breed);
            if(data instanceof CreatureSkill creatureSkill) creatureSkillRepository.save(creatureSkill);
        }catch (Exception e){
            logger.error("Error on save data!");
            throw new CreatureException(e.getMessage());
        }
    }

    /**
     * Method to get exist Creature by name
     * @param name String that will be used into query
     * @return Creature
     * */
    private Creature getByName(String name){
        return get(name);
    }

    /**
     * Method to get exist Creature by name or id
     * @param dataSearch Object that will be used into query, can be Long or String
     * @return Creature
     * */
    private Creature get(Object dataSearch){
        if(dataSearch instanceof Long id) return creatureRepository.findById(id).orElse(null);
        else if(dataSearch instanceof String name) return creatureRepository.findByName(name).orElse(null);
        return null;
    }

    /**
     * Method to verify call Creature camps
     * @param creatureDTO CreatureDTO object who will be checked
     * @throws CreatureException Exception if any error occurred
     * */
    private void verifyCreature(CreatureDTO creatureDTO) throws CreatureException {
        if(creatureDTO.getName() == null || creatureDTO.getName().isEmpty()) throw new CreatureException("Name is empty");
        if(getByName(creatureDTO.getName()) != null) throw new CreatureException("Name already exist");
        if(creatureDTO.getStatus() == null) throw new CreatureException("Status is empty");
        if(creatureDTO.getBreed() == null) throw new CreatureException("Breed is empty");
        if(creatureDTO.getDescription() == null) throw new CreatureException("Description is empty");
    }

    /**
     * Method to config the Creature
     * @param creature Creature object who will be configured
     * @param creatureDTO CreatureDTO object who will be used into queries
     * @throws CreatureException Exception if any error occurred
     * */
    private void configCreature(Creature creature, CreatureDTO creatureDTO) throws CreatureException {
        Breed breed;
        CreatureStatus creatureStatus;
        CreatureDescription creatureDescription;
        List<CreatureSkill> creatureSkills;
        breed = getBreed(creatureDTO.getBreed());
        creatureStatus = getCreatureStatus(creatureDTO);
        creatureDescription = getCreatureDescription(creatureDTO);
        creatureSkills = getSkills(creatureDTO.getSkills());
        creature.setBreed(breed);
        creature.setStatus(creatureStatus);
        creature.setDescription(creatureDescription);
        creature.setSkills(creatureSkills);
    }
}
