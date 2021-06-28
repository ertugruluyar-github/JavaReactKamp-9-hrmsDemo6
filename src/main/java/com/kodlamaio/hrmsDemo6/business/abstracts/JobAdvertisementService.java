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
	
	DataResult<List<JobAdvertisement>> getAll();
	DataResult<JobAdvertisement> get(int id);
	DataResult<List<JobAdvertisement>> getAllByActivated();
	DataResult<List<JobAdvertisement>> getAllByActivatedAndEmployerId(int id);
	
	DataResult<List<JobAdvertisement>> getAllByActivatedOrderByReleaseDateAsc();
	DataResult<List<JobAdvertisement>> getAllByActivatedOrderByApplicationDeadlineAsc();

	DataResult<List<JobAdvertisement>> getAllByActivatedAndWorkingTimeType(String type);
	DataResult<List<JobAdvertisement>> getAllByActivatedAndWorkingPlaceType(String type);
	DataResult<List<JobAdvertisement>> getAllByActivatedAndWorkingPlaceTypeOrWorkingTimeType(String workingPlaceType, String workingTimeType);
	
	DataResult<List<JobAdvertisement>> getAllByActivatedWithPageable(Pageable pageable);
	DataResult<List<JobAdvertisement>> getAllByActivatedAndWorkingPlaceTypeOrWorkingTimeTypeWithPageable(String workingPlaceType, String workingTimeType, Pageable pageable);
	
	Result activateJobAdvertisement(int id);
	Result deactivateJobAdvertisement(int id);
}
