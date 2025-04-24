package org.projecttest.animalshelterapi.dto;

import lombok.Data;

@Data
public class CreateShelterRequest {
    private String name;
    private String address;
    private String city;
    private String zipCode;
    private String phone;
    private String email;
}
