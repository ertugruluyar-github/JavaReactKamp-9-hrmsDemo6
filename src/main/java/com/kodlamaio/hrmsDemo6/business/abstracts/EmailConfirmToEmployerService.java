package com.kodlamaio.hrmsDemo6.business.abstracts;

import java.util.List;

import com.kodlamaio.hrmsDemo6.core.utilities.result.concretes.DataResult;
import com.kodlamaio.hrmsDemo6.core.utilities.result.concretes.Result;
import com.kodlamaio.hrmsDemo6.entities.concretes.EmailConfirmToEmployer;
import com.kodlamaio.hrmsDemo6.entities.concretes.Employer;

public interface EmailConfirmToEmployerService {
	DataResult<List<EmailConfirmToEmployer>> getAll();
	DataResult<EmailConfirmToEmployer> get(int id);
	DataResult<List<EmailConfirmToEmployer>> getAllByEmployerId(int id);
	DataResult<EmailConfirmToEmployer> getFirstByEmployerIdOrderByDateOfConfirmDesc(int id);
	
	Result add(EmailConfirmToEmployer emailConfirmToEmployer);
	Result delete(int id);
	Result deleteByEmployerId(int id);
	Result update(EmailConfirmToEmployer emailConfirmToEmployer);
	
	Result confirmEmployer(Employer employer);
	
}
