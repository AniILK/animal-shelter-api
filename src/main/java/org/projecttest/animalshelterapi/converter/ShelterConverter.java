package org.projecttest.animalshelterapi.converter;

import lombok.RequiredArgsConstructor;
import org.projecttest.animalshelterapi.dto.CreateShelterRequest;
import org.projecttest.animalshelterapi.dto.GetAnimalResponse;
import org.projecttest.animalshelterapi.dto.GetShelterResponse;
import org.projecttest.animalshelterapi.entity.Shelter;

import org.projecttest.animalshelterapi.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ShelterConverter {

    private final AnimalConverter animalConverter;

    public GetShelterResponse entityToDto(Shelter shelter) {
        if (shelter == null) {
            throw new ResourceNotFoundException("Shelter not found");
        }

        GetShelterResponse dto = new GetShelterResponse();
        dto.setId(shelter.getId());
        dto.setName(shelter.getName());
        dto.setAddress(shelter.getAddress());
        dto.setCity(shelter.getCity());
        dto.setZipCode(shelter.getZipCode());
        dto.setPhone(shelter.getPhone());
        dto.setEmail(shelter.getEmail());

        if (shelter.getCreatedAt() != null) {
            dto.setCreatedAt(shelter.getCreatedAt().toInstant());
        }

        if (shelter.getUpdatedAt() != null) {
            dto.setUpdatedAt(shelter.getUpdatedAt().toInstant());
        }

        if (shelter.getAnimals() != null) {
            List<GetAnimalResponse> animals = shelter.getAnimals().stream()
                    .map(animalConverter::entityToDto)
                    .collect(Collectors.toList());
            dto.setAnimals(animals);
        }

        return dto;
    }

    public Shelter dtoToEntity(CreateShelterRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("CreateShelterRequest cannot be null");
        }

        Shelter shelter = new Shelter();
        shelter.setName(request.getName());
        shelter.setAddress(request.getAddress());
        shelter.setCity(request.getCity());
        shelter.setZipCode(request.getZipCode());
        shelter.setPhone(request.getPhone());
        shelter.setEmail(request.getEmail());

        return shelter;
    }
}
