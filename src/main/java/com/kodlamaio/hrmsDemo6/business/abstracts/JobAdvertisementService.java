package com.kodlamaio.hrmsDemo6.business.abstracts;

import java.util.List;

import org.springframework.data.domain.Pageable;

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
	DataResult<JobAdvertisement> get(int id);
	DataResult<List<JobAdvertisement>> getByActivatedAndWorkingTimeType(String type);
	DataResult<List<JobAdvertisement>> getByActivatedAndWorkingPlaceType(String type);
	DataResult<List<JobAdvertisement>> getByActivatedWithPageable(Pageable pageable);
	
	Result activateJobAdvertisement(int id);
	Result deactivateJobAdvertisement(int id);
}
