package org.projecttest.animalshelterapi.converter;

import lombok.RequiredArgsConstructor;
import org.projecttest.animalshelterapi.dto.CreateAdoptionApplicationRequest;
import org.projecttest.animalshelterapi.dto.GetAdoptionApplicationResponse;
import org.projecttest.animalshelterapi.entity.AdoptionApplication;

import org.projecttest.animalshelterapi.exceptions.ResourceNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class AdoptionApplicationConverter {

    // Convert from AdoptionApplication entity to GetAdoptionApplicationResponse DTO
    public GetAdoptionApplicationResponse entityToDto(AdoptionApplication application) {
        if (application == null) {
            throw new ResourceNotFoundException("Adoption application not found");
        }

        GetAdoptionApplicationResponse response = new GetAdoptionApplicationResponse();
        BeanUtils.copyProperties(application, response);

        // Set the animalId
        if (application.getAnimal() != null) {
            response.setAnimalId(application.getAnimal().getId());
        }

        return response;
    }

    // Convert from CreateAdoptionApplicationRequest DTO to AdoptionApplication entity
    public AdoptionApplication toEntity(CreateAdoptionApplicationRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("Invalid adoption application request");
        }

        AdoptionApplication application = new AdoptionApplication();
        BeanUtils.copyProperties(request, application);

        if (request.getAnimal() == null) {
            throw new ResourceNotFoundException("No animal provided in the adoption application request");
        }

        application.setAnimal(request.getAnimal());

        return application;
    }
}
