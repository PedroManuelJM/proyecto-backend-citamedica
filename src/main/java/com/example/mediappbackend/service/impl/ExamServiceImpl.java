package com.example.mediappbackend.service.impl;

import com.example.mediappbackend.model.Exam;
import com.example.mediappbackend.repo.IExamRepo;
import com.example.mediappbackend.repo.IGenericRepo;
import com.example.mediappbackend.service.IExamService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExamServiceImpl extends CRUDImpl<Exam,Integer> implements IExamService {

    private final IExamRepo repo;
    @Override
    protected IGenericRepo<Exam, Integer> getRepo() {
        return repo;
    }




}
