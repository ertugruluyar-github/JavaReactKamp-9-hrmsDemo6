package com.kodlamaio.hrmsDemo6.business.abstracts;

import java.util.List;

import com.kodlamaio.hrmsDemo6.core.utilities.result.concretes.DataResult;
import com.kodlamaio.hrmsDemo6.core.utilities.result.concretes.Result;
import com.kodlamaio.hrmsDemo6.entities.concretes.WorkingPlaceType;

public interface WorkingPlaceTypeService {
	DataResult<List<WorkingPlaceType>> getAll();
	DataResult<WorkingPlaceType> get(int id);
	Result add(WorkingPlaceType workingPlaceType);
	Result delete(int id);
	Result update(WorkingPlaceType workingPlaceType);
	DataResult<List<WorkingPlaceType>> getAllByType(String type);
}
