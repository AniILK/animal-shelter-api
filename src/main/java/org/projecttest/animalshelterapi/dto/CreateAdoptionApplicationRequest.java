package org.projecttest.animalshelterapi.dto;

import lombok.Data;
import org.projecttest.animalshelterapi.entity.Animal;

@Data
public class CreateAdoptionApplicationRequest {
    private Animal animal;
    private String name;
    private String email;
    private String phone;
    private String comment;
}
