package com.example.mediappbackend.dto;

import jakarta.validation.constraints.NotNull;

public record PatientRecord(Integer idPatient, @NotNull String name) {

}
