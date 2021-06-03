package com.kodlamaio.hrmsDemo6.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kodlamaio.hrmsDemo6.entities.concretes.EmployeePosition;

@Repository
public interface EmployeePositionDao extends JpaRepository<EmployeePosition, Integer> {
	boolean existsEmployeePositionByPositionNameIgnoreCase(String positionName);
}
