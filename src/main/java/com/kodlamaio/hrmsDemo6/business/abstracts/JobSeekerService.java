package com.kodlamaio.hrmsDemo6.business.abstracts;

import java.util.List;

import com.kodlamaio.hrmsDemo6.core.utilities.result.concretes.DataResult;
import com.kodlamaio.hrmsDemo6.core.utilities.result.concretes.Result;
import com.kodlamaio.hrmsDemo6.entities.concretes.JobSeeker;

public interface JobSeekerService {
	DataResult<List<JobSeeker>> getAll();
	DataResult<JobSeeker> get(int id);
	
	Result add(JobSeeker jobSeeker);
	Result delete(int id);
	Result update(JobSeeker jobSeeker);
	
	boolean existsJobSeekerByNationalityId(String nationalityId);
	boolean existsJobSeekerByEmail(String email);	
	
	Result likeJobAdvertisement(int jobSeekerId, int jobAdvertisementId);
	Result dislikeJobAdvertisement(int jobSeekerId, int jobAdvertisementId);
	DataResult<Boolean> existsByFavouriteJobAdvertisementsId(int id);
	
	DataResult<List<JobSeeker>> getAllByFavouriteJobAdvertisementsId(int id);
}
