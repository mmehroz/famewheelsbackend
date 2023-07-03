package com.fame.famewheels.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fame.famewheels.dto.MakeDto;
import com.fame.famewheels.services.MakeService;

@RestController
@RequestMapping("fame")
public class MakeController {
    @Autowired
    private MakeService makeService;

    @GetMapping("/getMake")
    public ResponseEntity<List<MakeDto>> getAllMake(){
        List<MakeDto> make=this.makeService.getAllData();
        return new ResponseEntity<List<MakeDto>>(make, HttpStatus.OK);
    }
    
}
