package com.kodlamaio.hrmsDemo6.core.validators.emailVerify.abstracts;

public abstract class JobSeekerEmailVerifyService implements EmailVerifyService {

	@Override // default verify
	public boolean hasVerifyEmail(String email) {
		return true;
	}

}
