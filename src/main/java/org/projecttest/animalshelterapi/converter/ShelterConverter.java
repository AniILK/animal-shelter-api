package org.projecttest.animalshelterapi.converter;

import org.projecttest.animalshelterapi.dto.CreateShelterRequest;
import org.projecttest.animalshelterapi.dto.GetShelterResponse;
import org.projecttest.animalshelterapi.entity.Shelter;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ShelterConverter {

    // Convert from Shelter entity to GetShelterResponse DTO
    public GetShelterResponse entityToDto(Shelter shelter) {
        if (shelter == null) {
            throw new IllegalArgumentException("Shelter not found");
        }

        GetShelterResponse response = new GetShelterResponse();
        BeanUtils.copyProperties(shelter, response);
        return response;
    }

    // Convert from CreateShelterRequest DTO to Shelter entity
    public Shelter toEntity(CreateShelterRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("Invalid shelter creation request");
        }

        Shelter shelter = new Shelter();
        BeanUtils.copyProperties(request, shelter);
        return shelter;
    }

    // Convert from a list of Shelter DTOs to a list of Shelter entities
    public List<Shelter> toEntityList(List<CreateShelterRequest> requests) {
        List<Shelter> shelters = new ArrayList<>();
        for (CreateShelterRequest request : requests) {
            shelters.add(toEntity(request));
        }
        return shelters;
    }
}
