package com.kodlamaio.hrmsDemo6.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodlamaio.hrmsDemo6.business.abstracts.EmailConfirmToJobSeekerService;
import com.kodlamaio.hrmsDemo6.core.utilities.result.concretes.DataResult;
import com.kodlamaio.hrmsDemo6.core.utilities.result.concretes.Result;
import com.kodlamaio.hrmsDemo6.core.utilities.result.concretes.SuccessDataResult;
import com.kodlamaio.hrmsDemo6.core.utilities.result.concretes.SuccessResult;
import com.kodlamaio.hrmsDemo6.dataAccess.abstracts.EmailConfirmToJobSeekerDao;
import com.kodlamaio.hrmsDemo6.entities.concretes.EmailConfirmToJobSeeker;

@Service
public class EmailConfirmToJobSeekerManager implements EmailConfirmToJobSeekerService {
	
	private EmailConfirmToJobSeekerDao emailConfirmToJobSeekerDao;
	
	@Autowired
	public EmailConfirmToJobSeekerManager(EmailConfirmToJobSeekerDao emailConfirmToJobSeekerDao) {
		this.emailConfirmToJobSeekerDao = emailConfirmToJobSeekerDao;
	}

	@Override
	public DataResult<List<EmailConfirmToJobSeeker>> getAll() {
		return new SuccessDataResult<List<EmailConfirmToJobSeeker>>("Email confirm to job seekers listed successfully.", this.emailConfirmToJobSeekerDao.findAll());
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
}
