package com.kodlamaio.hrmsDemo6.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	public SystemEmployeeConfirmToEmployerManager(SystemEmployeeConfirmToEmployerDao systemEmployeeConfirmToEmployerDao, EmployerDao employerDao) {
		this.systemEmployeeConfirmToEmployerDao = systemEmployeeConfirmToEmployerDao;
		this.employerDao = employerDao;
	}

	@Override
	public DataResult<List<SystemEmployeeConfirmToEmployer>> getAll() {
		return new SuccessDataResult<List<SystemEmployeeConfirmToEmployer>>("System employee to employers listed successfully.", this.systemEmployeeConfirmToEmployerDao.findAll());
	}
	
	@Override
	public DataResult<SystemEmployeeConfirmToEmployer> get(int id) {
		if (this.systemEmployeeConfirmToEmployerDao.findById(id).orElse(null) != null) {
			return new SuccessDataResult<SystemEmployeeConfirmToEmployer>("The specified system employee confirm to employer was found successfully.",
					this.systemEmployeeConfirmToEmployerDao.findById(id).get());
		} else {
			return new ErrorDataResult<SystemEmployeeConfirmToEmployer>("The specified system employee confirm to employer is not available.");
		}
	}
	
	@Override
	public DataResult<SystemEmployeeConfirmToEmployer> getByEmployerId(int id) {
		return new SuccessDataResult<SystemEmployeeConfirmToEmployer>("The System employee confirm to employer got successfully.", this.systemEmployeeConfirmToEmployerDao.findByEmployer_Id(id));
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
	public Result update(SystemEmployeeConfirmToEmployer systemEmployeeConfirmToEmployer) {
		this.systemEmployeeConfirmToEmployerDao.save(systemEmployeeConfirmToEmployer);
		return new SuccessResult("System employee confirm to employer updated successfully.");
	}
	
	@Override
	public Result confirmEmployer(int employerId) {
		Employer employer = this.employerDao.findById(employerId).get();
		SystemEmployeeConfirmToEmployer confirm = new SystemEmployeeConfirmToEmployer();
		confirm.setEmployer(employer);
		confirm.setConfirm(true);
		this.systemEmployeeConfirmToEmployerDao.save(confirm);
		return new SuccessResult("Employer confirmed by system employee successfully.");
	}
	
}
