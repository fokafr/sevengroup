package com.example.datamanagement.dto;

import com.example.datamanagement.entities.BadgingEmploye;
import lombok.Data;

@Data
public class EmployeDto {
    private long id;
    private String name;
    private String firstname;
    private String pass;
    private String email;
    private String privilege;
    //private String activity;
    //private String firstConnectedTime;
    //private String lastConnectedTime;


}
