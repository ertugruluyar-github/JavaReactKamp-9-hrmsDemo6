package com.kodlamaio.hrmsDemo6.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kodlamaio.hrmsDemo6.entities.concretes.SystemEmployeeConfirmToEmployer;

public interface SystemEmployeeConfirmToEmployerDao extends JpaRepository<SystemEmployeeConfirmToEmployer, Integer> {
	SystemEmployeeConfirmToEmployer findByEmployer_Id(int id);
	//SystemEmployeeConfirmToEmployer findFirstByEmployer_IdOrderByDateOfConfirmDesc(int id);
	long deleteByEmployer_Id(int id);
}
