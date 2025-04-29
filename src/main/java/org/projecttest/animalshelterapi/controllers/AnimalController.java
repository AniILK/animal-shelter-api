package org.projecttest.animalshelterapi.controllers;

import lombok.RequiredArgsConstructor;
import org.projecttest.animalshelterapi.converter.AnimalConverter;
import org.projecttest.animalshelterapi.dto.CreateAnimalRequest;
import org.projecttest.animalshelterapi.dto.GetAnimalResponse;
import org.projecttest.animalshelterapi.entity.Animal;
import org.projecttest.animalshelterapi.service.AnimalService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api/animals")
@RestController
public class AnimalController {

    private final AnimalService animalService;
    private final AnimalConverter converter;

    @GetMapping
    public Page<GetAnimalResponse> getAnimals(
            @RequestParam(required = false) String species,
            Pageable pageable
    ) {
        if (species != null) {
            return animalService.findBySpecies(species, pageable)
                    .map(converter::entityToDto);
        }
        return animalService.findAllAnimals(pageable)
                .map(converter::entityToDto);
    }

    @PostMapping
    public GetAnimalResponse createAnimal(@RequestBody CreateAnimalRequest request) {
        Animal animal = converter.dtoToEntity(request);
        return converter.entityToDto(animalService.createAnimal(animal, request.getShelterId()));
    }

    @GetMapping("/{id}")
    public GetAnimalResponse getAnimalById(@PathVariable Long id) {
        return converter.entityToDto(animalService.findAnimalById(id));
    }

    @PutMapping("/{id}")
    public GetAnimalResponse putAnimalById(@PathVariable Long id, @RequestBody CreateAnimalRequest request) {
        Animal animal = converter.dtoToEntity(request);
        return converter.entityToDto(animalService.updateAnimal(id, animal));
    }

    @PatchMapping("/{id}")
    public GetAnimalResponse patchAnimalById(@PathVariable Long id, @RequestBody CreateAnimalRequest request) {
        Animal animal = converter.dtoToEntity(request);
        return converter.entityToDto(animalService.partialUpdateAnimal(id, animal));
    }

    @DeleteMapping("/{id}")
    public void deleteAnimalById(@PathVariable Long id) {
        animalService.deleteAnimal(id);
    }
}
