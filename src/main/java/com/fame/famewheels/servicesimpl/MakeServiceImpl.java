package com.fame.famewheels.servicesimpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.supercsv.cellprocessor.Collector;

import com.fame.famewheels.dto.MakeDto;
import com.fame.famewheels.entities.Make;
import com.fame.famewheels.repositories.MakeRepository;
import com.fame.famewheels.services.MakeService;

@Service
public class MakeServiceImpl implements MakeService{
    
    @Autowired
    private MakeRepository makeRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<MakeDto> getAllData() {

        List<Make> make=this.makeRepository.findAll();
        List<MakeDto> makeData=make.stream().map((m)-> this.modelMapper.map(m, MakeDto.class)).collect(Collectors.toList());
        return makeData;
    }
    
}
