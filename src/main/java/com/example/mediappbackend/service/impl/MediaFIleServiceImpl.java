package com.example.mediappbackend.service.impl;

import com.example.mediappbackend.model.MediaFile;
import com.example.mediappbackend.repo.IGenericRepo;
import com.example.mediappbackend.repo.IMediaFileRepo;
import com.example.mediappbackend.service.IMediaFileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class MediaFIleServiceImpl extends CRUDImpl<MediaFile,Integer> implements IMediaFileService {

    private final IMediaFileRepo repo;

    @Override
    protected IGenericRepo<MediaFile, Integer> getRepo() {
        return repo;
    }
}
