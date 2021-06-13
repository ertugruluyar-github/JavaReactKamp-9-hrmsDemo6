package com.kodlamaio.hrmsDemo6.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kodlamaio.hrmsDemo6.entities.concretes.EmailConfirmToEmployer;

public interface EmailConfirmToEmployerDao extends JpaRepository<EmailConfirmToEmployer, Integer> {
	EmailConfirmToEmployer findByEmployer_Id(int id);
}
