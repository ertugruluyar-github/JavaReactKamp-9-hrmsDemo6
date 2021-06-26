package com.kodlamaio.hrmsDemo6.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kodlamaio.hrmsDemo6.entities.concretes.EmailConfirmToEmployer;

public interface EmailConfirmToEmployerDao extends JpaRepository<EmailConfirmToEmployer, Integer> {
	List<EmailConfirmToEmployer> findByEmployer_Id(int id);
	EmailConfirmToEmployer findFirstByEmployer_IdOrderByDateOfConfirmDesc(int id);
	long deleteByEmployer_Id(int id);
}
