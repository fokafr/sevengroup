package com.example.datamanagement.services;

import com.example.datamanagement.dto.EmployeDto;
import com.example.datamanagement.entities.BadgingEmploye;
import com.example.datamanagement.entities.Employee;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public interface EmpService {
     BadgingEmploye badgingAfternoon(BadgingEmploye badg,String email, String pass);
     List<EmployeDto> findAllEmployee();
     Employee findAllEmployeeByID(long id);
     Employee addNewEmploye(EmployeDto employeDto);
     public boolean authenticate(String name, String pass);
     public Employee authenticateByEmailAndPass(String email, String pass);
     Employee getByName(String name);
     Employee getEmpByNameAndPass(String name, String pass);
     BadgingEmploye badgingOnMorning(BadgingEmploye badge, String email, String pass);
     List<BadgingEmploye> displayBadgingByName(String name);
     public boolean isLoginAlreadyInUse(String name);
     void deleteEmployeeByName(String name);
     void deleteRegistryById(long id);
     void addEmployee();
     List<BadgingEmploye>  displayAllRegistration();
     List<BadgingEmploye>  displayAllRegistrationOnSomePeriod(String startDate,String endDate);
}