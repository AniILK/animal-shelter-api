package org.projecttest.animalshelterapi.controllers;

import lombok.RequiredArgsConstructor;
import org.projecttest.animalshelterapi.converter.AnimalConverter;
import org.projecttest.animalshelterapi.dto.CreateAnimalRequest;
import org.projecttest.animalshelterapi.dto.GetAnimalResponse;
import org.projecttest.animalshelterapi.entity.Animal;
import org.projecttest.animalshelterapi.service.AnimalService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/animals")
@RestController
public class AnimalController {

    private final AnimalService animalService;
    private final AnimalConverter converter;

    @GetMapping
    public List<GetAnimalResponse> getAnimalList(@RequestParam(required = false) String species) {
        if (species != null) {
            return converter.toDtoList(animalService.findBySpecies(species));
        }
        return converter.toDtoList(animalService.findAllAnimals());
    }

    @PostMapping
    public GetAnimalResponse createAnimal(@RequestBody CreateAnimalRequest request) {
        Animal animal = converter.toEntity(request);
        return converter.toDto(animalService.createAnimal(animal, request.getShelterId()));
    }

    @GetMapping("/{id}")
    public GetAnimalResponse getAnimalById(@PathVariable Long id) {
        return converter.toDto(animalService.findAnimalById(id));
    }

    @PutMapping("/{id}")
    public GetAnimalResponse putAnimalById(@PathVariable Long id, @RequestBody CreateAnimalRequest request) {
        Animal animal = converter.toEntity(request);
        return converter.toDto(animalService.updateAnimal(id, animal));
    }

    @PatchMapping("/{id}")
    public GetAnimalResponse patchAnimalById(@PathVariable Long id, @RequestBody CreateAnimalRequest request) {
        Animal animal = converter.toEntity(request);
        return converter.toDto(animalService.partialUpdateAnimal(id, animal));
    }

    @DeleteMapping("/{id}")
    public void deleteAnimalById(@PathVariable Long id) {
        animalService.deleteAnimal(id);
    }
}
