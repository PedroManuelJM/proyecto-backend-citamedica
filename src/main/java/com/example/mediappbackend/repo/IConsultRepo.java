package com.example.mediappbackend.repo;

import com.example.mediappbackend.dto.IConsultProcDTO;
import com.example.mediappbackend.model.Consult;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface IConsultRepo extends IGenericRepo<Consult,Integer>{


    //FRT: jaime
    //BD: jaime
    @Query("FROM Consult c WHERE c.patient.dni = :dni OR LOWER(c.patient.firstName) LIKE %:fullname% OR LOWER(c.patient.lastName) LIKE %:fullname%")
    List<Consult> search(@Param("dni") String dni, @Param("fullname") String fullname);

    // >=      |  <
    //15-02-23 | 25-02-23
    @Query("FROM Consult c WHERE c.consultDate BETWEEN :date1 AND :date2")
    List<Consult> searchByDates(@Param("date1")LocalDateTime date1, @Param("date2") LocalDateTime date2);

    // QUERY PARA LOS DIAGRAMAS  DE REPORT
    @Query(value = "select * from fn_list()", nativeQuery = true)
 //   List<Object[]> callProcedureOrFunction(); metodo 1
    List<IConsultProcDTO> callProcedureOrFunction();

    @Query(value = "select * from fn_list()", nativeQuery = true)
    List<Object[]> callProcedureOrFunctionClass();
}
