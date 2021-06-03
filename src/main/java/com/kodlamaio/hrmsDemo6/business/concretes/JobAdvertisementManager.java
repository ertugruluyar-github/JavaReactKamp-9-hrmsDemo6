package com.kodlamaio.hrmsDemo6.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodlamaio.hrmsDemo6.business.abstracts.JobAdvertisementService;
import com.kodlamaio.hrmsDemo6.core.utilities.result.concretes.DataResult;
import com.kodlamaio.hrmsDemo6.core.utilities.result.concretes.Result;
import com.kodlamaio.hrmsDemo6.core.utilities.result.concretes.SuccessDataResult;
import com.kodlamaio.hrmsDemo6.core.utilities.result.concretes.SuccessResult;
import com.kodlamaio.hrmsDemo6.dataAccess.abstracts.JobAdvertisementDao;
import com.kodlamaio.hrmsDemo6.entities.concretes.JobAdvertisement;

@Service
public class JobAdvertisementManager implements JobAdvertisementService {
	
	private JobAdvertisementDao jobAdvertisementDao;
	
	@Autowired
	public JobAdvertisementManager(JobAdvertisementDao jobAdvertisementDao) {
		this.jobAdvertisementDao = jobAdvertisementDao;
	}
	
	@Override
	public Result add(JobAdvertisement jobAdvertisement) {
		this.jobAdvertisementDao.save(jobAdvertisement);
		return new SuccessResult("Job advertisement added successfully.");
	}
	
	@Override
	public DataResult<List<JobAdvertisement>> getByActivated() {
		return new SuccessDataResult<List<JobAdvertisement>>("Active job advertisements listed successfully.", this.jobAdvertisementDao.findByActiveTrue());
	}

	@Override
	public DataResult<List<JobAdvertisement>> getByActivatedOrderByReleaseDateAsc() {
		return new SuccessDataResult<List<JobAdvertisement>>("Active job advertisements listed and ordered by release date (Asc) successfully.", this.jobAdvertisementDao.findByActiveTrueOrderByReleaseDateAsc());
	}

	@Override
	public DataResult<List<JobAdvertisement>> getByActivatedOrderByApplicationDeadlineAsc() {
		return new SuccessDataResult<List<JobAdvertisement>>("Active job advertisements listed and ordered by application deadline date (Asc) successfully.", this.jobAdvertisementDao.findByActiveTrueOrderByApplicationDeadlineAsc());
	}

	@Override
	public DataResult<List<JobAdvertisement>> getByActivatedAndEmployerId(Integer id) {
		return new SuccessDataResult<List<JobAdvertisement>>("The employer's active job advertisements listed successfully.", this.jobAdvertisementDao.findByActiveTrueAndEmployer_Id(id));
	}

	@Override
	public Result activateJobAdvertisement(Integer id) {
		JobAdvertisement j = this.jobAdvertisementDao.findById(id).get();
		j.setActive(true);
		this.jobAdvertisementDao.save(j);
		return new SuccessResult("Job advertisement activated successfully.");
	}

	@Override
	public Result deactivateJobAdvertisement(Integer id) {
		JobAdvertisement j = this.jobAdvertisementDao.findById(id).get();
		j.setActive(false);
		this.jobAdvertisementDao.save(j);
		return new SuccessResult("Job advertisement deactivated successfully.");
	}
	
}
