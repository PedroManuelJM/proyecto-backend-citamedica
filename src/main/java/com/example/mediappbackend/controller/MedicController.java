package com.example.mediappbackend.controller;

import com.example.mediappbackend.dto.MedicDTO;
import com.example.mediappbackend.exception.ModelNotFoundException;
import com.example.mediappbackend.model.Medic;
import com.example.mediappbackend.service.impl.MedicServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/medics")
@RequiredArgsConstructor
public class MedicController {

    @Autowired
    private  final MedicServiceImpl service;// new PatientService();

    @Qualifier("medicMapper") // nota colocar un archivo file lombok.config
    private final ModelMapper mapper;

  //  @PreAuthorize("@authServiceImpl.hasAccess('findAll')")
    @GetMapping
    public ResponseEntity<List<MedicDTO>> findAll(){
        List<MedicDTO> list = service.findAll().stream().map(this::convertToDto).collect(Collectors.toList());
        return new ResponseEntity<>(list,OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<MedicDTO> findById( @PathVariable("id") Integer id){
        Medic obj = service.findById(id);

        return new ResponseEntity<>(this.convertToDto(obj),OK);
    }


    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody MedicDTO dto){
        Medic obj=service.save(convertToEntity(dto));
        URI location= ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdMedic()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<MedicDTO> update(@PathVariable("id") Integer id, @Valid @RequestBody MedicDTO dto){
        dto.setIdMedic(id);
        Medic obj=service.update(convertToEntity(dto),id);
        return new ResponseEntity<>(convertToDto(obj),OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id){

        Medic obj=service.findById(id);

        if (obj == null){
            throw new ModelNotFoundException("ID NOT FOUND"+ id);
        }
        service.delete(id);
        return new ResponseEntity<>(NO_CONTENT);
    }

    // código mapper

    private MedicDTO convertToDto(Medic obj){
        return mapper.map(obj, MedicDTO.class);
    }

    private Medic convertToEntity(MedicDTO dto){
        return mapper.map(dto,Medic.class);
    }

}
