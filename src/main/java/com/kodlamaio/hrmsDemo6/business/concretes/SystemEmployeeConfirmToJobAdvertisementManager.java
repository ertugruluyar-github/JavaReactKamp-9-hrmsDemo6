package com.kodlamaio.hrmsDemo6.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodlamaio.hrmsDemo6.business.abstracts.SystemEmployeeConfirmToJobAdvertisementService;
import com.kodlamaio.hrmsDemo6.core.utilities.result.concretes.DataResult;
import com.kodlamaio.hrmsDemo6.core.utilities.result.concretes.ErrorDataResult;
import com.kodlamaio.hrmsDemo6.core.utilities.result.concretes.ErrorResult;
import com.kodlamaio.hrmsDemo6.core.utilities.result.concretes.Result;
import com.kodlamaio.hrmsDemo6.core.utilities.result.concretes.SuccessDataResult;
import com.kodlamaio.hrmsDemo6.core.utilities.result.concretes.SuccessResult;
import com.kodlamaio.hrmsDemo6.dataAccess.abstracts.JobAdvertisementDao;
import com.kodlamaio.hrmsDemo6.dataAccess.abstracts.SystemEmployeeConfirmToJobAdvertisementDao;
import com.kodlamaio.hrmsDemo6.dataAccess.abstracts.SystemEmployeeDao;
import com.kodlamaio.hrmsDemo6.entities.concretes.JobAdvertisement;
import com.kodlamaio.hrmsDemo6.entities.concretes.SystemEmployee;
import com.kodlamaio.hrmsDemo6.entities.concretes.SystemEmployeeConfirmToJobAdvertisement;

@Service
public class SystemEmployeeConfirmToJobAdvertisementManager implements SystemEmployeeConfirmToJobAdvertisementService {

	private SystemEmployeeConfirmToJobAdvertisementDao systemEmployeeConfirmToJobAdvertisementDao;
	private JobAdvertisementDao jobAdvertisementDao;
	private SystemEmployeeDao systemEmployeeDao;

	@Autowired
	public SystemEmployeeConfirmToJobAdvertisementManager(
			SystemEmployeeConfirmToJobAdvertisementDao systemEmployeeConfirmToJobAdvertisementDao,
			JobAdvertisementDao jobAdvertisementDao, SystemEmployeeDao systemEmployeeDao) {
		this.systemEmployeeConfirmToJobAdvertisementDao = systemEmployeeConfirmToJobAdvertisementDao;
		this.jobAdvertisementDao = jobAdvertisementDao;
		this.systemEmployeeDao = systemEmployeeDao;
	}

	@Override
	public DataResult<List<SystemEmployeeConfirmToJobAdvertisement>> getAll() {
		return new SuccessDataResult<List<SystemEmployeeConfirmToJobAdvertisement>>(
				"System employee confirms to job advertisement listed successfully.",
				this.systemEmployeeConfirmToJobAdvertisementDao.findAll());
	}

	@Override
	public DataResult<SystemEmployeeConfirmToJobAdvertisement> get(int id) {
		if (this.systemEmployeeConfirmToJobAdvertisementDao.findById(id).orElse(null) != null) {
			return new SuccessDataResult<SystemEmployeeConfirmToJobAdvertisement>(
					"The specified system employee confirm to job advertisement was found successfully.",
					this.systemEmployeeConfirmToJobAdvertisementDao.findById(id).get());
		} else {
			return new ErrorDataResult<SystemEmployeeConfirmToJobAdvertisement>(
					"The specified system employee confirm to job advertisement is not available.");
		}
	}

	@Override
	public DataResult<SystemEmployeeConfirmToJobAdvertisement> getByJobAdvertisementId(int id) {
		SystemEmployeeConfirmToJobAdvertisement confirm = this.systemEmployeeConfirmToJobAdvertisementDao
				.findByJobAdvertisement_Id(id);

		if (confirm != null) {
			return new SuccessDataResult<SystemEmployeeConfirmToJobAdvertisement>(
					"The specified system employee confirms to job advertisement got by job advertisement id successfully.",
					confirm);
		} else {
			return new ErrorDataResult<SystemEmployeeConfirmToJobAdvertisement>(
					"The specified system employee confirms to job advertisement are not available.", confirm);
		}
	}

//	@Override
//	public DataResult<SystemEmployeeConfirmToJobAdvertisement> getFirstByJobAdvertisementIdOrderByDateOfConfirmDesc(
//			int id) {
//		SystemEmployeeConfirmToJobAdvertisement confirm = this.systemEmployeeConfirmToJobAdvertisementDao
//				.findFirstByJobAdvertisement_IdOrderByDateOfConfirmDesc(id);
//
//		if (confirm != null) {
//			return new SuccessDataResult<SystemEmployeeConfirmToJobAdvertisement>(
//					"The system employee confirm to job advertisement got by job advertisement id successfully.",
//					confirm);
//		} else {
//			return new ErrorDataResult<SystemEmployeeConfirmToJobAdvertisement>(
//					"The system employee confirm to job advertisement is not available.", confirm);
//		}
//	}

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
	public Result deleteByJobAdvertisementId(int id) {
		long countOfDeleted = this.systemEmployeeConfirmToJobAdvertisementDao.deleteByJobAdvertisement_Id(id);
		return new SuccessResult(countOfDeleted
				+ " system employee confirms to job advertisement deleted by job advertisement id successfully.");
	}

	@Override
	public Result update(SystemEmployeeConfirmToJobAdvertisement systemEmployeeConfirmToJobAdvertisement) {
		this.systemEmployeeConfirmToJobAdvertisementDao.save(systemEmployeeConfirmToJobAdvertisement);
		return new SuccessResult("System employee confirm to job advertisement updated successfully.");
	}

	@Override
	public Result confirmJobAdvertisement(int systemEmployeeId, int jobAdvertisementId) {
		JobAdvertisement currentJobAdvertisement = this.jobAdvertisementDao.findById(jobAdvertisementId).orElse(null);
		SystemEmployee systemEmployeeWhoConfirmToJobAdvertisement = this.systemEmployeeDao.findById(systemEmployeeId)
				.orElse(null);
		SystemEmployeeConfirmToJobAdvertisement systemEmployeeConfirmToJobAdvertisement = this.systemEmployeeConfirmToJobAdvertisementDao
				.findByJobAdvertisement_Id(jobAdvertisementId);
		
		if (currentJobAdvertisement == null) {
			return new ErrorResult("The job advertisement not exist.");
		} else if (systemEmployeeWhoConfirmToJobAdvertisement == null) {
			return new ErrorResult("The system employee not exist.");
		} else if (systemEmployeeConfirmToJobAdvertisement == null) {
			return new ErrorResult("The system employee confirm to job advertisement not exist.");
		} else {
			systemEmployeeConfirmToJobAdvertisement.setConfirmStatus(true);
			this.systemEmployeeConfirmToJobAdvertisementDao.save(systemEmployeeConfirmToJobAdvertisement);
			return new SuccessResult("Job advertisement confirmed by system employee successfully.");
		}
	}

}
