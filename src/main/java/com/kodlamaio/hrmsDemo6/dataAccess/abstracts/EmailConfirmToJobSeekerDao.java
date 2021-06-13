package com.kodlamaio.hrmsDemo6.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kodlamaio.hrmsDemo6.entities.concretes.EmailConfirmToJobSeeker;

public interface EmailConfirmToJobSeekerDao extends JpaRepository<EmailConfirmToJobSeeker, Integer> {
	EmailConfirmToJobSeeker findByEmployer_Id(int id);
}
