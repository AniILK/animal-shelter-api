package org.projecttest.animalshelterapi.entity;


import jakarta.persistence.*;
import lombok.Getter;

import lombok.Setter;
import lombok.ToString;
import org.projecttest.animalshelterapi.enums.AnimalSpecies;
import org.projecttest.animalshelterapi.enums.AnimalStatus;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@Table(name = "animals")
public class Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "shelter_id")
    private Shelter shelter;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private AnimalSpecies species;

    private String breed;
    private int age;
    private String gender;
    private String description;

    @Enumerated(EnumType.STRING)
    private AnimalStatus status;

    private Timestamp createdAt;
    private Timestamp updatedAt;

    @OneToMany(mappedBy = "animal", cascade = CascadeType.ALL)
    private List<MedicalHistoryRecord> medicalHistoryRecords = new ArrayList<>();

    @OneToMany(mappedBy = "animal", cascade = CascadeType.ALL)
    private List<AdoptionApplication> adoptionApplications = new ArrayList<>();

}
