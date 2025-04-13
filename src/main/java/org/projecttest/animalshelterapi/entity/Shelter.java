package org.projecttest.animalshelterapi.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@Table(name = "shelters")
public class Shelter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String address;
    private String city;
    private String zipCode;
    private String phone;

    @Column(nullable = false)
    private String email;

    private Timestamp createdAt;
    private Timestamp updatedAt;

    @OneToMany(mappedBy = "shelter", cascade = CascadeType.ALL)
    private List<Animal> animals = new ArrayList<>();

}
