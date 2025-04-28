package org.projecttest.animalshelterapi.converter;

import org.projecttest.animalshelterapi.dto.CreateAnimalRequest;
import org.projecttest.animalshelterapi.dto.GetAnimalResponse;
import org.projecttest.animalshelterapi.entity.Animal;
import org.projecttest.animalshelterapi.enums.AnimalStatus;
import org.projecttest.animalshelterapi.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AnimalConverter {

    public GetAnimalResponse toDto(Animal animal) {
        if (animal == null) {
            throw new ResourceNotFoundException("Animal not found");
        }

        GetAnimalResponse dto = new GetAnimalResponse();
        dto.setId(animal.getId());
        dto.setShelterId(animal.getShelter().getId());
        dto.setName(animal.getName());
        dto.setSpecies(animal.getSpecies());
        dto.setBreed(animal.getBreed());
        dto.setAge(animal.getAge());
        dto.setGender(animal.getGender());
        dto.setDescription(animal.getDescription());
        dto.setStatus(animal.getStatus());

        if (animal.getCreatedAt() != null) {
            dto.setCreatedAt(animal.getCreatedAt().toInstant());
        }

        if (animal.getUpdatedAt() != null) {
            dto.setUpdatedAt(animal.getUpdatedAt().toInstant());
        }

        return dto;
    }

    public Animal toEntity(CreateAnimalRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("CreateAnimalRequest cannot be null");
        }

        Animal animal = new Animal();
        animal.setName(request.getName());
        animal.setSpecies(request.getSpecies());
        animal.setBreed(request.getBreed());
        animal.setAge(request.getAge());
        animal.setGender(request.getGender());
        animal.setDescription(request.getDescription());
        animal.setStatus(request.getStatus());

        return animal;
    }

    public List<GetAnimalResponse> toDtoList(List<Animal> animals) {
        return animals.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public void updateAnimalStatus(Animal animal, AnimalStatus newStatus) {
        if (animal == null) {
            throw new ResourceNotFoundException("Animal not found");
        }
        animal.setStatus(newStatus);
    }
}
