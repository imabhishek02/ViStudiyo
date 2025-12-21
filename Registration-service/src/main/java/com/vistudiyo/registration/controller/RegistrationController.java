package com.vistudiyo.registration.controller;

import com.vistudiyo.registration.dtos.CustomerResponseDto;
import com.vistudiyo.registration.dtos.RegisterRequestDTO;
import com.vistudiyo.registration.dtos.UpdateCustomerDto;
import com.vistudiyo.registration.service.RegisterService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/registration")
public class RegistrationController {

    @Autowired
    private RegisterService registerService;

    @PostMapping("/register")
    public ResponseEntity<String>  registerEntity(@Valid @RequestBody RegisterRequestDTO request){
        registerService.registerEntity(request);
       return ResponseEntity.status(HttpStatus.CREATED).body("Success");
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<CustomerResponseDto> getUserByid(@PathVariable UUID id){
        CustomerResponseDto res = registerService.findByID(id);
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<CustomerResponseDto>> getAllUser(){
        List<CustomerResponseDto> allUser = registerService.findAllUser();
        return ResponseEntity.status(HttpStatus.OK).body(allUser);
    }

    @PostMapping("/updateUser/{id}")
    public ResponseEntity<String> updateUser(@PathVariable UUID id, @RequestBody UpdateCustomerDto updateUserDetails){
        registerService.updateUser(id,updateUserDetails);
        return ResponseEntity.status(HttpStatus.OK).body("Success");
    }

    @DeleteMapping("/deleteUser/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable UUID id){
        registerService.deleteUser(id);
        return ResponseEntity.status(HttpStatus.OK).body("Successfully Deleted");
    }
}
