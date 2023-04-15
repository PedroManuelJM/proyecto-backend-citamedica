package com.example.mediappbackend.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity

public class VitalSigns {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer idVitalSigns;

    @ManyToOne
    @JoinColumn(name = "id_patient",nullable = false, foreignKey = @ForeignKey(name = "FK_VITALSIGNS_PATIENT"))
    private Patient patient;

    @Column(nullable = false)
    private LocalDateTime vitalsigndate;

    @Column(nullable = false, length = 24)
    private String temperature;

    @Column(nullable = false, length = 24)
    private String pulse;

    @Column(nullable = false, length = 24)
    private String rate;


}
