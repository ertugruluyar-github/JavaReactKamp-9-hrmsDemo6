package com.kodlamaio.hrmsDemo6.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kodlamaio.hrmsDemo6.entities.concretes.CurriculumVitae;

@Repository
public interface CurriculumVitaeDao extends JpaRepository<CurriculumVitae, Integer> {
	List<CurriculumVitae> findByJobSeeker_id(Integer id);
}
