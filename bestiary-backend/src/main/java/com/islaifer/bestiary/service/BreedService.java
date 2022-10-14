package com.islaifer.bestiary.service;

import com.islaifer.bestiary.dao.repository.BreedRepository;
import com.islaifer.bestiary.exception.BreedException;
import com.islaifer.bestiary.model.dto.BreedDTO;
import com.islaifer.bestiary.model.entity.Breed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

/**
 * Service class to administrate the service rules for the Breeds
 * @version 0.2.0
 * */
@Service
public class BreedService {

    private final BreedRepository breedRepository;

    private final Logger logger = LoggerFactory.getLogger(BreedService.class);

    @Autowired
    public BreedService(BreedRepository breedRepository){
        this.breedRepository = breedRepository;
    }

    /**
     * Method to get all exist data
     * @return List<BreedDTO>
     * */
    public List<BreedDTO> getAll(){
        List<BreedDTO> results = new LinkedList<>();
        breedRepository.findAll().iterator().forEachRemaining(e -> results.add(new BreedDTO(e)));
        return results;
    }

    /**
     * Method to get exist data by id
     * @param id Long id that will use to search
     * @return BreedDTO
     * */
    public BreedDTO getById(Long id){
        return new BreedDTO(get(id));
    }

    /**
     * Method to post a new breed
     * @param data BreedDTO object that will post
     * @throws BreedException Exception if any error occurred
     * */
    public void post(BreedDTO data) throws BreedException {
        verifyBreed(data);
        try{
            save(data);
            logger.info("Data was posted!");
        }catch (Exception e){
            logger.error("Data wasn't posted!");
            throw new BreedException(e.getMessage());
        }
    }

    /**
     * Method to verify all camps of breed
     * @param data BreedDTO object that will be used to verify the camps
     * @throws BreedException Exception if any error occurred
     * */
    public void verifyBreed(BreedDTO data) throws BreedException {
        if(data.getName() == null || data.getName().isEmpty())
            throw new BreedException("Name is required!");
        if(data.getDescription() == null || data.getDescription().isEmpty())
            throw new BreedException("Description is required!");
        if(getByName(data.getName()).getName() != null && !getByName(data.getName()).getName().isEmpty())
            throw new BreedException("Name already exist!");
    }

    /**
     * Method to update exist breed
     * @param data BreedDTO object that will update
     * @throws BreedException Exception if any error occurred
     * */
    public void update(BreedDTO data) throws BreedException {
        try{
            save(data);
            logger.info("Data was updated!");
        }catch (Exception e){
            logger.error("Data wasn't updated!");
            throw new BreedException(e.getMessage());
        }
    }

    /**
     * Method to delete a exist data
     * @param data BreedDTO object that will update
     * @throws BreedException Exception if any error occurred
     * */
    public void delete(BreedDTO data) throws BreedException{
        Breed breed = get(data.getId());
        if(breed == null) throw  new BreedException("Data don't exist");
        //Create a logic to delete all creatures first
        breedRepository.delete(breed);
    }

    /**
     * Method used to save new Breed or update exist breed
     * @param data BreedDTO object that was saved or updated
     * */
    private void save(BreedDTO data){
        logger.debug("Start saving data!");
        Breed breed = get(data.getId());
        if(breed == null){
            logger.debug("Is a new Data, creating a new register!");
            breed = new Breed(data);
        }else{
            logger.debug("Data exists, updating data");
            breed.clone(data);
        }
        breedRepository.save(breed);
        logger.debug("Data was saved");
    }

    /**
     * Method to find exist data by name
     * @param name String that will be used to search data
     * @return BreedDTO
     * */
    private BreedDTO getByName(String name){
        return new BreedDTO(get(name));
    }

    /**
     * Method used to find any data by id
     * @param dataSearch Object that will use to search (can be the name or the id)
     * @return Breed
     * */
    private Breed get(Object dataSearch){
        if(dataSearch instanceof Long id) return breedRepository.findById(id).orElse(null);
        else if(dataSearch instanceof String name) return breedRepository.findByName(name).orElse(null);
        return null;
    }
}
