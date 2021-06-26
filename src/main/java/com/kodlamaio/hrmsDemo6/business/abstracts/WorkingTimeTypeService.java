package com.kodlamaio.hrmsDemo6.business.abstracts;

import java.util.List;

import com.kodlamaio.hrmsDemo6.core.utilities.result.concretes.DataResult;
import com.kodlamaio.hrmsDemo6.core.utilities.result.concretes.Result;
import com.kodlamaio.hrmsDemo6.entities.concretes.WorkingTimeType;

public interface WorkingTimeTypeService {
	DataResult<List<WorkingTimeType>> getAll();
	DataResult<WorkingTimeType> get(int id);
	
	Result add(WorkingTimeType workingTimeType);
	Result delete(int id);
	Result update(WorkingTimeType workingTimeType);
	
	DataResult<List<WorkingTimeType>> getAllByType(String type);
}
