package org.projecttest.animalshelterapi.repository;

import org.projecttest.animalshelterapi.entity.Animal;
import org.projecttest.animalshelterapi.enums.AnimalSpecies;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Long> {
//  Finds animals, by their species.
    List<Animal> findBySpecies(AnimalSpecies species);
}
