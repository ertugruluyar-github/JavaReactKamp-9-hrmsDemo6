package com.kodlamaio.hrmsDemo6.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodlamaio.hrmsDemo6.business.abstracts.SystemEmployeeService;
import com.kodlamaio.hrmsDemo6.core.utilities.result.concretes.DataResult;
import com.kodlamaio.hrmsDemo6.core.utilities.result.concretes.ErrorDataResult;
import com.kodlamaio.hrmsDemo6.core.utilities.result.concretes.ErrorResult;
import com.kodlamaio.hrmsDemo6.core.utilities.result.concretes.Result;
import com.kodlamaio.hrmsDemo6.core.utilities.result.concretes.SuccessDataResult;
import com.kodlamaio.hrmsDemo6.core.utilities.result.concretes.SuccessResult;
import com.kodlamaio.hrmsDemo6.dataAccess.abstracts.SystemEmployeeDao;
import com.kodlamaio.hrmsDemo6.entities.concretes.SystemEmployee;

@Service
public class SystemEmployeeManager implements SystemEmployeeService {
	
	private SystemEmployeeDao systemEmployeeDao;
	
	@Autowired
	public SystemEmployeeManager(SystemEmployeeDao systemEmployeeDao) {
		this.systemEmployeeDao = systemEmployeeDao;
	}

	@Override
	public DataResult<List<SystemEmployee>> getAll() {
		return new SuccessDataResult<List<SystemEmployee>>("System employees listed successfully.", this.systemEmployeeDao.findAll());
	}

	@Override
	public DataResult<SystemEmployee> get(int id) {
		if (this.systemEmployeeDao.findById(id).orElse(null) != null ) {
			return new SuccessDataResult<SystemEmployee>(
					"The specified system employee was found successfully.",
					this.systemEmployeeDao.findById(id).get());
		} else {
			return new ErrorDataResult<SystemEmployee>("The specified system employee is not available.");
		}
	}

	@Override
	public Result add(SystemEmployee employeePosition) {
		this.systemEmployeeDao.save(employeePosition);
		return new SuccessResult("System employee added successfully.");
	}

	@Override
	public Result delete(int id) {
		this.systemEmployeeDao.deleteById(id);
		return new SuccessResult("System employee deleted successfully.");
	}

	@Override
	public Result update(SystemEmployee employeePosition) {
		this.systemEmployeeDao.save(employeePosition);
		return new SuccessResult("System employee updated successfully.");
	}

	@Override
	public Result existsSystemEmployeeByNationalityId(String nationalityId) {
		if (this.systemEmployeeDao.existsSystemEmployeeByNationalityId(nationalityId)) {
			return new SuccessResult("There is a system employee with this identification number: " + nationalityId);
		} else {
			return new ErrorResult("There is no system employee with this identification number."+ nationalityId);
		}
	}

	@Override
	public Result existsSystemEmployeeByEmail(String email) {
		if (this.systemEmployeeDao.existsSystemEmployeeByEmail(email)) {
			return new SuccessResult("There is system employee with this email: " + email);
		} else {
			return new ErrorResult("There is no system employee with email."+ email);
		}
	}

}
