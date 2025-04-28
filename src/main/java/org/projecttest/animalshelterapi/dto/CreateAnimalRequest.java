package org.projecttest.animalshelterapi.dto;

import lombok.Data;
import org.projecttest.animalshelterapi.enums.AnimalSpecies;
import org.projecttest.animalshelterapi.enums.AnimalStatus;

@Data
public class CreateAnimalRequest {
    private Long shelterId;
    private String name;
    private AnimalSpecies species;
    private String breed;
    private int age;
    private String gender;
    private String description;
    private AnimalStatus status;
}
