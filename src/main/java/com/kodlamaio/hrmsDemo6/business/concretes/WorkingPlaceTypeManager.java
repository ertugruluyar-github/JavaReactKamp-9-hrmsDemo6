package com.kodlamaio.hrmsDemo6.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodlamaio.hrmsDemo6.business.abstracts.WorkingPlaceTypeService;
import com.kodlamaio.hrmsDemo6.core.utilities.result.concretes.DataResult;
import com.kodlamaio.hrmsDemo6.core.utilities.result.concretes.ErrorDataResult;
import com.kodlamaio.hrmsDemo6.core.utilities.result.concretes.Result;
import com.kodlamaio.hrmsDemo6.core.utilities.result.concretes.SuccessDataResult;
import com.kodlamaio.hrmsDemo6.core.utilities.result.concretes.SuccessResult;
import com.kodlamaio.hrmsDemo6.dataAccess.abstracts.WorkingPlaceTypeDao;
import com.kodlamaio.hrmsDemo6.entities.concretes.WorkingPlaceType;

@Service
public class WorkingPlaceTypeManager implements WorkingPlaceTypeService {
	
	private WorkingPlaceTypeDao workingPlaceTypeDao;
	
	@Autowired
	public WorkingPlaceTypeManager(WorkingPlaceTypeDao workingPlaceTypeDao) {
		this.workingPlaceTypeDao = workingPlaceTypeDao;
	}
	
	@Override
	public DataResult<List<WorkingPlaceType>> getAll() {
		return new SuccessDataResult<List<WorkingPlaceType>>("WorkingPlaceTypes listed successfully.", this.workingPlaceTypeDao.findAll());
	}

	@Override
	public DataResult<WorkingPlaceType> get(int id) {
		if (this.workingPlaceTypeDao.findById(id).orElse(null) != null) {
			return new SuccessDataResult<WorkingPlaceType>("The specified WorkingPlaceType was found successfully.",
					this.workingPlaceTypeDao.findById(id).get());
		} else {
			return new ErrorDataResult<WorkingPlaceType>("The specified WorkingPlaceType is not available.");
		}
	}

	@Override
	public Result add(WorkingPlaceType workingPlaceType) {
		this.workingPlaceTypeDao.save(workingPlaceType);
		return new SuccessResult("WorkingPlaceType added successfully.");
	}

	@Override
	public Result delete(int id) {
		this.workingPlaceTypeDao.deleteById(id);
		return new SuccessResult("WorkingPlaceType deleted successfully.");
	}

	@Override
	public Result update(WorkingPlaceType workingPlaceType) {
		this.workingPlaceTypeDao.save(workingPlaceType);
		return new SuccessResult("WorkingPlaceType updated successfully.");
	}

	@Override
	public DataResult<List<WorkingPlaceType>> getAllByType(String type) {
		return new SuccessDataResult<List<WorkingPlaceType>>("WorkingPlaceTypes that type" + type + " listed successfully.", this.workingPlaceTypeDao.findByType(type));
	}
}
