package com.vistudiyo.registration.service;

import com.vistudiyo.registration.dtos.CustomerResponseDto;
import com.vistudiyo.registration.dtos.RegisterRequestDTO;
import com.vistudiyo.registration.dtos.UpdateCustomerDto;
import com.vistudiyo.registration.entity.Editors;
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
        Editors obj = mapper.map(request,Editors.class);
        registerRepo.save(obj);
    }

    public CustomerResponseDto findByID(UUID id) {
       Optional<Editors> editor =  registerRepo.findById(id);
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
        Editors editor = registerRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Please enter valid id"));

        updateUserMapper.updateUserFromDto(updateUserDetails,editor);
        registerRepo.save(editor);
    }


    public void deleteUser(UUID id) {
        Editors obj = registerRepo.findById(id).orElseThrow(()->new RuntimeException("No such id"));
        registerRepo.delete(obj);

    }
}
