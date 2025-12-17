package com.vistudiyo.registration.dtos;

import jakarta.validation.constraints.*;
import lombok.Data;


@Data
public class RegisterRequestDTO {

    @Size(min = 1,max = 20,message = "Please enter valid name")
    @NotNull
    private String name;

   // @NotNull
    private String loginType;
    private String experience;
    private String category;

    @NotBlank
    @Email(message = "Please enter the valid email ID")
    private String email;

    @NotBlank(message = "This field cannot be empty.")
    @Size(min = 1,max = 20,message = "Please enter valid password")
    private String password;

    @NotBlank(message = "This field cannot be empty.")
    @Size(min = 1,max = 20,message = "Please enter valid username")
    private String userName;
}
