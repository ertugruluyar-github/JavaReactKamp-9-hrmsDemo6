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
import com.kodlamaio.hrmsDemo6.dataAccess.abstracts.JobSeekerDao;
import com.kodlamaio.hrmsDemo6.entities.concretes.EmailConfirmToJobSeeker;
import com.kodlamaio.hrmsDemo6.entities.concretes.JobSeeker;

@Service
public class EmailConfirmToJobSeekerManager implements EmailConfirmToJobSeekerService {
	
	private EmailConfirmToJobSeekerDao emailConfirmToJobSeekerDao;
	private JobSeekerDao jobSeekerDao;
	
	@Autowired
	public EmailConfirmToJobSeekerManager(EmailConfirmToJobSeekerDao emailConfirmToJobSeekerDao, JobSeekerDao jobSeekerDao) {
		this.emailConfirmToJobSeekerDao = emailConfirmToJobSeekerDao;
		this.jobSeekerDao = jobSeekerDao;
	}

	@Override
	public DataResult<List<EmailConfirmToJobSeeker>> getAll() {
		return new SuccessDataResult<List<EmailConfirmToJobSeeker>>("Email confirm to job seekers listed successfully.", this.emailConfirmToJobSeekerDao.findAll());
	}
	
	@Override
	public DataResult<EmailConfirmToJobSeeker> get(int id) {
		if (this.emailConfirmToJobSeekerDao.findById(id).orElse(null) != null) {
			return new SuccessDataResult<EmailConfirmToJobSeeker>("The specified email confirm to job seeker was found successfully.",
					this.emailConfirmToJobSeekerDao.findById(id).get());
		} else {
			return new ErrorDataResult<EmailConfirmToJobSeeker>("The specified email confirm to job seeker is not available.");
		}
	}

	@Override
	public DataResult<EmailConfirmToJobSeeker> getByJobSeekerId(int id) {
		return new SuccessDataResult<EmailConfirmToJobSeeker>("The email confirm to job seeker got successfully.", this.emailConfirmToJobSeekerDao.findByJobSeeker_Id(id));
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
	public Result update(EmailConfirmToJobSeeker emailConfirmToJobSeeker) {
		this.emailConfirmToJobSeekerDao.save(emailConfirmToJobSeeker);
		return new SuccessResult("Email confirm to job seeker updated successfully.");
	}
	
	@Override
	public Result confirmEmployer(int jobSeekerId) {
		JobSeeker jobSeeker = this.jobSeekerDao.findById(jobSeekerId).get();
		EmailConfirmToJobSeeker confirm = new EmailConfirmToJobSeeker();
		confirm.setJobSeeker(jobSeeker);
		confirm.setConfirm(true);
		this.emailConfirmToJobSeekerDao.save(confirm);
		return new SuccessResult("Job seeker confirmed by email successfully.");
	}
	
}
