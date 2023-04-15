package com.example.mediappbackend.controller;

import com.example.mediappbackend.dto.PatientDTO;
import com.example.mediappbackend.dto.SpecialtyDTO;
import com.example.mediappbackend.exception.ModelNotFoundException;
import com.example.mediappbackend.model.Patient;
import com.example.mediappbackend.model.Specialty;
import com.example.mediappbackend.service.impl.SpecialtyServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/specialties")
@RequiredArgsConstructor
public class SpecialtyController {

    @Autowired
    private  final SpecialtyServiceImpl service;// new PatientService();

    @Qualifier("defaultMapper")
    private final ModelMapper mapper;

    @GetMapping
    public ResponseEntity<List<SpecialtyDTO>> findAll(){
        List<SpecialtyDTO> list = service.findAll().stream().map(this::convertToDto).collect(Collectors.toList());
        return new ResponseEntity<>(list,OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<SpecialtyDTO> findById( @PathVariable("id") Integer id){
        Specialty obj = service.findById(id);

        return new ResponseEntity<>(this.convertToDto(obj),OK);
    }


    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody SpecialtyDTO dto){
        Specialty obj=service.save(convertToEntity(dto));
        URI location= ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdSpecialty()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<SpecialtyDTO> update(@PathVariable("id") Integer id, @Valid @RequestBody SpecialtyDTO dto){
        dto.setIdSpecialty(id);
        Specialty obj=service.update(convertToEntity(dto),id);
        return new ResponseEntity<>(convertToDto(obj),OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id){

        Specialty obj=service.findById(id);

        if (obj == null){
            throw new ModelNotFoundException("ID NOT FOUND"+ id);
        }
        service.delete(id);
        return new ResponseEntity<>(NO_CONTENT);
    }

    // código mapper

    private SpecialtyDTO convertToDto(Specialty obj){
        return mapper.map(obj, SpecialtyDTO.class);
    }

    private Specialty convertToEntity(SpecialtyDTO dto){
        return mapper.map(dto,Specialty.class);
    }

}
