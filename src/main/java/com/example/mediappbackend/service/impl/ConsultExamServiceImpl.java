package com.example.mediappbackend.service.impl;

import com.example.mediappbackend.model.ConsultExam;
import com.example.mediappbackend.repo.IConsultExamRepo;
import com.example.mediappbackend.service.IConsultExamService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ConsultExamServiceImpl  implements IConsultExamService {

    private final IConsultExamRepo repo;

    @Override
    public List<ConsultExam> getExamsByConsultId(Integer idConsult) {
        return repo.getExamsByConsultId(idConsult);
    }
}
