package com.islaifer.bestiary.dao.repository;

import com.islaifer.bestiary.model.entity.Breed;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Breed repository
 * @version 0.2.0
 * */
@Repository
public interface BreedRepository extends CrudRepository<Breed, Long> {
    Optional<Breed> findByName(String name);
}
