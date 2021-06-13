package com.kodlamaio.hrmsDemo6.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kodlamaio.hrmsDemo6.entities.concretes.WorkingTimeType;

public interface WorkingTimeTypeDao extends JpaRepository<WorkingTimeType, Integer> {
	List<WorkingTimeType> findByType(String type);
}
