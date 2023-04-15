package com.example.mediappbackend.repo;

import com.example.mediappbackend.model.User;

public interface IUserRepo extends  IGenericRepo<User,Integer>{

    // From User WHERE username= ?
    // Derived Query
    User findOneByUsername(String username);

}
