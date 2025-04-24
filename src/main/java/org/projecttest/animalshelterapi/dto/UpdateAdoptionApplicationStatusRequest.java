package org.projecttest.animalshelterapi.dto;

import lombok.Data;
import org.projecttest.animalshelterapi.enums.ApplicationStatus;

@Data
public class UpdateAdoptionApplicationStatusRequest {
    private ApplicationStatus status;
}
