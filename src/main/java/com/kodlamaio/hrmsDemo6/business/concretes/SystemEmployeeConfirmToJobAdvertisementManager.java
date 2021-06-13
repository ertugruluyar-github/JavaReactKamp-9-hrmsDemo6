package com.kodlamaio.hrmsDemo6.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodlamaio.hrmsDemo6.business.abstracts.SystemEmployeeConfirmToJobAdvertisementService;
import com.kodlamaio.hrmsDemo6.core.utilities.result.concretes.DataResult;
import com.kodlamaio.hrmsDemo6.core.utilities.result.concretes.ErrorDataResult;
import com.kodlamaio.hrmsDemo6.core.utilities.result.concretes.Result;
import com.kodlamaio.hrmsDemo6.core.utilities.result.concretes.SuccessDataResult;
import com.kodlamaio.hrmsDemo6.core.utilities.result.concretes.SuccessResult;
import com.kodlamaio.hrmsDemo6.dataAccess.abstracts.JobAdvertisementDao;
import com.kodlamaio.hrmsDemo6.dataAccess.abstracts.SystemEmployeeConfirmToJobAdvertisementDao;
import com.kodlamaio.hrmsDemo6.entities.concretes.JobAdvertisement;
import com.kodlamaio.hrmsDemo6.entities.concretes.SystemEmployeeConfirmToJobAdvertisement;

@Service
public class SystemEmployeeConfirmToJobAdvertisementManager implements SystemEmployeeConfirmToJobAdvertisementService {
	
	private SystemEmployeeConfirmToJobAdvertisementDao systemEmployeeConfirmToJobAdvertisementDao;
	private JobAdvertisementDao jobAdvertisementDao;
	
	@Autowired
	public SystemEmployeeConfirmToJobAdvertisementManager(SystemEmployeeConfirmToJobAdvertisementDao systemEmployeeConfirmToJobAdvertisementDao, JobAdvertisementDao jobAdvertisementDao) {
		this.systemEmployeeConfirmToJobAdvertisementDao = systemEmployeeConfirmToJobAdvertisementDao;
		this.jobAdvertisementDao = jobAdvertisementDao;
	}

	@Override
	public DataResult<List<SystemEmployeeConfirmToJobAdvertisement>> getAll() {
		return new SuccessDataResult<List<SystemEmployeeConfirmToJobAdvertisement>>("System employee confirm to job advertisements listed successfully.", this.systemEmployeeConfirmToJobAdvertisementDao.findAll());
	}
	
	@Override
	public DataResult<SystemEmployeeConfirmToJobAdvertisement> get(int id) {
		if (this.systemEmployeeConfirmToJobAdvertisementDao.findById(id).orElse(null) != null) {
			return new SuccessDataResult<SystemEmployeeConfirmToJobAdvertisement>("The specified system employee confirm to job advertisement was found successfully.",
					this.systemEmployeeConfirmToJobAdvertisementDao.findById(id).get());
		} else {
			return new ErrorDataResult<SystemEmployeeConfirmToJobAdvertisement>("The specified system employee confirm to job advertisement is not available.");
		}
	}

	@Override
	public DataResult<SystemEmployeeConfirmToJobAdvertisement> getByEmployerId(int id) {
		return new SuccessDataResult<SystemEmployeeConfirmToJobAdvertisement>("The system employee confirm to job advertisement got by employer id successfully.", this.systemEmployeeConfirmToJobAdvertisementDao.findByJobAdvertisement_Id(id));
	}

	@Override
	public Result add(SystemEmployeeConfirmToJobAdvertisement systemEmployeeConfirmToJobAdvertisement) {
		this.systemEmployeeConfirmToJobAdvertisementDao.save(systemEmployeeConfirmToJobAdvertisement);
		return new SuccessResult("System employee confirm to job advertisement added successfully.");
	}

	@Override
	public Result delete(int id) {
		this.systemEmployeeConfirmToJobAdvertisementDao.deleteById(id);
		return new SuccessResult("System employee confirm to job advertisement deleted successfully.");
	}

	@Override
	public Result update(SystemEmployeeConfirmToJobAdvertisement systemEmployeeConfirmToJobAdvertisement) {
		this.systemEmployeeConfirmToJobAdvertisementDao.save(systemEmployeeConfirmToJobAdvertisement);
		return new SuccessResult("System employee confirm to job advertisement updated successfully.");
	}

	@Override
	public Result confirmJobAdvertisement(int jobAdvertisementId) {
		JobAdvertisement jobAdvertisement = this.jobAdvertisementDao.findById(jobAdvertisementId).get();
		SystemEmployeeConfirmToJobAdvertisement confirm = new SystemEmployeeConfirmToJobAdvertisement();
		confirm.setJobAdvertisement(jobAdvertisement);
		confirm.setConfirm(true);
		this.systemEmployeeConfirmToJobAdvertisementDao.save(confirm);
		return new SuccessResult("Job advertisement confirmed by system employee successfully.");
	}
	
}
