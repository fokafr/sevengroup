package com.example.datamanagement.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class BadgingEmploye implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String firstname;
    private String empName;
    private String email;
    private String timeOnMorning;
    private String timeOnAfternoon;
    private String report;
//    @JsonIgnore
//    @ManyToOne(cascade = CascadeType.ALL)
//  private Employee employee;
}
