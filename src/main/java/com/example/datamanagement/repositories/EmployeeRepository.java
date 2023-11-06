package com.example.datamanagement.repositories;

import com.example.datamanagement.dto.EmployeDto;
import com.example.datamanagement.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Repository
@EnableJpaRepositories
public interface EmployeeRepository extends JpaRepository<Employee, Long>, CrudRepository<Employee,Long> {
public Optional<Employee> findByNameAndPass(String name, String pass);
public Optional<Employee> findByEmailAndPass(String email, String pass);
public  List<Optional<Employee>> findByName(String name);

/*@Query("delete from employe emp where emp.name = ?1")
public  void deleteEmployeByName(String pname);*/

//@Modifying
//@Query("delete from employe where name := pname")
//public  void deleteEmployeByName(@Param("pname") String pname);
//public  void deleteEmployeByName(String pname);
}