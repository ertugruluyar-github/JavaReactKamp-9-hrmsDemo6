package com.kodlamaio.hrmsDemo6.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodlamaio.hrmsDemo6.business.abstracts.WorkingTimeTypeService;
import com.kodlamaio.hrmsDemo6.core.utilities.result.concretes.DataResult;
import com.kodlamaio.hrmsDemo6.core.utilities.result.concretes.ErrorDataResult;
import com.kodlamaio.hrmsDemo6.core.utilities.result.concretes.Result;
import com.kodlamaio.hrmsDemo6.core.utilities.result.concretes.SuccessDataResult;
import com.kodlamaio.hrmsDemo6.core.utilities.result.concretes.SuccessResult;
import com.kodlamaio.hrmsDemo6.dataAccess.abstracts.WorkingTimeTypeDao;
import com.kodlamaio.hrmsDemo6.entities.concretes.WorkingTimeType;

@Service
public class WorkingTimeTypeManager implements WorkingTimeTypeService {
	
	private WorkingTimeTypeDao workingTimeTypeDao;
	
	@Autowired
	public WorkingTimeTypeManager(WorkingTimeTypeDao workingTimeTypeDao) {
		this.workingTimeTypeDao = workingTimeTypeDao;
	}
	
	@Override
	public DataResult<List<WorkingTimeType>> getAll() {
		return new SuccessDataResult<List<WorkingTimeType>>("WorkingTimeTypes listed successfully.", this.workingTimeTypeDao.findAll());
	}

	@Override
	public DataResult<WorkingTimeType> get(int id) {
		if (this.workingTimeTypeDao.findById(id).orElse(null) != null) {
			return new SuccessDataResult<WorkingTimeType>("The specified WorkingTimeType was found successfully.",
					this.workingTimeTypeDao.findById(id).get());
		} else {
			return new ErrorDataResult<WorkingTimeType>("The specified WorkingTimeType is not available.");
		}
	}

	@Override
	public Result add(WorkingTimeType workingTimeType) {
		this.workingTimeTypeDao.save(workingTimeType);
		return new SuccessResult("WorkingTimeType added successfully.");
	}

	@Override
	public Result delete(int id) {
		this.workingTimeTypeDao.deleteById(id);
		return new SuccessResult("WorkingTimeType deleted successfully.");
	}

	@Override
	public Result update(WorkingTimeType workingTimeType) {
		this.workingTimeTypeDao.save(workingTimeType);
		return new SuccessResult("WorkingTimeType updated successfully.");
	}

	@Override
	public DataResult<List<WorkingTimeType>> getAllByType(String type) {
		return new SuccessDataResult<List<WorkingTimeType>>("WorkingTimeTypes that type" + type + " listed successfully.", this.workingTimeTypeDao.findByType(type));
	}

}
