package org.projecttest.animalshelterapi.controllers;

import lombok.RequiredArgsConstructor;
import org.projecttest.animalshelterapi.converter.AdoptionApplicationConverter;
import org.projecttest.animalshelterapi.dto.CreateAdoptionApplicationRequest;
import org.projecttest.animalshelterapi.dto.GetAdoptionApplicationResponse;
import org.projecttest.animalshelterapi.dto.UpdateAdoptionApplicationStatusRequest;
import org.projecttest.animalshelterapi.entity.AdoptionApplication;
import org.projecttest.animalshelterapi.service.AdoptionApplicationService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api/adoption-applications")
@RestController
public class AdoptionApplicationController {

    private final AdoptionApplicationService adoptionApplicationService;
    private final AdoptionApplicationConverter converter;

    @GetMapping
    public Page<GetAdoptionApplicationResponse> getApplications(Pageable pageable) {
        return adoptionApplicationService.findAllApplications(pageable)
                .map(converter::entityToDto);
    }

    @PostMapping
    public GetAdoptionApplicationResponse createAdoptionApplication(@RequestBody CreateAdoptionApplicationRequest request) {
        AdoptionApplication application = converter.dtoToEntity(request);
        return converter.entityToDto(adoptionApplicationService.createApplication(application, request.getAnimalId()));
    }

    @GetMapping("/{id}")
    public GetAdoptionApplicationResponse getApplicationById(@PathVariable Long id) {
        return converter.entityToDto(adoptionApplicationService.findApplicationById(id));
    }

    @PatchMapping("/{id}")
    public GetAdoptionApplicationResponse patchApplicationById(
            @PathVariable Long id,
            @RequestBody UpdateAdoptionApplicationStatusRequest request)
    {
        AdoptionApplication application = adoptionApplicationService.findApplicationById(id);
        converter.updateApplicationStatus(application, request.getStatus());
        return converter.entityToDto(adoptionApplicationService.updateApplicationStatus(id, application));
    }

    @DeleteMapping("/{id}")
    public void deleteApplication(@PathVariable Long id) {
        adoptionApplicationService.deleteApplication(id);
    }
}
