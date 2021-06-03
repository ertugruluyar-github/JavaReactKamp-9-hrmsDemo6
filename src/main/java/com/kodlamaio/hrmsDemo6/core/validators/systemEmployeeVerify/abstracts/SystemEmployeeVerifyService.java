package com.kodlamaio.hrmsDemo6.core.validators.systemEmployeeVerify.abstracts;

import com.kodlamaio.hrmsDemo6.entities.concretes.Employer;

public interface SystemEmployeeVerifyService {
	boolean hasVerifyBySystemEmployee(Employer employer);
}
