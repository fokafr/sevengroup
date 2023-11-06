package com.example.datamanagement.mappers;

import com.example.datamanagement.dto.EmployeDto;
import com.example.datamanagement.entities.Employee;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class EmpMapperImpl {

    public EmployeDto fromEmpToDto(Employee emp){
        EmployeDto empDto = new EmployeDto();
        BeanUtils.copyProperties(emp, empDto);
        return empDto;
    }

    public Employee fromEmpDtoToEmp(EmployeDto empdto){
        Employee emp = new Employee();
        BeanUtils.copyProperties(empdto, emp);
        return emp;
    }
}
