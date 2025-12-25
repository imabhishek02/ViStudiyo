package com.vistudiyo.registration.service;

import com.vistudiyo.registration.dtos.CustomerResponseDto;
import com.vistudiyo.registration.dtos.RegisterRequestDTO;
import com.vistudiyo.registration.dtos.UpdateCustomerDto;
import com.vistudiyo.registration.entity.Editors;
import com.vistudiyo.registration.exception.EmailAlreadyExist;
import com.vistudiyo.registration.exception.InvalidIDException;
import com.vistudiyo.registration.exception.UserNameAlreadyExist;
import com.vistudiyo.registration.mapper.UpdateUserMapper;
import com.vistudiyo.registration.repository.RegisterRepo;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class RegisterService {

    @Autowired
    private RegisterRepo registerRepo;


    @Autowired
    private UpdateUserMapper updateUserMapper;



    ModelMapper mapper = new ModelMapper();

    public void registerEntity(@Valid RegisterRequestDTO request) {
        if(registerRepo.findByUsername(request.getUserName()).isPresent()){
            throw new UserNameAlreadyExist("Username already exist");
        }if(registerRepo.findByEmail(request.getEmail()).isPresent()){
            throw new EmailAlreadyExist("Email Already Exists");
        }
            Editors obj = mapper.map(request, Editors.class);
            registerRepo.save(obj);
    }

    public CustomerResponseDto findByID(UUID id) {
       Editors editor =  registerRepo.findById(id).orElseThrow(()->new InvalidIDException("Id doesn't exist"));
       CustomerResponseDto obj = mapper.map(editor,CustomerResponseDto.class);
       return obj;
    }

    public List<CustomerResponseDto> findAllUser() {
        List<Editors>allUser = registerRepo.findAll();
        List<CustomerResponseDto> allUserDto = allUser.stream()
                .map(editor ->mapper.map(editor,CustomerResponseDto.class))
                .toList();
        return allUserDto;
    }

    public void updateUser(UUID id, UpdateCustomerDto updateUserDetails) {
        Editors editor = registerRepo.findById(id).orElseThrow(()->new InvalidIDException("Id doesn't exist"));
        updateUserMapper.updateUserFromDto(updateUserDetails,editor);
        registerRepo.save(editor);
    }


    public void deleteUser(UUID id) {
        Editors obj = registerRepo.findById(id).orElseThrow(()->new RuntimeException("No such id"));
        registerRepo.delete(obj);

    }
}
