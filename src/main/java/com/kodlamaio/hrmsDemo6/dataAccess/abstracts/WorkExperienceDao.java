package com.kodlamaio.hrmsDemo6.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kodlamaio.hrmsDemo6.entities.concretes.WorkExperience;

@Repository
public interface WorkExperienceDao extends JpaRepository<WorkExperience, Integer> {
	List<WorkExperience> findAllByOrderByEndDateDesc();
	List<WorkExperience> findByEndDateIsNull();
	List<WorkExperience> findByEndDateIsNotNullOrderByEndDateDesc();
}
