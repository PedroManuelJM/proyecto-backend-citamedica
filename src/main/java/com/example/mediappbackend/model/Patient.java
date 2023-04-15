package com.example.mediappbackend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
//@Table(name= "tbl_patient")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer idPatient;

    @Column(length = 70,nullable = false)
    private String firstName;

    @Column(length = 70,nullable = false)
    private String lastName;

    @Column(length = 8,nullable = false)
    private String dni;

    @Column(length = 150)
    private String address;

    @Column(length = 9,nullable = false)
    private String phone;

    @Column(length = 55,nullable = false)
    private String email;
}
