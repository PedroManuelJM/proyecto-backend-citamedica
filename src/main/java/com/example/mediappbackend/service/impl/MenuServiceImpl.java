package com.example.mediappbackend.service.impl;

import com.example.mediappbackend.model.Menu;
import com.example.mediappbackend.model.VitalSigns;
import com.example.mediappbackend.repo.IGenericRepo;
import com.example.mediappbackend.repo.IMenuRepo;
import com.example.mediappbackend.service.IMenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MenuServiceImpl extends CRUDImpl<Menu,Integer> implements IMenuService {

    private final IMenuRepo repo;

    @Override
    protected IGenericRepo<Menu, Integer> getRepo() {
        return repo;
    }

    @Override
    public List<Menu> getMenusByUsername(String username) {
       // String contextSessionUser = SecurityContextHolder.getContext().getAuthentication().getName();
        return repo.getMenusByUsername(username);
    }
}
