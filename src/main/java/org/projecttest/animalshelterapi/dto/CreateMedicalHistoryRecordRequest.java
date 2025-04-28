package org.projecttest.animalshelterapi.dto;

import lombok.Data;


@Data
public class CreateMedicalHistoryRecordRequest {
    private Long animalId;
    private String description;
    private String veterinarian;
}
