package com.example.datamanagement.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Employee implements Serializable {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long id;
    private String name;
    private String firstname;
    private String pass;
    private String email;
    private String privilege; //ADMIN, SUPER_USER, USER
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY , orphanRemoval = true)
    @JoinColumn(name = "employee_id")
    private List<BadgingEmploye> badgingEmploye = new ArrayList<>();

    public void addBadgingOnEmp(BadgingEmploye badg){
        if(badg != null){
            if(badgingEmploye == null){
                badgingEmploye = new ArrayList<>();
            }
            badgingEmploye.add(badg);
        }
    }
}