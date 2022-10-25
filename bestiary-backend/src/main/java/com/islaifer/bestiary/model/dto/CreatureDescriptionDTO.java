package com.islaifer.bestiary.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO class for creatures description
 * @version 0.5.0
 * */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreatureDescriptionDTO{

    private Long id;

    private String appearance;

    private String behavior;

    private String habitat;

    private String warning;

    private String obs;
}
