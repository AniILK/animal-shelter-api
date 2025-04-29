package org.projecttest.animalshelterapi.service;

import lombok.RequiredArgsConstructor;
import org.projecttest.animalshelterapi.entity.Animal;
import org.projecttest.animalshelterapi.entity.Shelter;
import org.projecttest.animalshelterapi.enums.AnimalSpecies;

import org.projecttest.animalshelterapi.exceptions.ResourceNotFoundException;
import org.projecttest.animalshelterapi.repository.AnimalRepository;
import org.projecttest.animalshelterapi.repository.ShelterRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AnimalService {

    private final AnimalRepository animalRepository;
    private final ShelterRepository shelterRepository;

    public Page<Animal> findAllAnimals(Pageable pageable) {
        return animalRepository.findAll(pageable);
    }

    public Page<Animal> findBySpecies(String species, Pageable pageable) {
        return animalRepository.findBySpecies(species, pageable);
    }

    public Animal createAnimal(Animal animal, Long shelterId) {
        Shelter shelter = shelterRepository.findById(shelterId)
                .orElseThrow(() -> new ResourceNotFoundException(STR."Shelter with id \{shelterId} not found"));
        animal.setShelter(shelter);
        return animalRepository.save(animal);
    }

    public Animal findAnimalById(Long id) {
        return animalRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(STR."Animal with id \{id} not found"));
    }

    public Animal updateAnimal(Long id, Animal updatedAnimal) {
        Animal animal = findAnimalById(id);
        animal.setName(updatedAnimal.getName());
        animal.setSpecies(updatedAnimal.getSpecies());
        animal.setBreed(updatedAnimal.getBreed());
        animal.setAge(updatedAnimal.getAge());
        animal.setGender(updatedAnimal.getGender());
        animal.setDescription(updatedAnimal.getDescription());
        animal.setStatus(updatedAnimal.getStatus());
        return animalRepository.save(animal);
    }

    public Animal partialUpdateAnimal(Long id, Animal updatedAnimal) {
        Animal animal = findAnimalById(id);

        if (updatedAnimal.getName() != null) animal.setName(updatedAnimal.getName());
        if (updatedAnimal.getSpecies() != null) animal.setSpecies(updatedAnimal.getSpecies());
        if (updatedAnimal.getBreed() != null) animal.setBreed(updatedAnimal.getBreed());
        if (updatedAnimal.getAge() != 0) animal.setAge(updatedAnimal.getAge());
        if (updatedAnimal.getGender() != null) animal.setGender(updatedAnimal.getGender());
        if (updatedAnimal.getDescription() != null) animal.setDescription(updatedAnimal.getDescription());
        if (updatedAnimal.getStatus() != null) animal.setStatus(updatedAnimal.getStatus());

        return animalRepository.save(animal);
    }



    public void deleteAnimal(Long id) {
        Animal animal = findAnimalById(id);
        animalRepository.delete(animal);
    }
}
