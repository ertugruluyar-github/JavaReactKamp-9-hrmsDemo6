package com.kodlamaio.hrmsDemo6.business.abstracts;

import java.util.List;

import com.kodlamaio.hrmsDemo6.core.utilities.result.concretes.DataResult;
import com.kodlamaio.hrmsDemo6.core.utilities.result.concretes.Result;
import com.kodlamaio.hrmsDemo6.entities.concretes.EmailConfirmToJobSeeker;
import com.kodlamaio.hrmsDemo6.entities.concretes.JobSeeker;

public interface EmailConfirmToJobSeekerService {
	DataResult<List<EmailConfirmToJobSeeker>> getAll();
	DataResult<EmailConfirmToJobSeeker> get(int id);
	DataResult<List<EmailConfirmToJobSeeker>> getAllByJobSeekerId(int id);
	DataResult<EmailConfirmToJobSeeker> getFirstByJobSeekerIdOrderByDateOfConfirmDesc(int id);
	
	Result add(EmailConfirmToJobSeeker emailConfirmToJobSeeker);
	Result delete(int id);
	Result deleteByJobSeekerId(int id);
	Result update(EmailConfirmToJobSeeker emailConfirmToJobSeeker);
	
	Result confirmJobSeeker(JobSeeker jobSeeker);
	
}
