package org.projecttest.animalshelterapi.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.projecttest.animalshelterapi.enums.ApplicationStatus;

import java.sql.Timestamp;

@Entity
@Getter
@Setter
@ToString
@Table(name = "adoption_applications")
public class AdoptionApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "animal_id")
    private Animal animal;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    private String phone;

    @Enumerated(EnumType.STRING)
    private ApplicationStatus status;

    private String comment;
    private Timestamp createdAt;
}
