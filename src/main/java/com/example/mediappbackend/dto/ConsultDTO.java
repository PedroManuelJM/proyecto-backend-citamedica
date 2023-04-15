package com.example.mediappbackend.dto;

import com.example.mediappbackend.model.ConsultDetail;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ConsultDTO {
 
    @EqualsAndHashCode.Include
    private Integer idConsult;

    @NotNull
    private PatientDTO patient;

    @NotNull
    private MedicDTO medic;

    @NotNull
    private SpecialtyDTO specialty;

    @NotNull
    private String numConsult;

    @NotNull
    private LocalDateTime consultDate = LocalDateTime.now();

    @JsonManagedReference
    @NotNull
    private List<ConsultDetailDTO> details;
}
