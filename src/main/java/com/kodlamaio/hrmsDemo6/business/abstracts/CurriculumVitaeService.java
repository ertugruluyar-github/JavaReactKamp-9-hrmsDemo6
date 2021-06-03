package com.kodlamaio.hrmsDemo6.business.abstracts;

import java.util.List;

import com.kodlamaio.hrmsDemo6.core.utilities.result.concretes.DataResult;
import com.kodlamaio.hrmsDemo6.core.utilities.result.concretes.Result;
import com.kodlamaio.hrmsDemo6.entities.concretes.CurriculumVitae;

public interface CurriculumVitaeService {
	DataResult<List<CurriculumVitae>> getAll();
	DataResult<CurriculumVitae> get(int id);
	Result add(CurriculumVitae curriculumVitae);
	Result delete(int id);
	Result update(CurriculumVitae curriculumVitae);
	DataResult<List<CurriculumVitae>> getAllByJobSeekerId(int id);
	DataResult<String> uploadPhoto(Integer id, String filePath);
}
