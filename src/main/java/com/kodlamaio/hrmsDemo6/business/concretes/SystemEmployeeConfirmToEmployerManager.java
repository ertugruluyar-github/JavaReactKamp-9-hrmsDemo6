package com.kodlamaio.hrmsDemo6.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kodlamaio.hrmsDemo6.business.abstracts.SystemEmployeeConfirmToEmployerService;
import com.kodlamaio.hrmsDemo6.core.utilities.result.concretes.DataResult;
import com.kodlamaio.hrmsDemo6.core.utilities.result.concretes.ErrorDataResult;
import com.kodlamaio.hrmsDemo6.core.utilities.result.concretes.Result;
import com.kodlamaio.hrmsDemo6.core.utilities.result.concretes.SuccessDataResult;
import com.kodlamaio.hrmsDemo6.core.utilities.result.concretes.SuccessResult;
import com.kodlamaio.hrmsDemo6.dataAccess.abstracts.EmployerDao;
import com.kodlamaio.hrmsDemo6.dataAccess.abstracts.SystemEmployeeConfirmToEmployerDao;
import com.kodlamaio.hrmsDemo6.entities.concretes.Employer;
import com.kodlamaio.hrmsDemo6.entities.concretes.SystemEmployeeConfirmToEmployer;

@Service
public class SystemEmployeeConfirmToEmployerManager implements SystemEmployeeConfirmToEmployerService {

	private SystemEmployeeConfirmToEmployerDao systemEmployeeConfirmToEmployerDao;
	private EmployerDao employerDao;

	@Autowired
	public SystemEmployeeConfirmToEmployerManager(
			SystemEmployeeConfirmToEmployerDao systemEmployeeConfirmToEmployerDao,
			EmployerDao employerDao) {
		this.systemEmployeeConfirmToEmployerDao = systemEmployeeConfirmToEmployerDao;
		this.employerDao = employerDao;
	}

	@Override
	public DataResult<List<SystemEmployeeConfirmToEmployer>> getAll() {
		return new SuccessDataResult<List<SystemEmployeeConfirmToEmployer>>(
				"System employee confirm to employers listed successfully.", this.systemEmployeeConfirmToEmployerDao.findAll());
	}

	@Override
	public DataResult<SystemEmployeeConfirmToEmployer> get(int id) {
		if (this.systemEmployeeConfirmToEmployerDao.findById(id).orElse(null) != null) {
			return new SuccessDataResult<SystemEmployeeConfirmToEmployer>(
					"The specified system employee confirm to employer was found successfully.",
					this.systemEmployeeConfirmToEmployerDao.findById(id).get());
		} else {
			return new ErrorDataResult<SystemEmployeeConfirmToEmployer>(
					"The specified system employee confirm to employer is not available.");
		}
	}

	@Override
	public DataResult<List<SystemEmployeeConfirmToEmployer>> getAllByEmployerId(int id) {
		List<SystemEmployeeConfirmToEmployer> confirms = this.systemEmployeeConfirmToEmployerDao.findByEmployer_Id(id);

		if (!confirms.isEmpty()) {
			return new SuccessDataResult<List<SystemEmployeeConfirmToEmployer>>(
					"The specified system employee confirms to employer got by employer id successfully.", confirms);
		} else {
			return new ErrorDataResult<List<SystemEmployeeConfirmToEmployer>>(
					"The specified system employee confirms to employer are not available.", confirms);
		}

	}

	@Override
	public DataResult<SystemEmployeeConfirmToEmployer> getFirstByEmployerIdOrderByDateOfConfirmDesc(int id) {
		SystemEmployeeConfirmToEmployer confirm = this.systemEmployeeConfirmToEmployerDao
				.findFirstByEmployer_IdOrderByDateOfConfirmDesc(id);

		if (confirm != null) {
			return new SuccessDataResult<SystemEmployeeConfirmToEmployer>(
					"The specified latest system employee confirm to employer got by employer id successfully.", confirm);
		} else {
			return new ErrorDataResult<SystemEmployeeConfirmToEmployer>(
					"The specified latest system employee confirm to employer is not available.", confirm);
		}
	}

	@Override
	public Result add(SystemEmployeeConfirmToEmployer systemEmployeeConfirmToEmployer) {
		this.systemEmployeeConfirmToEmployerDao.save(systemEmployeeConfirmToEmployer);
		return new SuccessResult("System employee confirm to employer added successfully.");
	}

	@Override
	public Result delete(int id) {
		this.systemEmployeeConfirmToEmployerDao.deleteById(id);
		return new SuccessResult("System employee confirm to employer deleted successfully.");
	}

	@Override
	public Result deleteByEmployerId(int id) {
		long countOfDeleted = this.systemEmployeeConfirmToEmployerDao.deleteByEmployer_Id(id);
		return new SuccessResult(countOfDeleted + " system employee confirms to employer deleted successfully.");
	}

	@Override
	public Result update(SystemEmployeeConfirmToEmployer systemEmployeeConfirmToEmployer) {
		this.systemEmployeeConfirmToEmployerDao.save(systemEmployeeConfirmToEmployer);
		return new SuccessResult("System employee confirm to employer updated successfully.");
	}

	@Override
	public Result confirmEmployer(int employerId) {
		SystemEmployeeConfirmToEmployer latestConfirm = this.systemEmployeeConfirmToEmployerDao
				.findFirstByEmployer_IdOrderByDateOfConfirmDesc(employerId);
		latestConfirm.setConfirm(true);
		this.systemEmployeeConfirmToEmployerDao.save(latestConfirm);
		String latestEmployerJsonString = this.employerDao.findById(employerId).get().getEmployerLastUpdateJsonString();
		try {
			Employer latestEmployer = new ObjectMapper().readValue(latestEmployerJsonString, Employer.class);
			this.employerDao.save(latestEmployer);
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return new SuccessResult("Employer confirmed by system employee successfully.");
	}

}
