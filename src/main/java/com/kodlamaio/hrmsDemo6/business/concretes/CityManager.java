package com.kodlamaio.hrmsDemo6.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodlamaio.hrmsDemo6.business.abstracts.CityService;
import com.kodlamaio.hrmsDemo6.core.utilities.result.concretes.DataResult;
import com.kodlamaio.hrmsDemo6.core.utilities.result.concretes.ErrorDataResult;
import com.kodlamaio.hrmsDemo6.core.utilities.result.concretes.Result;
import com.kodlamaio.hrmsDemo6.core.utilities.result.concretes.SuccessDataResult;
import com.kodlamaio.hrmsDemo6.core.utilities.result.concretes.SuccessResult;
import com.kodlamaio.hrmsDemo6.dataAccess.abstracts.CityDao;
import com.kodlamaio.hrmsDemo6.entities.concretes.City;

@Service
public class CityManager implements CityService {
	
	private CityDao cityDao;
	
	@Autowired
	public CityManager(CityDao cityDao) {
		this.cityDao = cityDao;
	}

	@Override
	public DataResult<List<City>> getAll() {
		return new SuccessDataResult<List<City>>("Cities listed succesfully.", this.cityDao.findAll());
	}
	
	@Override
	public DataResult<City> get(int id) {
		if (this.cityDao.findById(id).orElse(null) != null) {
			return new SuccessDataResult<City>("The specified city was found successfully.",
					this.cityDao.findById(id).get());
		} else {
			return new ErrorDataResult<City>("The specified city is not available.");
		}
	}
	
	@Override
	public Result add(City city) {
		this.cityDao.save(city);
		return new SuccessResult("City added succesfully.");
	}

	@Override
	public Result delete(int id) {
		this.cityDao.deleteById(id);
		return new SuccessResult("City deleted succesfully.");
	}

	@Override
	public Result update(City city) {
		this.cityDao.save(city);
		return new SuccessResult("City update successfully.");
	}

}
