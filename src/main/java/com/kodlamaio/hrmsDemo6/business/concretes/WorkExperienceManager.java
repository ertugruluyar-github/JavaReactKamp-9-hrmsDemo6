package com.kodlamaio.hrmsDemo6.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodlamaio.hrmsDemo6.business.abstracts.WorkExperienceService;
import com.kodlamaio.hrmsDemo6.core.utilities.result.concretes.DataResult;
import com.kodlamaio.hrmsDemo6.core.utilities.result.concretes.Result;
import com.kodlamaio.hrmsDemo6.core.utilities.result.concretes.SuccessDataResult;
import com.kodlamaio.hrmsDemo6.dataAccess.abstracts.WorkExperienceDao;
import com.kodlamaio.hrmsDemo6.entities.concretes.WorkExperience;

@Service
public class WorkExperienceManager implements WorkExperienceService {
	
	private WorkExperienceDao workExperienceDao;
	
	@Autowired
	public WorkExperienceManager(WorkExperienceDao workExperienceDao) {
		this.workExperienceDao = workExperienceDao;
	}

	@Override
	public DataResult<List<WorkExperience>> getAll() {
		return new SuccessDataResult<List<WorkExperience>>("Work experiences listed succesfully.", this.workExperienceDao.findAll());
	}

	@Override
	public DataResult<WorkExperience> get(int id) {
		return new SuccessDataResult<WorkExperience>("Work experience got succesfully.", this.workExperienceDao.findById(id).get());
	}

	@Override
	public Result add(WorkExperience schoolDegree) {
		this.workExperienceDao.save(schoolDegree);
		return new SuccessDataResult<WorkExperience>("Work experience added succesfully.");
	}

	@Override
	public Result delete(int id) {
		this.workExperienceDao.deleteById(id);
		return new SuccessDataResult<WorkExperience>("Work experience deleted succesfully.");
	}

	@Override
	public Result update(WorkExperience schoolDegree) {
		this.workExperienceDao.save(schoolDegree);
		return new SuccessDataResult<WorkExperience>("Work experience updated succesfully.");
	}
	
	@Override
	public DataResult<List<WorkExperience>> getAllOrderByEndDateDesc() {
		return new SuccessDataResult<List<WorkExperience>>("Work experiences listed and ordered by end date (Desc) succesfully.", this.workExperienceDao.findAllByOrderByEndDateDesc());
	}

	@Override
	public DataResult<List<WorkExperience>> getByEndDateIsNull() {
		return new SuccessDataResult<List<WorkExperience>>("Work experiences in progress listed succesfully.", this.workExperienceDao.findByEndDateIsNull());
	}

	@Override
	public DataResult<List<WorkExperience>> getByEndDateIsNotNullOrderByEndDateDesc() {
		return new SuccessDataResult<List<WorkExperience>>("Finished work experiences listed and ordered by end date (Desc) succesfully.", this.workExperienceDao.findByEndDateIsNotNullOrderByEndDateDesc());
	}
	
}
