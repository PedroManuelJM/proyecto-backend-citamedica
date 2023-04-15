package com.example.mediappbackend.service.impl;

import com.example.mediappbackend.dto.ConsultProcDTO;
import com.example.mediappbackend.dto.IConsultProcDTO;
import com.example.mediappbackend.model.Consult;
import com.example.mediappbackend.model.Exam;
import com.example.mediappbackend.repo.IConsultExamRepo;
import com.example.mediappbackend.repo.IGenericRepo;
import com.example.mediappbackend.repo.IConsultRepo;
import com.example.mediappbackend.service.IConsultService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.File;
import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
public class ConsultServiceImpl extends CRUDImpl<Consult,Integer> implements IConsultService {

    private final IConsultRepo consultRepo;

    private final IConsultExamRepo ceRepo;


    @Override
    protected IGenericRepo<Consult, Integer> getRepo() {
        return consultRepo;
    }

    // guarda en la tabla consulta y en la tabla intermedia
    @Override
    @Transactional
    public Consult saveTransactional(Consult consult, List<Exam> exams) {
        consultRepo.save(consult);
        exams.forEach(ex -> ceRepo.saveExam(consult.getIdConsult(), ex.getIdExam()));

        return consult;
    }

    public List<Consult> search(String dni, String fullname) {
        return consultRepo.search(dni, fullname);
    }

    @Override
    public List<Consult> searchByDates(LocalDateTime date1, LocalDateTime date2) {
        int OFFSET_DAYS = 1;
        return consultRepo.searchByDates(date1, date2.plusDays(OFFSET_DAYS));
    }

    // METODO 2
    @Override
    public List<IConsultProcDTO> callProcedureOrFunction() {
        return consultRepo.callProcedureOrFunction();
    }

    /* METODO 1
    @Override
    public List<ConsultProcDTO> callProcedureOrFunction() {
        // lista de objetos
        List<ConsultProcDTO> lst = new ArrayList<>();
        consultRepo.callProcedureOrFunction().forEach(e -> {
            ConsultProcDTO dto= new ConsultProcDTO();
            dto.setQuantity((Integer) e[0]);
            dto.setConsultdate((String) e[1]);
            lst.add(dto);
        });
        return lst;
    }*/
    @Override
    public List<ConsultProcDTO> callProcedureOrFunctionClass() {
        // lista de objetos
        List<ConsultProcDTO> lst = new ArrayList<>();
        consultRepo.callProcedureOrFunctionClass().forEach(e -> {
            ConsultProcDTO dto= new ConsultProcDTO();
            dto.setQuantity((Integer) e[0]);
            dto.setConsultDate((String) e[1]);
            lst.add(dto);
        });
        return lst;
    }
    @Override
    public byte[] generateReport() throws Exception {
        byte[]data = null;
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("txt_title","Report Title");
        File file= new ClassPathResource("/reports/consultas.jasper").getFile();
        JasperPrint print= JasperFillManager.fillReport(file.getPath(),parameters,new JRBeanCollectionDataSource(callProcedureOrFunctionClass()));
        data = JasperExportManager.exportReportToPdf(print);

        return data;
    }


}
