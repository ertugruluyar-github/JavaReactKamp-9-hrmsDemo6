package com.kodlamaio.hrmsDemo6.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodlamaio.hrmsDemo6.business.abstracts.TechnologyKnowledgeService;
import com.kodlamaio.hrmsDemo6.core.utilities.result.concretes.DataResult;
import com.kodlamaio.hrmsDemo6.core.utilities.result.concretes.ErrorDataResult;
import com.kodlamaio.hrmsDemo6.core.utilities.result.concretes.Result;
import com.kodlamaio.hrmsDemo6.core.utilities.result.concretes.SuccessDataResult;
import com.kodlamaio.hrmsDemo6.core.utilities.result.concretes.SuccessResult;
import com.kodlamaio.hrmsDemo6.dataAccess.abstracts.TechnologyKnowledgeDao;
import com.kodlamaio.hrmsDemo6.entities.concretes.TechnologyKnowledge;

@Service
public class TechnologyKnowledgeManager implements TechnologyKnowledgeService {
	
	private TechnologyKnowledgeDao technologyKnowledgeDao;
	
	@Autowired
	public TechnologyKnowledgeManager(TechnologyKnowledgeDao technologyKnowledgeDao) {
		this.technologyKnowledgeDao = technologyKnowledgeDao;
	}

	@Override
	public DataResult<List<TechnologyKnowledge>> getAll() {
		return new SuccessDataResult<List<TechnologyKnowledge>>("Technology Knowledges listed succesfully.", this.technologyKnowledgeDao.findAll());
	}

	@Override
	public DataResult<TechnologyKnowledge> get(int id) {
		if (this.technologyKnowledgeDao.findById(id).orElse(null) != null) {
			return new SuccessDataResult<TechnologyKnowledge>("The specified Technology Knowledge was found successfully.",
					this.technologyKnowledgeDao.findById(id).get());
		} else {
			return new ErrorDataResult<TechnologyKnowledge>("The specified Technology Knowledge is not available.");
		}
	}

	@Override
	public Result add(TechnologyKnowledge technologyKnowledge) {
		this.technologyKnowledgeDao.save(technologyKnowledge);
		return new SuccessResult("Technology Knowledge added succesfully.");
	}

	@Override
	public Result delete(int id) {
		this.technologyKnowledgeDao.deleteById(id);
		return new SuccessResult("Technology Knowledge deleted succesfully.");
	}

	@Override
	public Result update(TechnologyKnowledge technologyKnowledge) {
		this.technologyKnowledgeDao.save(technologyKnowledge);
		return new SuccessResult("Technology Knowledge updated succesfully.");
	}

	
}
