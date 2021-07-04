package com.kodlamaio.hrmsDemo6.business.abstracts;

import java.util.List;

import com.kodlamaio.hrmsDemo6.core.utilities.result.concretes.DataResult;
import com.kodlamaio.hrmsDemo6.core.utilities.result.concretes.Result;
import com.kodlamaio.hrmsDemo6.entities.concretes.SystemEmployeeConfirmToJobAdvertisement;

public interface SystemEmployeeConfirmToJobAdvertisementService {
	DataResult<List<SystemEmployeeConfirmToJobAdvertisement>> getAll();
	DataResult<SystemEmployeeConfirmToJobAdvertisement> get(int id);
	DataResult<List<SystemEmployeeConfirmToJobAdvertisement>> getAllByJobAdvertisementId(int id);
	DataResult<SystemEmployeeConfirmToJobAdvertisement> getFirstByJobAdvertisementIdOrderByDateOfConfirmDesc(int id);
	
	Result add(SystemEmployeeConfirmToJobAdvertisement systemEmployeeConfirmToJobAdvertisement);
	Result delete(int id);
	Result deleteByJobAdvertisementId(int id);
	Result update(SystemEmployeeConfirmToJobAdvertisement systemEmployeeConfirmToJobAdvertisement);
	
	Result confirmJobAdvertisement(int jobAdvertisementId);
	
}
