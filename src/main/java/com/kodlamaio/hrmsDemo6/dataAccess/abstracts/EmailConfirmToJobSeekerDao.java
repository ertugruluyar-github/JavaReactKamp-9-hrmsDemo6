package com.kodlamaio.hrmsDemo6.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kodlamaio.hrmsDemo6.entities.concretes.EmailConfirmToJobSeeker;

public interface EmailConfirmToJobSeekerDao extends JpaRepository<EmailConfirmToJobSeeker, Integer> {
	List<EmailConfirmToJobSeeker> findByJobSeeker_Id(int id);
	EmailConfirmToJobSeeker findFirstByJobSeeker_IdOrderByDateOfConfirmDesc(int id);
	long deleteByJobSeeker_Id(int id);
}
