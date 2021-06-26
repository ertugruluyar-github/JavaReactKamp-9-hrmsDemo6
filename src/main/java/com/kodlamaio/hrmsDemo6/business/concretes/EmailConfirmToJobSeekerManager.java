package com.kodlamaio.hrmsDemo6.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodlamaio.hrmsDemo6.business.abstracts.EmailConfirmToJobSeekerService;
import com.kodlamaio.hrmsDemo6.core.utilities.result.concretes.DataResult;
import com.kodlamaio.hrmsDemo6.core.utilities.result.concretes.ErrorDataResult;
import com.kodlamaio.hrmsDemo6.core.utilities.result.concretes.Result;
import com.kodlamaio.hrmsDemo6.core.utilities.result.concretes.SuccessDataResult;
import com.kodlamaio.hrmsDemo6.core.utilities.result.concretes.SuccessResult;
import com.kodlamaio.hrmsDemo6.dataAccess.abstracts.EmailConfirmToJobSeekerDao;
import com.kodlamaio.hrmsDemo6.entities.concretes.EmailConfirmToJobSeeker;
import com.kodlamaio.hrmsDemo6.entities.concretes.JobSeeker;

@Service
public class EmailConfirmToJobSeekerManager implements EmailConfirmToJobSeekerService {

	private EmailConfirmToJobSeekerDao emailConfirmToJobSeekerDao;

	@Autowired
	public EmailConfirmToJobSeekerManager(EmailConfirmToJobSeekerDao emailConfirmToJobSeekerDao) {
		this.emailConfirmToJobSeekerDao = emailConfirmToJobSeekerDao;
	}

	@Override
	public DataResult<List<EmailConfirmToJobSeeker>> getAll() {
		return new SuccessDataResult<List<EmailConfirmToJobSeeker>>("Email confirm to job seekers listed successfully.",
				this.emailConfirmToJobSeekerDao.findAll());
	}

	@Override
	public DataResult<EmailConfirmToJobSeeker> get(int id) {
		if (this.emailConfirmToJobSeekerDao.findById(id).orElse(null) != null) {
			return new SuccessDataResult<EmailConfirmToJobSeeker>(
					"The specified email confirm to job seeker was found successfully.",
					this.emailConfirmToJobSeekerDao.findById(id).get());
		} else {
			return new ErrorDataResult<EmailConfirmToJobSeeker>(
					"The specified email confirm to job seeker is not available.");
		}
	}

	@Override
	public DataResult<List<EmailConfirmToJobSeeker>> getAllByJobSeekerId(int id) {
		List<EmailConfirmToJobSeeker> confirms = this.emailConfirmToJobSeekerDao.findByJobSeeker_Id(id);

		if (!confirms.isEmpty()) {
			return new SuccessDataResult<List<EmailConfirmToJobSeeker>>(
					"The specified email confirms to job seeker got by job seeker id successfully.", confirms);
		} else {
			return new ErrorDataResult<List<EmailConfirmToJobSeeker>>(
					"The specified email confirms to job seeker are not available.", confirms);
		}
	}

	@Override
	public DataResult<EmailConfirmToJobSeeker> getFirstByJobSeekerIdOrderByDateOfConfirmDesc(int id) {
		EmailConfirmToJobSeeker confirm = this.emailConfirmToJobSeekerDao
				.findFirstByJobSeeker_IdOrderByDateOfConfirmDesc(id);

		if (confirm != null) {
			return new SuccessDataResult<EmailConfirmToJobSeeker>(
					"The specified email confirm to job seeker got by job seeker id successfully.", confirm);
		} else {
			return new ErrorDataResult<EmailConfirmToJobSeeker>(
					"The specified email confirm to job seeker is not available.", confirm);
		}
	}

	@Override
	public Result add(EmailConfirmToJobSeeker emailConfirmToJobSeeker) {
		this.emailConfirmToJobSeekerDao.save(emailConfirmToJobSeeker);
		return new SuccessResult("Email confirm to job seeker added successfully.");
	}

	@Override
	public Result delete(int id) {
		this.emailConfirmToJobSeekerDao.deleteById(id);
		return new SuccessResult("Email confirm to job seeker deleted successfully.");
	}

	@Override
	public Result deleteByJobSeekerId(int id) {
		long countOfDeleted = this.emailConfirmToJobSeekerDao.deleteByJobSeeker_Id(id);
		return new SuccessResult(countOfDeleted + " email confirms to job seeker deleted successfully.");
	}

	@Override
	public Result update(EmailConfirmToJobSeeker emailConfirmToJobSeeker) {
		this.emailConfirmToJobSeekerDao.save(emailConfirmToJobSeeker);
		return new SuccessResult("Email confirm to job seeker updated successfully.");
	}

	@Override
	public Result confirmJobSeeker(JobSeeker jobSeeker) {
		EmailConfirmToJobSeeker latestConfirm = this.emailConfirmToJobSeekerDao
				.findFirstByJobSeeker_IdOrderByDateOfConfirmDesc(jobSeeker.getId());
		latestConfirm.setConfirm(true);
		this.emailConfirmToJobSeekerDao.save(latestConfirm);
		return new SuccessResult("Job seeker's email confirmed successfully.");
	}

}
