package com.islaifer.bestiary.controller;

import com.islaifer.bestiary.constants.MessagesConstants;
import com.islaifer.bestiary.exception.BreedException;
import com.islaifer.bestiary.model.dto.BreedDTO;
import com.islaifer.bestiary.service.BreedService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller class to administrate the calls of api about breed
 * @version 0.1.0
 * */
@RestController
@CrossOrigin
@RequestMapping("/bestiary/breed")
public class BreedController {
    private final BreedService breedService;

    private final Logger logger = LoggerFactory.getLogger(BreedController.class);

    @Autowired
    public BreedController(BreedService breedService){
        this.breedService = breedService;
    }

    /**
     * Method to get all breeds on api call
     * @return ResponseEntity<List<BreedDTO>>
     * */
    @GetMapping("/getAll")
    public ResponseEntity<List<BreedDTO>> getAll(){
        logger.debug("Start getting all breeds");
        return ResponseEntity.ok(breedService.getAll());
    }

    /**
     * Method to get a specific breed on api call
     * @param id Long that will be used in the search
     * @return ResponseEntity<BreedDTO>
     * */
    @GetMapping("/get/{id}")
    public ResponseEntity<BreedDTO> get(@PathVariable("id") Long id){
        return ResponseEntity.ok(breedService.getById(id));
    }

    /**
     * Method to post new breed on api call
     * @param breedDTO BreedDTO object that will be posted
     * @return ResponseEntity<String>
     * */
    @PostMapping("/post")
    public ResponseEntity<String> post(@RequestBody BreedDTO breedDTO){
        try {
            breedService.post(breedDTO);
            return ResponseEntity.ok(MessagesConstants.SUCCESS_POST.toString());
        } catch (BreedException e) {
            logger.error(e.getMessage());
            return ResponseEntity.badRequest().body(MessagesConstants.ERROR_POST.toString());
        }
    }

    /**
     * Method to update existent breed on api call
     * @param breedDTO BreedDTO Object that will be updated
     * @return ResponseEntity<String>
     * */
    @PutMapping("/put")
    public ResponseEntity<String> put(@RequestBody BreedDTO breedDTO){
        try {
            breedService.update(breedDTO);
            return ResponseEntity.ok(MessagesConstants.SUCCESS_UPDATE.toString());
        } catch (BreedException e) {
            logger.error(e.getMessage());
            return ResponseEntity.badRequest().body(MessagesConstants.ERROR_UPDATE.toString());
        }
    }

    /**
     * Method to delete existent breed on api call
     * @param breedDTO BreedDTO object that will be deleted
     * @return ResponseEntity<String>
     * */
    @DeleteMapping("/delete")
    public ResponseEntity<String> delete(@RequestBody BreedDTO breedDTO){
        try {
            breedService.delete(breedDTO);
            return ResponseEntity.ok(MessagesConstants.SUCCESS_DELETE.toString());
        } catch (BreedException e) {
            logger.error(e.getMessage());
            return ResponseEntity.badRequest().body(MessagesConstants.ERROR_DELETE.toString());
        }
    }
}
