package com.example.datamanagement.repositories;

import com.example.datamanagement.entities.BadgingEmploye;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BadgingEmpRepository extends JpaRepository<BadgingEmploye,Long>
 {
     List<BadgingEmploye> findByEmpName(String name);
 }
