package com.example.mediappbackend.service.impl;

import com.example.mediappbackend.model.Specialty;
import com.example.mediappbackend.repo.IGenericRepo;
import com.example.mediappbackend.repo.ISpecialtyRepo;
import com.example.mediappbackend.service.ISpecialtyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SpecialtyServiceImpl extends CRUDImpl<Specialty,Integer> implements ISpecialtyService {

    private final ISpecialtyRepo repo;
    @Override
    protected IGenericRepo<Specialty, Integer> getRepo() {
        return repo;
    }




}
