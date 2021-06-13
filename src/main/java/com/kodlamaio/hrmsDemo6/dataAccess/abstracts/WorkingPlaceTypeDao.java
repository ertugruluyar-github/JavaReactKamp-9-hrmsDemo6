package com.kodlamaio.hrmsDemo6.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kodlamaio.hrmsDemo6.entities.concretes.WorkingPlaceType;

public interface WorkingPlaceTypeDao extends JpaRepository<WorkingPlaceType, Integer> {
	List<WorkingPlaceType> findByType(String Type);
}
