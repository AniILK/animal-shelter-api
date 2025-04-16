package org.projecttest.animalshelterapi.service;

import lombok.RequiredArgsConstructor;
import org.projecttest.animalshelterapi.entity.Shelter;
import org.projecttest.animalshelterapi.exceptions.ResourceNotFoundException;
import org.projecttest.animalshelterapi.repository.ShelterRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ShelterService {

    private final ShelterRepository shelterRepository;

//  Create and update shelter
    public Shelter saveShelter(Shelter shelter) {
        return shelterRepository.save(shelter);
    }

//  Retrieve all shelters and throw an exception if none are found
    public List<Shelter> getAllShelters() {
        List<Shelter> shelters = shelterRepository.findAll();
        if (shelters.isEmpty()) {
            throw new ResourceNotFoundException("No shelters found");
        }
        return shelters;
    }

//  Gets shelter by its id, throws exception, if the shelter doesn't exist.
    public Shelter getShelterById(Long id) {
        return shelterRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(STR."Shelter with id \{id} not found"));
    }

//  Update an existing shelter and throws exception if shelter with provided id doesn't exist.
    public Shelter updateShelter(Long id, Shelter updatedShelter) {
        Shelter shelter = shelterRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(STR."Shelter with id \{id} not found"));

        BeanUtils.copyProperties(updatedShelter, shelter, "id", "createdAt", "updatedAt");

        return shelterRepository.save(shelter);
    }

//  Delete shelter by its id, throws exception, if shelter doesn't exist.
    public void deleteShelter(Long id) {
        if (!shelterRepository.existsById(id)) {
            throw new ResourceNotFoundException(STR."Shelter with id \{id} not found");
        }
        shelterRepository.deleteById(id);
    }
}
