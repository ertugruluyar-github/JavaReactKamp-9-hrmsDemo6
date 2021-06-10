package com.kodlamaio.hrmsDemo6.business.abstracts;

import java.util.List;

import com.kodlamaio.hrmsDemo6.core.utilities.result.concretes.DataResult;
import com.kodlamaio.hrmsDemo6.core.utilities.result.concretes.Result;
import com.kodlamaio.hrmsDemo6.entities.concretes.JobAdvertisement;

public interface JobAdvertisementService {
	Result add(JobAdvertisement jobAdvertisement);
	Result delete(int id);
	Result update(JobAdvertisement jobAdvertisement);
	
	DataResult<List<JobAdvertisement>> getByActivated();
	DataResult<List<JobAdvertisement>> getByActivatedOrderByReleaseDateAsc();
	DataResult<List<JobAdvertisement>> getByActivatedOrderByApplicationDeadlineAsc();
	DataResult<List<JobAdvertisement>> getByActivatedAndEmployerId(int id);
	
	Result activateJobAdvertisement(int id);
	Result deactivateJobAdvertisement(int id);
}
