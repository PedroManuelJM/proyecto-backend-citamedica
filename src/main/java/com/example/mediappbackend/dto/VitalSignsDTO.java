package com.example.mediappbackend.dto;

import com.example.mediappbackend.model.Patient;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class VitalSignsDTO {

    @EqualsAndHashCode.Include
    private Integer idVitalSigns;

    @NotNull
    private PatientDTO patient;

    @NotNull
    private LocalDateTime vitalsigndate;

    @NotNull
    @NotEmpty
    private String temperature;

    @NotNull
    @NotEmpty
    private String pulse;

    @NotNull
    @NotEmpty
    private String rate;

}
