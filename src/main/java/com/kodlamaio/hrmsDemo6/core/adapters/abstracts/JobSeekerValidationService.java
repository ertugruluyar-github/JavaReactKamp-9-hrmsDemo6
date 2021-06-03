package com.kodlamaio.hrmsDemo6.core.adapters.abstracts;

import org.springframework.stereotype.Service;

import com.kodlamaio.hrmsDemo6.entities.concretes.JobSeeker;

@Service
public interface JobSeekerValidationService {
	boolean isRealPerson(JobSeeker jobSeeker);
}
