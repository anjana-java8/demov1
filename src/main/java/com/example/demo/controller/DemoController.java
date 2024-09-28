package com.example.demo.controller;

import com.example.demo.entity.Demo;
import com.example.demo.payload.DemoDto;
import com.example.demo.service.DemoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vi/demo")
public class DemoController {

    private DemoService demoService;

    public DemoController(DemoService demoService) {
        this.demoService = demoService;
    }


//    @GetMapping
//    public List<Demo> getAllDemos(){
//        List<Demo> demos = demoService.getDemos();
//        return demos;
//
//    }


//    @GetMapping
//    public ResponseEntity <List<Demo>> getAllDemos(){
//        List<Demo> demos = demoService.getDemos();
//        return new ResponseEntity<>(demos, HttpStatus.OK);
//
//    }
    @GetMapping
    public ResponseEntity <List<DemoDto>> getAllDemos(){
        List<DemoDto> dtos = demoService.getDemos();
        return new ResponseEntity<>(dtos, HttpStatus.OK);

    }

//    @PostMapping
//    public ResponseEntity<Demo> createDemo(
//            @RequestBody Demo demo
//    ){
//        Demo demo1 = demoService.createDemo(demo);
//        return new ResponseEntity<>(demo1,HttpStatus.CREATED);
//    }

    @PostMapping
    public ResponseEntity<DemoDto> createDemo(
            @RequestBody DemoDto demoDto
    ){
        DemoDto demo1Dto = demoService.createDemo(demoDto);
        return new ResponseEntity<>(demo1Dto,HttpStatus.CREATED);
    }

    @DeleteMapping
    public ResponseEntity<String> deleteDemo(
            @RequestParam long id
    ){
        demoService.deleteDemo(id);
        return new ResponseEntity<>("Deleted", HttpStatus.OK);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Demo> updateDemo(
            @PathVariable long id,
            @RequestBody Demo demo
    ){

        Demo updateDemo = demoService.updateDemo(id,demo);
        return new ResponseEntity<>(updateDemo,HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<DemoDto> getDemoById(
            @PathVariable long id
    ){
        DemoDto dto = demoService.getDemoById(id);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

}
