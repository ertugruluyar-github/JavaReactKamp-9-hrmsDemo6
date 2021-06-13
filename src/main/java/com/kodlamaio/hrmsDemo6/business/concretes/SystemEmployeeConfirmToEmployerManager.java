package com.kodlamaio.hrmsDemo6.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodlamaio.hrmsDemo6.business.abstracts.SystemEmployeeConfirmToEmployerService;
import com.kodlamaio.hrmsDemo6.core.utilities.result.concretes.DataResult;
import com.kodlamaio.hrmsDemo6.core.utilities.result.concretes.Result;
import com.kodlamaio.hrmsDemo6.core.utilities.result.concretes.SuccessDataResult;
import com.kodlamaio.hrmsDemo6.core.utilities.result.concretes.SuccessResult;
import com.kodlamaio.hrmsDemo6.dataAccess.abstracts.SystemEmployeeConfirmToEmployerDao;
import com.kodlamaio.hrmsDemo6.entities.concretes.SystemEmployeeConfirmToEmployer;

@Service
public class SystemEmployeeConfirmToEmployerManager implements SystemEmployeeConfirmToEmployerService {
	
	private SystemEmployeeConfirmToEmployerDao systemEmployeeConfirmToEmployerDao;
	
	@Autowired
	public SystemEmployeeConfirmToEmployerManager(SystemEmployeeConfirmToEmployerDao systemEmployeeConfirmToEmployerDao) {
		this.systemEmployeeConfirmToEmployerDao = systemEmployeeConfirmToEmployerDao;
	}

	@Override
	public DataResult<List<SystemEmployeeConfirmToEmployer>> getAll() {
		return new SuccessDataResult<List<SystemEmployeeConfirmToEmployer>>("System employee to employers listed successfully.", this.systemEmployeeConfirmToEmployerDao.findAll());
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
}
