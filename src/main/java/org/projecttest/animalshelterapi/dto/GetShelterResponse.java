package org.projecttest.animalshelterapi.dto;

import lombok.Data;

import java.time.Instant;

@Data
public class GetShelterResponse {
    private Long id;
    private String name;
    private String address;
    private String city;
    private String zipCode;
    private String phone;
    private String email;
    private Instant createdAt;
    private Instant updatedAt;
}
