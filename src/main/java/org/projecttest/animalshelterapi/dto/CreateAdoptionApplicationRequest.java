package org.projecttest.animalshelterapi.dto;

import lombok.Data;

@Data
public class CreateAdoptionApplicationRequest {
    private Long animalId;
    private String name;
    private String email;
    private String phone;
    private String comment;
}
