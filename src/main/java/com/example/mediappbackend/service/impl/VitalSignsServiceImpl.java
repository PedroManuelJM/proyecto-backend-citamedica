package com.example.mediappbackend.service.impl;

import com.example.mediappbackend.model.VitalSigns;
import com.example.mediappbackend.repo.IGenericRepo;
import com.example.mediappbackend.repo.IVitalSignsRepo;
import com.example.mediappbackend.service.IVitalSignsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VitalSignsServiceImpl extends CRUDImpl<VitalSigns,Integer> implements IVitalSignsService {


    private final IVitalSignsRepo repo;
    @Override
    protected IGenericRepo<VitalSigns, Integer> getRepo() {
        return repo;
    }
}
