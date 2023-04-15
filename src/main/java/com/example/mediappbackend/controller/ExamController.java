package com.example.mediappbackend.controller;

import com.example.mediappbackend.dto.ExamDTO;
import com.example.mediappbackend.exception.ModelNotFoundException;
import com.example.mediappbackend.model.Exam;
import com.example.mediappbackend.service.impl.ExamServiceImpl;
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
@RequestMapping("/exams")
@RequiredArgsConstructor
public class ExamController {

    @Autowired
    private  final ExamServiceImpl service;// new PatientService();

    @Qualifier("defaultMapper")
    private final ModelMapper mapper;

    @GetMapping
    public ResponseEntity<List<ExamDTO>> findAll(){
        List<ExamDTO> list = service.findAll().stream().map(this::convertToDto).collect(Collectors.toList());
        return new ResponseEntity<>(list,OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<ExamDTO> findById( @PathVariable("id") Integer id){
        Exam obj = service.findById(id);

        return new ResponseEntity<>(this.convertToDto(obj),OK);
    }


    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody ExamDTO dto){
        Exam obj=service.save(convertToEntity(dto));
        URI location= ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdExam()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ExamDTO> update(@PathVariable("id") Integer id, @Valid @RequestBody ExamDTO dto){
        dto.setIdExam(id);
        Exam obj=service.update(convertToEntity(dto),id);
        return new ResponseEntity<>(convertToDto(obj),OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id){

        Exam obj=service.findById(id);

        if (obj == null){
            throw new ModelNotFoundException("ID NOT FOUND"+ id);
        }
        service.delete(id);
        return new ResponseEntity<>(NO_CONTENT);
    }

    // código mapper

    private ExamDTO convertToDto(Exam obj){
        return mapper.map(obj, ExamDTO.class);
    }

    private Exam convertToEntity(ExamDTO dto){
        return mapper.map(dto,Exam.class);
    }

}
