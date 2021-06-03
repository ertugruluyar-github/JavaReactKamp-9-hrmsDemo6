package com.kodlamaio.hrmsDemo6.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodlamaio.hrmsDemo6.business.abstracts.EmployerService;
import com.kodlamaio.hrmsDemo6.core.utilities.result.concretes.DataResult;
import com.kodlamaio.hrmsDemo6.core.utilities.result.concretes.ErrorDataResult;
import com.kodlamaio.hrmsDemo6.core.utilities.result.concretes.ErrorResult;
import com.kodlamaio.hrmsDemo6.core.utilities.result.concretes.Result;
import com.kodlamaio.hrmsDemo6.core.utilities.result.concretes.SuccessDataResult;
import com.kodlamaio.hrmsDemo6.core.utilities.result.concretes.SuccessResult;
import com.kodlamaio.hrmsDemo6.core.validators.emailRegex.abstracts.EmployerEmailRegexValidatorService;
import com.kodlamaio.hrmsDemo6.core.validators.emailVerify.abstracts.EmployerEmailVerifyService;
import com.kodlamaio.hrmsDemo6.core.validators.systemEmployeeVerify.abstracts.EmployerSystemEmployeeVerifyService;
import com.kodlamaio.hrmsDemo6.dataAccess.abstracts.EmployerDao;
import com.kodlamaio.hrmsDemo6.entities.concretes.Employer;

@Service
public class EmployerManager implements EmployerService {

	private EmployerDao employerDao;
	private EmployerEmailRegexValidatorService employerEmailRegexValidatorService;
	private EmployerEmailVerifyService employerEmailVerifyService;
	private EmployerSystemEmployeeVerifyService employerSystemEmployeeVerifyService;

	@Autowired
	public EmployerManager(EmployerDao employerDao,
			EmployerEmailRegexValidatorService employerEmailRegexValidatorService, 
			EmployerEmailVerifyService employerEmailVerifyService, 
			EmployerSystemEmployeeVerifyService employerSystemEmployeeVerifyService) {
		this.employerDao = employerDao;
		this.employerEmailRegexValidatorService = employerEmailRegexValidatorService;
		this.employerEmailVerifyService = employerEmailVerifyService;
		this.employerSystemEmployeeVerifyService = employerSystemEmployeeVerifyService;
	}

	@Override
	public DataResult<List<Employer>> getAll() {
		return new SuccessDataResult<List<Employer>>("Employers listed successfully.", this.employerDao.findAll());
	}

	@Override
	public DataResult<Employer> get(int id) {
		if (this.employerDao.findById(id).orElse(null) != null) {
			return new SuccessDataResult<Employer>("The specified employer was found successfully.",
					this.employerDao.findById(id).get());
		} else {
			return new ErrorDataResult<Employer>("The specified employer is not available.");
		}
	}

	@Override
	public Result add(Employer employer) {
		if (!this.employerEmailRegexValidatorService.isValidEmail(employer.getEmail(),
				employer.getWebSite())) {
			return new ErrorResult("Email must have the same domain as the web site.");
		} else if (this.existsEmployerByEmail(employer.getEmail())) {
			return new ErrorResult("There is an employer record with this email.");
		} else if (!this.employerEmailVerifyService.hasVerifyEmail(employer.getEmail())) {
			return new ErrorResult("Email not verified!");
		} else if (!this.employerSystemEmployeeVerifyService.hasVerifyBySystemEmployee(employer)) {
			return new ErrorResult("The employer has not been verified by the system!");
		} else {
			this.employerDao.save(employer);
			return new SuccessResult("Employer added successfully.");
		}
	}

	@Override
	public Result delete(int id) {
		this.employerDao.deleteById(id);
		return new SuccessResult("Employer deleted successfully.");
	}

	@Override
	public Result update(Employer employer) {
		this.employerDao.save(employer);
		return new SuccessResult("Employer updated successfully.");
	}

	@Override
	public boolean existsEmployerByEmail(String email) {
		return this.employerDao.existsEmployerByEmail(email);
	}

//	@Override
//	public boolean hasEmptyField(Employer employer) {
//		if (employer.getCompanyName().isEmpty() || employer.getWebSite().isEmpty()
//				|| employer.getEmail().isEmpty() || employer.getPassword().isEmpty()) {
//			return true;
//		} else {
//			return false;
//		}
//	}

}
