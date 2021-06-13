package com.kodlamaio.hrmsDemo6.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodlamaio.hrmsDemo6.business.abstracts.EmailConfirmToEmployerService;
import com.kodlamaio.hrmsDemo6.core.utilities.result.concretes.DataResult;
import com.kodlamaio.hrmsDemo6.core.utilities.result.concretes.Result;
import com.kodlamaio.hrmsDemo6.core.utilities.result.concretes.SuccessDataResult;
import com.kodlamaio.hrmsDemo6.core.utilities.result.concretes.SuccessResult;
import com.kodlamaio.hrmsDemo6.dataAccess.abstracts.EmailConfirmToEmployerDao;
import com.kodlamaio.hrmsDemo6.entities.concretes.EmailConfirmToEmployer;

@Service
public class EmailConfirmToEmployerManager implements EmailConfirmToEmployerService {
	
	private EmailConfirmToEmployerDao emailConfirmToEmployerDao;
	
	@Autowired
	public EmailConfirmToEmployerManager(EmailConfirmToEmployerDao emailConfirmToEmployerDao) {
		this.emailConfirmToEmployerDao = emailConfirmToEmployerDao;
	}

	@Override
	public DataResult<List<EmailConfirmToEmployer>> getAll() {
		return new SuccessDataResult<List<EmailConfirmToEmployer>>("Email confirm to employers listed successfully.", this.emailConfirmToEmployerDao.findAll());
	}

	@Override
	public DataResult<EmailConfirmToEmployer> getByEmployerId(int id) {
		return new SuccessDataResult<EmailConfirmToEmployer>("The email confirm to employer got successfully.", this.emailConfirmToEmployerDao.findByEmployer_Id(id));
	}

	@Override
	public Result add(EmailConfirmToEmployer emailConfirmToEmployer) {
		this.emailConfirmToEmployerDao.save(emailConfirmToEmployer);
		return new SuccessResult("Email confirm to employer added successfully.");
	}

	@Override
	public Result delete(int id) {
		this.emailConfirmToEmployerDao.deleteById(id);
		return new SuccessResult("Email confirm to employer deleted successfully.");
	}

	@Override
	public Result update(EmailConfirmToEmployer emailConfirmToEmployer) {
		this.emailConfirmToEmployerDao.save(emailConfirmToEmployer);
		return new SuccessResult("Email confirm to employer updated successfully.");
	}

}
