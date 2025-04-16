package org.projecttest.animalshelterapi.service;

import lombok.RequiredArgsConstructor;
import org.projecttest.animalshelterapi.entity.AdoptionApplication;
import org.projecttest.animalshelterapi.entity.Animal;
import org.projecttest.animalshelterapi.entity.MedicalHistoryRecord;
import org.projecttest.animalshelterapi.enums.AnimalSpecies;
import org.projecttest.animalshelterapi.enums.AnimalStatus;
import org.projecttest.animalshelterapi.enums.ApplicationStatus;
import org.projecttest.animalshelterapi.exceptions.ResourceNotFoundException;
import org.projecttest.animalshelterapi.repository.AnimalRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class AnimalService {

    private final AnimalRepository animalRepository;
    private final MedicalHistoryRecordService medicalHistoryRecordService;

//  Create or update animal.
    public Animal saveAnimal(Animal animal) {
        return animalRepository.save(animal);
    }

//  Get all animals, throw an exception, if none are found.
    public List<Animal> getAllAnimals() {
        List<Animal> animals = animalRepository.findAll();
        if (animals.isEmpty()) {
            throw new ResourceNotFoundException("No animals found");
        }
        return animals;
    }

//  Get animal by id and throw an exception if none are found
    public Animal getAnimalById(Long animalId) {
        return animalRepository.findById(animalId)
                .orElseThrow(() -> new ResourceNotFoundException(STR."Animal with id \{animalId} not found"));
    }

//  Get animals by species and throw an exception, if none are found.
    public List<Animal> getAnimalsBySpecies(AnimalSpecies species) {
        List<Animal> animals = animalRepository.findBySpecies(species);
        if (animals.isEmpty()) {
            throw new ResourceNotFoundException(STR."Animal with species \{species} not found");
        }
        return animals;
    }

//  Update animal, throw exception, if animal doesn't exist.
    public Animal updateAnimal(Long id, Animal updatedanimal) {
        Animal animal = animalRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(STR."Animal with id \{id} not found"));

        BeanUtils.copyProperties(updatedanimal, animal, "id","createdAt","updatedAt");

        return animalRepository.save(animal);
    }

//  Delete animal by id.
    public void deleteAnimalById(Long animalId) {
        if(!animalRepository.existsById(animalId)) {
            throw new ResourceNotFoundException(STR."Animal with id \{animalId} not found");
        }
    }

//  Get animal by Shelter id.
    public List<Animal> getAnimalsByShelterId(Long shelterId) {
        List<Animal> animals = animalRepository.findAll()
                .stream()
                .filter(animal -> animal.getShelter() != null && animal.getShelter().getId().equals(shelterId))
                .toList();
        if (animals.isEmpty()) {
            throw new ResourceNotFoundException(STR."No animals found for shelter with id: \{shelterId}");
        }
        return animals;
    }

//  Gets MedHistRecords by animal id, throws exception, if animal id doesn't exist.
    public List<MedicalHistoryRecord> getMedicalHistoryByAnimalId(Long animalId) {

        // Optionally, verify the animal exists before retrieving its medical history.
        Animal animal = animalRepository.findById(animalId)
                .orElseThrow(() -> new ResourceNotFoundException(STR."Animal with id \{animalId} not found"));

        // Delegate fetching of the records to MedicalHistoryRecordService.
        return medicalHistoryRecordService.getRecordsByAnimalId(animal.getId());
    }

//  Update animal status(AVAILABLE,PENDING,ADOPTED), throw exception if not animal is found.
    public Animal updateAnimalStatus(Long id, AnimalStatus newStatus) {
        Animal animal = animalRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(STR."Adoption application with id \{id} not found"));
        animal.setStatus(newStatus);

        return animalRepository.save(animal);
    }
}
