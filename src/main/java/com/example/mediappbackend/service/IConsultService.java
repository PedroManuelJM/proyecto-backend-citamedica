package com.example.mediappbackend.service;

import com.example.mediappbackend.dto.ConsultProcDTO;
import com.example.mediappbackend.dto.IConsultProcDTO;
import com.example.mediappbackend.model.Consult;
import com.example.mediappbackend.model.Exam;

import java.time.LocalDateTime;
import java.util.List;

public interface IConsultService extends  ICRUD<Consult,Integer> {

    Consult saveTransactional(Consult consult, List<Exam> exams);

    List<Consult> search(String dni, String fullname);

    List<Consult> searchByDates(LocalDateTime date1, LocalDateTime date2);

    // List<ConsultProcDTO> callProcedureOrFunction(); metodo 1
    List<IConsultProcDTO> callProcedureOrFunction();

    List<ConsultProcDTO> callProcedureOrFunctionClass();
    byte[] generateReport() throws Exception;


}
