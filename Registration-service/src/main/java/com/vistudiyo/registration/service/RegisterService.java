package com.vistudiyo.registration.service;

import com.vistudiyo.registration.dtos.CustomerResponseDto;
import com.vistudiyo.registration.dtos.RegisterRequestDTO;
import com.vistudiyo.registration.dtos.UpdateCustomerDto;
import com.vistudiyo.registration.entity.Editors;
import com.vistudiyo.registration.exception.*;
import com.vistudiyo.registration.mapper.UpdateUserMapper;
import com.vistudiyo.registration.repository.RegisterRepo;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
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

    private static final Logger log = LoggerFactory.getLogger(RegisterService.class);


    public void registerEntity(@Valid RegisterRequestDTO request) {
        try{
            if(registerRepo.findByUsername(request.getUserName()).isPresent()){
                throw new UserNameAlreadyExist("Username already exist");
            }if(registerRepo.findByEmail(request.getEmail()).isPresent()){
                throw new EmailAlreadyExist("Email Already Exists");
            }
            if(isValidPswd(request.getPassword()) && isValidUserName(request.getUserName())){
                Editors obj = mapper.map(request, Editors.class);
                registerRepo.save(obj);
            }

        }catch (DataAccessException ex) {
            throw new RuntimeException("Database error occurred", ex);
        }
    }

    public boolean isValidPswd(String pswd){
        if(pswd == null)return false;
        String pattern = "\\A(?=\\S*?[0-9])(?=\\S*?[a-z])(?=\\S*?[A-Z])(?=\\S*?[@#$%^&+=])\\S{8,}\\z";
        boolean isValid = pswd.matches(pattern);
        if(!isValid){
            throw new InValidPasswordEx("Password must contain least 1 uppercase,lowercase,special character" +
                    ",numeric and minimum length of 8.");
        }
        return true;
    }

    public boolean isValidUserName(String userName){
        if (userName == null) return false;
        String pattern = "^[A-Za-z0-9_.-]{5,20}$";
        boolean isValid = userName.matches(pattern);
        if(!isValid){
            throw new InValidUsernameEx("Username can contain lowercase, uppercase, number or -,_,. and minimum length is 5");
        }
        return true;
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
        if(updateUserDetails.getUserName()!=null){
            isValidUserName(updateUserDetails.getUserName());
        }
        if(updateUserDetails.getPassword() !=null ){
            isValidPswd(updateUserDetails.getPassword());
        }
        updateUserMapper.updateUserFromDto(updateUserDetails,editor);
        registerRepo.save(editor);

    }

    public void deleteUser(UUID id) {
        Editors obj = registerRepo.findById(id).orElseThrow(()->new InvalidIDException("Id doesn't exist"));
        registerRepo.delete(obj);

    }
}
