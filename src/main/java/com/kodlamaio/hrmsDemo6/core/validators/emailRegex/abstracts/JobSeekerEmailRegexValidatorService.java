package com.kodlamaio.hrmsDemo6.core.validators.emailRegex.abstracts;

public abstract class JobSeekerEmailRegexValidatorService implements EmailRegexValidatorService {
	// Default Regex doğrulaması
	public boolean isValidEmail(String email) {
		return true;
	}
}
