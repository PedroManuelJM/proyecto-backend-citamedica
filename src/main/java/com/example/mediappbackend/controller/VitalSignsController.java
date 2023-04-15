package com.example.mediappbackend.controller;

import com.example.mediappbackend.dto.VitalSignsDTO;
import com.example.mediappbackend.exception.ModelNotFoundException;
import com.example.mediappbackend.model.VitalSigns;
import com.example.mediappbackend.service.impl.VitalSignsServiceImpl;
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
@RequestMapping("/vitalsigns")
@RequiredArgsConstructor
public class VitalSignsController {

    @Autowired
    private  final VitalSignsServiceImpl service;// new PatientService();

    @Qualifier("defaultMapper")
    private final ModelMapper mapper;

    @GetMapping
    public ResponseEntity<List<VitalSignsDTO>> findAll(){
        List<VitalSignsDTO> list = service.findAll().stream().map(this::convertToDto).collect(Collectors.toList());
        return new ResponseEntity<>(list,OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<VitalSignsDTO> findById( @PathVariable("id") Integer id){
        VitalSigns obj = service.findById(id);

        return new ResponseEntity<>(this.convertToDto(obj),OK);
    }


    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody VitalSignsDTO dto){
        VitalSigns obj=service.save(convertToEntity(dto));
        URI location= ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdVitalSigns()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<VitalSignsDTO> update(@PathVariable("id") Integer id, @Valid @RequestBody VitalSignsDTO dto){
        dto.setIdVitalSigns(id);
        VitalSigns obj=service.update(convertToEntity(dto),id);
        return new ResponseEntity<>(convertToDto(obj),OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id){

        VitalSigns obj=service.findById(id);

        if (obj == null){
            throw new ModelNotFoundException("ID NOT FOUND"+ id);
        }
        service.delete(id);
        return new ResponseEntity<>(NO_CONTENT);
    }

    // código mapper

    private VitalSignsDTO convertToDto(VitalSigns obj){
        return mapper.map(obj, VitalSignsDTO.class);
    }

    private VitalSigns convertToEntity(VitalSignsDTO dto){
        return mapper.map(dto,VitalSigns.class);
    }
}
