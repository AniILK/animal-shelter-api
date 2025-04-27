package org.projecttest.animalshelterapi.dto;

import lombok.Data;
import org.projecttest.animalshelterapi.entity.Shelter;
import org.projecttest.animalshelterapi.enums.AnimalSpecies;
import org.projecttest.animalshelterapi.enums.AnimalStatus;

import java.time.Instant;

@Data
public class GetAnimalResponse {
    private Long id;
    private Shelter shelter;
    private String name;
    private AnimalSpecies species;
    private String breed;
    private int age;
    private String gender;
    private String description;
    private AnimalStatus status;
    private Instant createdAt;
    private Instant updatedAt;
}
