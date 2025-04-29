package org.projecttest.animalshelterapi.repository;

import org.projecttest.animalshelterapi.entity.Animal;
import org.projecttest.animalshelterapi.enums.AnimalSpecies;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Long> {
//  Finds animals, by their species.
Page<Animal> findBySpecies(String species, Pageable pageable);
}
