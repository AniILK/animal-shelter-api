package org.projecttest.animalshelterapi.controllers;

import lombok.RequiredArgsConstructor;
import org.projecttest.animalshelterapi.converter.ShelterConverter;
import org.projecttest.animalshelterapi.dto.CreateShelterRequest;
import org.projecttest.animalshelterapi.dto.GetShelterResponse;
import org.projecttest.animalshelterapi.entity.Shelter;
import org.projecttest.animalshelterapi.service.ShelterService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/shelters")
@RestController
public class ShelterController {

    private final ShelterService shelterService;
    private final ShelterConverter converter;

    @GetMapping
    public List<GetShelterResponse> getShelterList() {
        return converter.toDtoList(shelterService.findAllShelters());
    }

    @PostMapping
    public GetShelterResponse createShelter(@RequestBody CreateShelterRequest request) {
        Shelter shelter = converter.toEntity(request);
        return converter.toDto(shelterService.createShelter(shelter));
    }

    @GetMapping("/{id}")
    public GetShelterResponse getShelterById(@PathVariable Long id) {
        return converter.toDto(shelterService.findShelterById(id));
    }

    @PutMapping("/{id}")
    public GetShelterResponse putShelterById(@PathVariable Long id, @RequestBody CreateShelterRequest request) {
        Shelter shelter = converter.toEntity(request);
        return converter.toDto(shelterService.updateShelter(id, shelter));
    }

    @PatchMapping("/{id}")
    public GetShelterResponse patchShelterById(@PathVariable Long id, @RequestBody CreateShelterRequest request) {
        Shelter shelter = converter.toEntity(request);
        return converter.toDto(shelterService.partialUpdateShelter(id, shelter));
    }

    @DeleteMapping("/{id}")
    public void deleteShelterById(@PathVariable Long id) {
        shelterService.deleteShelter(id);
    }
}
