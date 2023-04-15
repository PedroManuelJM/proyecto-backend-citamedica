package com.example.mediappbackend.service;

import com.example.mediappbackend.model.Menu;

import java.util.List;

public interface IMenuService extends ICRUD<Menu,Integer>{

    List<Menu> getMenusByUsername(String username);
}
