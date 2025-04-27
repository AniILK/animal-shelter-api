package org.projecttest.animalshelterapi.converter;

import org.projecttest.animalshelterapi.dto.CreateAnimalRequest;
import org.projecttest.animalshelterapi.dto.GetAnimalResponse;
import org.projecttest.animalshelterapi.entity.Animal;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AnimalConverter {

    // Convert from Animal entity to GetAnimalResponse DTO
    public GetAnimalResponse entityToDto(Animal animal) {
        if (animal == null) {
            throw new IllegalArgumentException("Animal not found");
        }

        GetAnimalResponse response = new GetAnimalResponse();
        BeanUtils.copyProperties(animal, response);

        if (animal.getShelter() != null) {
            response.setShelter(animal.getShelter());
        }

        return response;
    }

    // Convert from CreateAnimalRequest DTO to Animal entity
    public Animal toEntity(CreateAnimalRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("Invalid animal creation request");
        }

        Animal animal = new Animal();
        BeanUtils.copyProperties(request, animal);
        return animal;
    }

    // Convert from a list of Animal DTOs to a list of Animal entities
    public List<Animal> toEntityList(List<CreateAnimalRequest> requests) {
        List<Animal> animals = new ArrayList<>();
        for (CreateAnimalRequest request : requests) {
            animals.add(toEntity(request));
        }
        return animals;
    }
}
