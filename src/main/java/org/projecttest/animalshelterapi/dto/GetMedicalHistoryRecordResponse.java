package org.projecttest.animalshelterapi.dto;

import jakarta.persistence.*;
import lombok.Data;
import org.projecttest.animalshelterapi.entity.Animal;

import java.sql.Timestamp;

@Data
public class GetMedicalHistoryRecordResponse {

    private Long id;
    private Long animalId;
    private String animalName;
    private String animalSpecies;
    private String description;
    private String veterinarian;
    private Timestamp updatedAt;
    private Timestamp createdAt;
}
