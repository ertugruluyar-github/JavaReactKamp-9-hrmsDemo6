package com.kodlamaio.hrmsDemo6.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodlamaio.hrmsDemo6.business.abstracts.EmployeePositionService;
import com.kodlamaio.hrmsDemo6.core.utilities.result.concretes.DataResult;
import com.kodlamaio.hrmsDemo6.core.utilities.result.concretes.ErrorDataResult;
import com.kodlamaio.hrmsDemo6.core.utilities.result.concretes.ErrorResult;
import com.kodlamaio.hrmsDemo6.core.utilities.result.concretes.Result;
import com.kodlamaio.hrmsDemo6.core.utilities.result.concretes.SuccessDataResult;
import com.kodlamaio.hrmsDemo6.core.utilities.result.concretes.SuccessResult;
import com.kodlamaio.hrmsDemo6.dataAccess.abstracts.EmployeePositionDao;
import com.kodlamaio.hrmsDemo6.entities.concretes.EmployeePosition;

@Service
public class EmployeePositionManager implements EmployeePositionService {
	
	private EmployeePositionDao employeePositionDao;
	
	@Autowired
	public EmployeePositionManager(EmployeePositionDao employeePositionDao) {
		this.employeePositionDao = employeePositionDao;
	}

	@Override
	public DataResult<List<EmployeePosition>> getAll() {
		return new SuccessDataResult<List<EmployeePosition>>("Employee positions listed successfully.",
				this.employeePositionDao.findAll());
	}

	@Override
	public DataResult<EmployeePosition> get(int id) {
		if (this.employeePositionDao.findById(id).orElse(null) != null ) {
			return new SuccessDataResult<EmployeePosition>(
					"The specified employee position was found successfully.",
					this.employeePositionDao.findById(id).get());
		} else {
			return new ErrorDataResult<EmployeePosition>("The specified employee position is not available.");
		}
	}

	@Override
	public Result add(EmployeePosition employeePosition) {
		if (this.employeePositionDao.existsEmployeePositionByPositionNameIgnoreCase(employeePosition.getPositionName())) {
			return new ErrorResult("There's a employee position with that name.");
		} else {
			this.employeePositionDao.save(employeePosition);
			return new SuccessResult("Employee position added successfully.");
		}
		
	}

	@Override
	public Result delete(int id) {
		this.employeePositionDao.deleteById(id);
		return new SuccessResult("Employee position deleted successfully.");
	}

	@Override
	public Result update(EmployeePosition employeePosition) {
		this.employeePositionDao.save(employeePosition);
		return new SuccessResult("Employee position updated successfully.");
	}

	@Override
	public boolean existsEmployeePositionByPositionName(String positionName) {
		return this.employeePositionDao.existsEmployeePositionByPositionNameIgnoreCase(positionName);
	}

}
