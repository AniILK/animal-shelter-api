package org.projecttest.animalshelterapi.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class CreateMedicalHistoryRecordRequest {
    private Long animalId;
    private String description;
    private String veterinarian;
}
