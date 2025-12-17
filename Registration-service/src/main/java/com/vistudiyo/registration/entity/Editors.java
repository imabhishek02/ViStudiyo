package com.vistudiyo.registration.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Editors {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID editorID;
    @Column(nullable = false,length = 20)
    private String name;
    @Column(nullable = false,updatable = false)
    private  String loginType;
    private String experience;
    private String category;
    @Column(nullable = false,unique = true,updatable = false)
    private String email;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false,unique = true)
    private String userName;


}
