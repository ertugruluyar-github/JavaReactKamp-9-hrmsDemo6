package com.kodlamaio.hrmsDemo6.business.abstracts;

import java.util.List;

import com.kodlamaio.hrmsDemo6.core.utilities.result.concretes.DataResult;
import com.kodlamaio.hrmsDemo6.core.utilities.result.concretes.Result;
import com.kodlamaio.hrmsDemo6.entities.concretes.WorkExperience;

public interface WorkExperienceService {
	DataResult<List<WorkExperience>> getAll();
	DataResult<WorkExperience> get(int id);
	Result add(WorkExperience workExperience);
	Result delete(int id);
	Result update(WorkExperience workExperience);
	
	DataResult<List<WorkExperience>> getAllOrderByEndDateDesc();
	DataResult<List<WorkExperience>> getByEndDateIsNull();
	DataResult<List<WorkExperience>> getByEndDateIsNotNullOrderByEndDateDesc();
}
