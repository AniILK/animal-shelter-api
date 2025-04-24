package org.projecttest.animalshelterapi.dto;

import lombok.Data;
import org.projecttest.animalshelterapi.enums.AnimalStatus;

@Data
public class UpdateAnimalStatusRequest {
    private AnimalStatus status;
}
