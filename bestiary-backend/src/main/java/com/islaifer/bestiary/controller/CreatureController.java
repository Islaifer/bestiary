package com.islaifer.bestiary.controller;

import com.islaifer.bestiary.constants.MessagesConstants;
import com.islaifer.bestiary.exception.CreatureException;
import com.islaifer.bestiary.model.dto.CreatureDTO;
import com.islaifer.bestiary.service.CreatureService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller class to administrate the calls of api about creature
 * @version 0.1.0
 * */
@RestController
@CrossOrigin
@RequestMapping("/bestiary/creature")
public class CreatureController {

    private final CreatureService creatureService;

    private final Logger logger = LoggerFactory.getLogger(CreatureController.class);

    @Autowired
    public CreatureController(CreatureService creatureService) {
        this.creatureService = creatureService;
    }

    /**
     * Method to get all creatures by api call
     * @return ResponseEntity<List<CreatureDTO>>
     * */
    @GetMapping("/getAll")
    public ResponseEntity<List<CreatureDTO>> getAll(){
        return ResponseEntity.ok(creatureService.getAll());
    }

    /**
     * Method to get specific creature by id on api call
     * @param id Long id that will be used to search
     * @return ResponseEntity<CreatureDTO>
     * */
    @GetMapping("/get/{id}")
    public ResponseEntity<CreatureDTO> getById(@PathVariable("id") Long id){
        return ResponseEntity.ok(creatureService.getById(id));
    }

    /**
     * Method to post a new creature by api call
     * @param creatureDTO CreatureDTO object that will be posted
     * @return ResponseEntity<String>
     * */
    @PostMapping("/post")
    public ResponseEntity<String> post(@RequestBody CreatureDTO creatureDTO){
        try {
            creatureService.post(creatureDTO);
            return ResponseEntity.ok(MessagesConstants.SUCCESS_POST.toString());
        } catch (CreatureException e) {
            logger.error(e.getMessage());
            return ResponseEntity.badRequest().body(MessagesConstants.ERROR_POST.toString());
        }
    }

    /**
     * Method to update exist creature by api call
     * @param creatureDTO  CreatureDTO object that will be updated
     * @return ResponseEntity<String>
     * */
    @PutMapping("/put")
    public ResponseEntity<String> put(@RequestBody CreatureDTO creatureDTO){
        try {
            creatureService.update(creatureDTO);
            return ResponseEntity.ok(MessagesConstants.SUCCESS_UPDATE.toString());
        } catch (CreatureException e) {
            logger.error(e.getMessage());
            return ResponseEntity.badRequest().body(MessagesConstants.ERROR_UPDATE.toString());
        }
    }

    /**
     * Method to delete exist creature by api call
     * @param creatureDTO CreatureDTO object who will be deleted
     * @return ResponseEntity<String>
     * */
    @DeleteMapping("/delete")
    public ResponseEntity<String> delete(@RequestBody CreatureDTO creatureDTO){
        try {
            creatureService.delete(creatureDTO);
            return ResponseEntity.ok(MessagesConstants.SUCCESS_DELETE.toString());
        } catch (CreatureException e) {
            logger.error(e.getMessage());
            return ResponseEntity.badRequest().body(MessagesConstants.ERROR_DELETE.toString());
        }
    }
}
