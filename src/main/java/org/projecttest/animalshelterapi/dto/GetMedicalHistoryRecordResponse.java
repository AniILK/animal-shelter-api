package org.projecttest.animalshelterapi.dto;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class GetMedicalHistoryRecordResponse {

    private Long id;
    private Long animalId;
    private String animalName;
    private String animalSpecies;
    private String animalBreed;
    private String animalGender;
    private int animalAge;
    private String description;
    private String veterinarian;
    private Timestamp updatedAt;
    private Timestamp createdAt;
}
