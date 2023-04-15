package com.example.mediappbackend.service.impl;

import com.example.mediappbackend.model.Medic;
import com.example.mediappbackend.repo.IGenericRepo;
import com.example.mediappbackend.repo.IMedicRepo;
import com.example.mediappbackend.service.IMedicService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MedicServiceImpl extends  CRUDImpl<Medic,Integer> implements IMedicService {

    private final IMedicRepo repo;

    @Override
    protected IGenericRepo<Medic, Integer> getRepo() {
        return repo;
    }
}
