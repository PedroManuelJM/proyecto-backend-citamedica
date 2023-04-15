package com.example.mediappbackend.service;

import com.example.mediappbackend.model.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IPatientService extends  ICRUD<Patient,Integer>{

    Page<Patient> listPage(Pageable pageable);



}
