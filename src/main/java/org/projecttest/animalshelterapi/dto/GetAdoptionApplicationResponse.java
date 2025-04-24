package org.projecttest.animalshelterapi.dto;

import lombok.Data;
import org.projecttest.animalshelterapi.enums.ApplicationStatus;

import java.time.Instant;

@Data
public class GetAdoptionApplicationResponse {
    private Long id;
    private Long animalId;
    private String name;
    private String email;
    private String phone;
    private ApplicationStatus status;
    private String comment;
    private Instant createdAt;
}
