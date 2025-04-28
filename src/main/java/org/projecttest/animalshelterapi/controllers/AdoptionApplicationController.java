package org.projecttest.animalshelterapi.controllers;

import lombok.RequiredArgsConstructor;
import org.projecttest.animalshelterapi.converter.AdoptionApplicationConverter;
import org.projecttest.animalshelterapi.dto.CreateAdoptionApplicationRequest;
import org.projecttest.animalshelterapi.dto.GetAdoptionApplicationResponse;
import org.projecttest.animalshelterapi.dto.UpdateAdoptionApplicationStatusRequest;
import org.projecttest.animalshelterapi.entity.AdoptionApplication;
import org.projecttest.animalshelterapi.service.AdoptionApplicationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/adoption-applications")
@RestController
public class AdoptionApplicationController {

    private final AdoptionApplicationService adoptionApplicationService;
    private final AdoptionApplicationConverter converter;

    @GetMapping
    public List<GetAdoptionApplicationResponse> getApplicationList() {
        return converter.toDtoList(adoptionApplicationService.findAllApplications());
    }

    @PostMapping
    public GetAdoptionApplicationResponse createAdoptionApplication(@RequestBody CreateAdoptionApplicationRequest request) {
        AdoptionApplication application = converter.toEntity(request);
        return converter.toDto(adoptionApplicationService.createApplication(application, request.getAnimalId()));
    }

    @GetMapping("/{id}")
    public GetAdoptionApplicationResponse getApplicationById(@PathVariable Long id) {
        return converter.toDto(adoptionApplicationService.findApplicationById(id));
    }

    @PatchMapping("/{id}")
    public GetAdoptionApplicationResponse patchApplicationById(
            @PathVariable Long id,
            @RequestBody UpdateAdoptionApplicationStatusRequest request)
    {
        AdoptionApplication application = adoptionApplicationService.findApplicationById(id);
        converter.updateApplicationStatus(application, request.getStatus());
        return converter.toDto(adoptionApplicationService.updateApplicationStatus(id, application));
    }

    @DeleteMapping("/{id}")
    public void deleteApplication(@PathVariable Long id) {
        adoptionApplicationService.deleteApplication(id);
    }
}
