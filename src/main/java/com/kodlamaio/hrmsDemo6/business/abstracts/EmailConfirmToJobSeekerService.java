package com.kodlamaio.hrmsDemo6.business.abstracts;

import java.util.List;

import com.kodlamaio.hrmsDemo6.core.utilities.result.concretes.DataResult;
import com.kodlamaio.hrmsDemo6.core.utilities.result.concretes.Result;
import com.kodlamaio.hrmsDemo6.entities.concretes.EmailConfirmToJobSeeker;

public interface EmailConfirmToJobSeekerService {
	DataResult<List<EmailConfirmToJobSeeker>> getAll();
	DataResult<EmailConfirmToJobSeeker> getByJobSeekerId(int id);
	
	Result add(EmailConfirmToJobSeeker emailConfirmToJobSeeker);
	Result delete(int id);
	Result update(EmailConfirmToJobSeeker emailConfirmToJobSeeker);
}
