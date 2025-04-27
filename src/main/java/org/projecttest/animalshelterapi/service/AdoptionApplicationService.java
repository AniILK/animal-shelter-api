package org.projecttest.animalshelterapi.service;

import lombok.RequiredArgsConstructor;
import org.projecttest.animalshelterapi.dto.CreateAdoptionApplicationRequest;
import org.projecttest.animalshelterapi.entity.AdoptionApplication;
import org.projecttest.animalshelterapi.enums.ApplicationStatus;
import org.projecttest.animalshelterapi.exceptions.ResourceNotFoundException;
import org.projecttest.animalshelterapi.repository.AdoptionApplicationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdoptionApplicationService {

    private final AdoptionApplicationRepository adoptionApplicationRepository;

//  Create a new application.
    public AdoptionApplication saveApplication(AdoptionApplication adoptionApplication) {
        return adoptionApplicationRepository.save(adoptionApplication);
    }
//  Get all adoption applications, throw exception, if no applications exist.
    public List<AdoptionApplication> getAllAdoptionApplications() {
        List<AdoptionApplication> adoptionApplications = adoptionApplicationRepository.findAll();
        if (adoptionApplications.isEmpty()) {
            throw new ResourceNotFoundException("No adoption applications found.");
        }
        return adoptionApplications;
    }

//  Get adoption application by id, throw exception, if id doesn't exist.
    public AdoptionApplication getAdoptionApplicationById(Long id) {
        return adoptionApplicationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(STR."Adoption application with id \{id} not found"));
    }

//  Delete adoption application by id, throw exception if it doesn't exist.
    public void deleteAdoptionApplicationById(Long id) {
        if (!adoptionApplicationRepository.existsById(id)) {
            throw new ResourceNotFoundException(STR."Adoption application with id \{id} not found");
        }
        adoptionApplicationRepository.deleteById(id);
    }

//  Get all adoption applications for a unique animal id.
    public List<AdoptionApplication> getAdoptionApplicationsByAnimalId(Long animalId) {
       List<AdoptionApplication> applications = adoptionApplicationRepository.findAll()
               .stream()
               .filter(app -> app.getAnimal() != null && app.getAnimal().getId().equals(animalId))
               .toList();
       if (applications.isEmpty()) {
           throw new ResourceNotFoundException(STR."No adoption applications found for animal id: \{animalId}");
       }
       return applications;
    }
//  Update applications status (PENDING,APPROVED,REJECTED), throw exception, if application is not found.
    public AdoptionApplication updateApplicationStatus(Long id, ApplicationStatus newStatus) {
        AdoptionApplication application = adoptionApplicationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(STR."Adoption application with id \{id} not found"));
        application.setStatus(newStatus);

        return adoptionApplicationRepository.save(application);
    }

}
