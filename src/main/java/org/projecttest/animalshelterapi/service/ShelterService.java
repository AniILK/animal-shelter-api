package org.projecttest.animalshelterapi.service;

import lombok.RequiredArgsConstructor;

import org.projecttest.animalshelterapi.entity.Shelter;

import org.projecttest.animalshelterapi.exceptions.ResourceNotFoundException;
import org.projecttest.animalshelterapi.repository.ShelterRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ShelterService {

    private final ShelterRepository shelterRepository;

    public Page<Shelter> findAllShelters(Pageable pageable) {
        return shelterRepository.findAll(pageable);
    }

    public Shelter createShelter(Shelter shelter) {
        return shelterRepository.save(shelter);
    }

    public Shelter findShelterById(Long id) {
        return shelterRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(STR."Shelter with id \{id} not found"));
    }

    public Shelter updateShelter(Long id, Shelter updatedShelter) {
        Shelter shelter = findShelterById(id);
        shelter.setName(updatedShelter.getName());
        shelter.setAddress(updatedShelter.getAddress());
        shelter.setCity(updatedShelter.getCity());
        shelter.setZipCode(updatedShelter.getZipCode());
        shelter.setPhone(updatedShelter.getPhone());
        shelter.setEmail(updatedShelter.getEmail());
        return shelterRepository.save(shelter);
    }

    public Shelter partialUpdateShelter(Long id, Shelter updatedShelter) {
        Shelter shelter = findShelterById(id);

        if (updatedShelter.getName() != null) shelter.setName(updatedShelter.getName());
        if (updatedShelter.getAddress() != null) shelter.setAddress(updatedShelter.getAddress());
        if (updatedShelter.getCity() != null) shelter.setCity(updatedShelter.getCity());
        if (updatedShelter.getZipCode() != null) shelter.setZipCode(updatedShelter.getZipCode());
        if (updatedShelter.getPhone() != null) shelter.setPhone(updatedShelter.getPhone());
        if (updatedShelter.getEmail() != null) shelter.setEmail(updatedShelter.getEmail());

        return shelterRepository.save(shelter);
    }

    public void deleteShelter(Long id) {
        Shelter shelter = findShelterById(id);
        shelterRepository.delete(shelter);
    }
}
