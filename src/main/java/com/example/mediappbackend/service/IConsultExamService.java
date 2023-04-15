package com.example.mediappbackend.service;

import com.example.mediappbackend.model.ConsultExam;

import java.util.List;

public interface IConsultExamService {

    List<ConsultExam> getExamsByConsultId(Integer idConsult);
}
