package com.kodlamaio.hrmsDemo6.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodlamaio.hrmsDemo6.business.abstracts.EmailConfirmToEmployerService;
import com.kodlamaio.hrmsDemo6.core.utilities.result.concretes.DataResult;
import com.kodlamaio.hrmsDemo6.core.utilities.result.concretes.ErrorDataResult;
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
		return new SuccessDataResult<List<EmailConfirmToEmployer>>("Email confirm to employers listed successfully.",
				this.emailConfirmToEmployerDao.findAll());
	}

	@Override
	public DataResult<EmailConfirmToEmployer> get(int id) {
		if (this.emailConfirmToEmployerDao.findById(id).orElse(null) != null) {
			return new SuccessDataResult<EmailConfirmToEmployer>(
					"The specified email confirm to employer was found successfully.",
					this.emailConfirmToEmployerDao.findById(id).get());
		} else {
			return new ErrorDataResult<EmailConfirmToEmployer>(
					"The specified email confirm to employer is not available.");
		}
	}

	@Override
	public DataResult<List<EmailConfirmToEmployer>> getAllByEmployerId(int id) {
		List<EmailConfirmToEmployer> confirms = this.emailConfirmToEmployerDao.findByEmployer_Id(id);

		if (!confirms.isEmpty()) {
			return new SuccessDataResult<List<EmailConfirmToEmployer>>(
					"The specified email confirms to employer got by employer id successfully.", confirms);
		} else {
			return new ErrorDataResult<List<EmailConfirmToEmployer>>(
					"The specified email confirms to employer are not available.", confirms);
		}
	}

	@Override
	public DataResult<EmailConfirmToEmployer> getFirstByEmployerIdOrderByDateOfConfirmDesc(int id) {
		EmailConfirmToEmployer confirm = this.emailConfirmToEmployerDao
				.findFirstByEmployer_IdOrderByDateOfConfirmDesc(id);

		if (confirm != null) {
			return new SuccessDataResult<EmailConfirmToEmployer>(
					"The specified email confirm to employer got by employer id successfully.", confirm);
		} else {
			return new ErrorDataResult<EmailConfirmToEmployer>(
					"The specified email confirm to employer is not available.", confirm);
		}
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
	public Result deleteByEmployerId(int id) {
		long countOfDeleted = this.emailConfirmToEmployerDao.deleteByEmployer_Id(id);
		return new SuccessResult(countOfDeleted + " email confirms to employer deleted successfully.");
	}

	@Override
	public Result update(EmailConfirmToEmployer emailConfirmToEmployer) {
		this.emailConfirmToEmployerDao.save(emailConfirmToEmployer);
		return new SuccessResult("Email confirm to employer updated successfully.");
	}

	@Override
	public Result confirmEmployer(int employerId) {
		EmailConfirmToEmployer latestConfirm = this.emailConfirmToEmployerDao
				.findFirstByEmployer_IdOrderByDateOfConfirmDesc(employerId);
		latestConfirm.setConfirm(true);
		this.emailConfirmToEmployerDao.save(latestConfirm);
		return new SuccessResult("Employer's email confirmed successfully.");
	}

}
