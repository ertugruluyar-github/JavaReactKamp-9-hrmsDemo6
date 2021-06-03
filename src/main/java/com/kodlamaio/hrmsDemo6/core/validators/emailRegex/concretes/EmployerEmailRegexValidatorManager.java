package com.kodlamaio.hrmsDemo6.core.validators.emailRegex.concretes;

import org.springframework.stereotype.Service;

import com.kodlamaio.hrmsDemo6.core.validators.emailRegex.abstracts.EmployerEmailRegexValidatorService;

@Service
public class EmployerEmailRegexValidatorManager extends EmployerEmailRegexValidatorService {
	// Default doğrulamaya da sahip superclass'dan.
	// email, web site ile aynı domain'e sahip mi? Ekstra metodu superclassdan geliyor.
}
