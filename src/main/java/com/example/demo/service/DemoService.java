package com.example.demo.service;


import com.example.demo.entity.Demo;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.payload.DemoDto;
import com.example.demo.repository.DemoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DemoService {

    private DemoRepository demoRepository;
    private ModelMapper modelMapper;

    public DemoService(DemoRepository demoRepository,ModelMapper modelMapper) {

        this.demoRepository = demoRepository;
        this.modelMapper = modelMapper;
    }

//    public List<Demo> getDemos(){
//        List<Demo> demos = demoRepository.findAll();
//        return demos;
//
//    }

    public List<DemoDto> getDemos(){
        List<Demo> demos = demoRepository.findAll();
        List<DemoDto> dtos = demos.stream().map(r->mapToDto(r)).collect(Collectors.toList());
        return  dtos;

    }
//    public Demo createDemo(Demo demo) {
//        Demo saveEntity = demoRepository.save(demo);
//        return saveEntity;
//    }


    public DemoDto createDemo(DemoDto demoDto) {
        //copying data from dto to entity
//        Demo demo = new Demo();
//        demo.setName(demoDto.getName());
//        demo.setEmail(demoDto.getEmail());
//        demo.setMobile(demoDto.getMobile());

        Demo demo = mapToEntity(demoDto);
        Demo saveEntity = demoRepository.save(demo);

        //copying data from entity to dto
//        DemoDto dto = new DemoDto();
//        dto.setName(saveEntity.getName());
//        dto.setEmail(saveEntity.getEmail());
//        dto.setMobile(saveEntity.getMobile());

        DemoDto dto = mapToDto(saveEntity);
        return dto;
    }

    public void deleteDemo(long id) {
        demoRepository.deleteById(id);
    }

    public Demo updateDemo(long id, Demo demo) {
        Demo demo2 = demoRepository.findById(id).get();
        demo2.setName(demo.getName());
        demo2.setEmail(demo.getEmail());
        demo2.setMobile(demo.getMobile());
        Demo saveEntity = demoRepository.save(demo2);
        return saveEntity;
    }



//    Demo mapToEntity(DemoDto demoDto){
//        Demo demo = new Demo();
//        demo.setName(demoDto.getName());
//        demo.setEmail(demoDto.getEmail());
//        demo.setMobile(demoDto.getMobile());
//        return demo;
//    }

//    DemoDto mapToDto(Demo demo){
//        DemoDto dto = new DemoDto();
//        dto.setName(dto.getName());
//        dto.setEmail(dto.getEmail());
//        dto.setMobile(dto.getMobile());
//        return dto;
//    }

    Demo mapToEntity(DemoDto demoDto){
        Demo demo = modelMapper.map(demoDto, Demo.class);
//        Demo demo = new Demo();
//        demo.setName(demoDto.getName());
//        demo.setEmail(demoDto.getEmail());
//        demo.setMobile(demoDto.getMobile());
        return demo;
    }

    DemoDto mapToDto(Demo demo){
        DemoDto demoDto = modelMapper.map(demo, DemoDto.class);
//        DemoDto dto = new DemoDto();
//        dto.setName(dto.getName());
//        dto.setEmail(dto.getEmail());
//        dto.setMobile(dto.getMobile());
        return demoDto;
    }

//    public DemoDto getDemoById(long id) {
//        Demo demo = demoRepository.findById(id).get();
//        return mapToDto(demo);
//    }

    public DemoDto getDemoById(long id) {
        Demo demo = demoRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("Record Not Found")
        );
        return mapToDto(demo);
    }
}
