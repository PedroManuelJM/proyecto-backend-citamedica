package com.example.mediappbackend.controller;

import com.example.mediappbackend.dto.MenuDTO;
import com.example.mediappbackend.model.Menu;
import com.example.mediappbackend.service.IMenuService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/menus")
@RequiredArgsConstructor
public class MenuController {

    @Qualifier("defaultMapper")
    private final ModelMapper modelMapper;
    private final IMenuService service;

    @PostMapping("/user")
    public ResponseEntity<List<MenuDTO>> getMenusByUser(@RequestBody String username) throws Exception {
        List<Menu> menus = service.getMenusByUsername(username);
        List<MenuDTO> menusDTO = menus.stream().map(m -> {
            m.setRoles(new ArrayList<>()); //necesario porque fue un nativeQuery y devuelve PersistenBag
            return modelMapper.map(m, MenuDTO.class);
        }).collect(Collectors.toList());
        return new ResponseEntity<>(menusDTO, HttpStatus.OK);
    }

    /* para pruebas es potmasn*/
    /*
    @PostMapping("/user")
    public ResponseEntity<List<MenuDTO>> getMenusByUser() throws Exception {
        List<Menu> menus = service.getMenusByUsername("");
        List<MenuDTO> menusDTO = menus.stream().map(m -> {
            m.setRoles(new ArrayList<>()); //necesario porque fue un nativeQuery y devuelve PersistenBag
            return modelMapper.map(m, MenuDTO.class);
        }).collect(Collectors.toList());
        return new ResponseEntity<>(menusDTO, HttpStatus.OK);
    }*/
}
