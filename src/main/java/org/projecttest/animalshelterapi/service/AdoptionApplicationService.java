package org.projecttest.animalshelterapi.service;

import lombok.RequiredArgsConstructor;
import org.projecttest.animalshelterapi.entity.AdoptionApplication;
import org.projecttest.animalshelterapi.entity.Animal;
import org.projecttest.animalshelterapi.exceptions.ResourceNotFoundException;
import org.projecttest.animalshelterapi.repository.AdoptionApplicationRepository;
import org.projecttest.animalshelterapi.repository.AnimalRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdoptionApplicationService {

    private final AdoptionApplicationRepository adoptionApplicationRepository;
    private final AnimalRepository animalRepository;

    public Page<AdoptionApplication> findAllApplications(Pageable pageable) {
        return adoptionApplicationRepository.findAll(pageable);
    }

    public AdoptionApplication createApplication(AdoptionApplication application, Long animalId) {
        Animal animal = animalRepository.findById(animalId)
                .orElseThrow(() -> new ResourceNotFoundException(STR."Animal with id \{animalId} not found"));

        application.setAnimal(animal);
        return adoptionApplicationRepository.save(application);
    }

    public AdoptionApplication findApplicationById(Long id) {
        return adoptionApplicationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(STR."Adoption application with id \{id} not found"));
    }

    public AdoptionApplication updateApplicationStatus(Long id, AdoptionApplication updatedApplication) {
        AdoptionApplication application = findApplicationById(id);

        if (updatedApplication.getStatus() != null) {
            application.setStatus(updatedApplication.getStatus());
        }

        return adoptionApplicationRepository.save(application);
    }

    public void deleteApplication(Long id) {
        AdoptionApplication application = findApplicationById(id);
        adoptionApplicationRepository.delete(application);
    }
}
