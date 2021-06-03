package com.kodlamaio.hrmsDemo6.business.abstracts;

import java.util.List;

import com.kodlamaio.hrmsDemo6.core.utilities.result.concretes.DataResult;
import com.kodlamaio.hrmsDemo6.core.utilities.result.concretes.Result;
import com.kodlamaio.hrmsDemo6.entities.concretes.TechnologyKnowledge;

public interface TechnologyKnowledgeService {
	DataResult<List<TechnologyKnowledge>> getAll();
	DataResult<TechnologyKnowledge> get(int id);
	Result add(TechnologyKnowledge technologyKnowledge);
	Result delete(int id);
	Result update(TechnologyKnowledge technologyKnowledge);
}
