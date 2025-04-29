package org.projecttest.animalshelterapi.converter;

import org.projecttest.animalshelterapi.dto.CreateAdoptionApplicationRequest;
import org.projecttest.animalshelterapi.dto.GetAdoptionApplicationResponse;
import org.projecttest.animalshelterapi.entity.AdoptionApplication;
import org.projecttest.animalshelterapi.enums.ApplicationStatus;

import org.projecttest.animalshelterapi.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AdoptionApplicationConverter {

    public GetAdoptionApplicationResponse entityToDto(AdoptionApplication application) {
        if (application == null) {
            throw new ResourceNotFoundException("AdoptionApplication not found");
        }

        GetAdoptionApplicationResponse dto = new GetAdoptionApplicationResponse();
        dto.setId(application.getId());
        dto.setAnimalId(application.getAnimal().getId());
        dto.setName(application.getName());
        dto.setEmail(application.getEmail());
        dto.setPhone(application.getPhone());
        dto.setComment(application.getComment());
        dto.setStatus(application.getStatus());
        dto.setCreatedAt(application.getCreatedAt().toInstant());

        return dto;
    }

    public AdoptionApplication dtoToEntity(CreateAdoptionApplicationRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("CreateAdoptionApplicationRequest cannot be null");
        }

        AdoptionApplication application = new AdoptionApplication();
        application.setName(request.getName());
        application.setEmail(request.getEmail());
        application.setPhone(request.getPhone());
        application.setComment(request.getComment());
        application.setStatus(ApplicationStatus.PENDING);

        return application;
    }

    public void updateApplicationStatus(AdoptionApplication application, ApplicationStatus newStatus) {
        if (application == null) {
            throw new ResourceNotFoundException("AdoptionApplication not found");
        }
        application.setStatus(newStatus);
    }
}
