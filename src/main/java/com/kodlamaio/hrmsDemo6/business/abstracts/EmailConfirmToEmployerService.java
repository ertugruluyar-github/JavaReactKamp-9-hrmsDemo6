package com.kodlamaio.hrmsDemo6.business.abstracts;

import java.util.List;

import com.kodlamaio.hrmsDemo6.core.utilities.result.concretes.DataResult;
import com.kodlamaio.hrmsDemo6.core.utilities.result.concretes.Result;
import com.kodlamaio.hrmsDemo6.entities.concretes.EmailConfirmToEmployer;

public interface EmailConfirmToEmployerService {
	DataResult<List<EmailConfirmToEmployer>> getAll();
	DataResult<EmailConfirmToEmployer> getByEmployerId(int id);
	
	Result add(EmailConfirmToEmployer emailConfirmToEmployer);
	Result delete(int id);
	Result update(EmailConfirmToEmployer emailConfirmToEmployer);
}
