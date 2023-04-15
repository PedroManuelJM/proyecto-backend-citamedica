package com.example.mediappbackend.config;

import com.example.mediappbackend.dto.ConsultDTO;
import com.example.mediappbackend.dto.MedicDTO;
import com.example.mediappbackend.model.Consult;
import com.example.mediappbackend.model.Medic;
import org.hibernate.collection.spi.PersistentCollection;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {

    @Bean("defaultMapper")
    public ModelMapper modelMapper(){
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setPropertyCondition(context -> !(context.getSource() instanceof PersistentCollection));
        return mapper;
    }

    @Bean("medicMapper")
    public ModelMapper medicMapper(){
       ModelMapper mapper = new ModelMapper();
        TypeMap<MedicDTO, Medic> typeMap=mapper.createTypeMap(MedicDTO.class, Medic.class);
        typeMap.addMapping(MedicDTO::getPrimaryName,(dest, v) -> dest.setFirstName((String) v));
        typeMap.addMapping(MedicDTO::getSurname,(dest, v) -> dest.setLastName((String) v));
        typeMap.addMapping(MedicDTO::getPhoto,(dest, v) -> dest.setPhotoUrl((String) v));

        TypeMap<Medic, MedicDTO> typeMap2=mapper.createTypeMap(Medic.class, MedicDTO.class);
        typeMap2.addMapping(Medic::getFirstName,(dest, v) -> dest.setPrimaryName((String) v));
        typeMap2.addMapping(Medic::getLastName,(dest, v) -> dest.setSurname((String) v));
        typeMap2.addMapping(Medic::getPhotoUrl,(dest, v) -> dest.setPhoto((String) v));
        return  mapper;

    }

    @Bean("consultMapper")
    public ModelMapper consultMapper(){
        ModelMapper mapper = new ModelMapper();
        TypeMap<Consult, ConsultDTO> typeMap1 = mapper.createTypeMap(Consult.class, ConsultDTO.class);
        typeMap1.addMapping(e-> e.getMedic().getFirstName(), (dest, v) -> dest.getMedic().setPrimaryName((String) v));
        typeMap1.addMapping(e-> e.getMedic().getLastName(), (dest, v) -> dest.getMedic().setSurname((String) v));
        typeMap1.addMapping(e-> e.getMedic().getPhotoUrl(), (dest, v) -> dest.getMedic().setPhoto((String) v));

        //mapper.getConfiguration().setPropertyCondition(context -> !(context.getSource() instanceof PersistentCollection));

        return mapper;
    }
}
