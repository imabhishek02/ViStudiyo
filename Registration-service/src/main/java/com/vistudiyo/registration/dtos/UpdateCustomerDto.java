package com.vistudiyo.registration.dtos;

import jakarta.persistence.Column;
import jakarta.validation.Valid;
import lombok.Data;


@Data
public class UpdateCustomerDto {

    private String name;
    private String experience;
    private String category;
    private String password;
    private String userName;
}
