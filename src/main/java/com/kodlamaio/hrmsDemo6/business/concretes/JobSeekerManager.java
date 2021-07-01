package com.kodlamaio.hrmsDemo6.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodlamaio.hrmsDemo6.business.abstracts.EmailConfirmToJobSeekerService;
import com.kodlamaio.hrmsDemo6.business.abstracts.JobAdvertisementService;
import com.kodlamaio.hrmsDemo6.business.abstracts.JobSeekerService;
import com.kodlamaio.hrmsDemo6.core.adapters.abstracts.JobSeekerValidationService;
import com.kodlamaio.hrmsDemo6.core.utilities.result.concretes.DataResult;
import com.kodlamaio.hrmsDemo6.core.utilities.result.concretes.ErrorDataResult;
import com.kodlamaio.hrmsDemo6.core.utilities.result.concretes.ErrorResult;
import com.kodlamaio.hrmsDemo6.core.utilities.result.concretes.Result;
import com.kodlamaio.hrmsDemo6.core.utilities.result.concretes.SuccessDataResult;
import com.kodlamaio.hrmsDemo6.core.utilities.result.concretes.SuccessResult;
import com.kodlamaio.hrmsDemo6.core.validators.emailRegex.abstracts.JobSeekerEmailRegexValidatorService;
import com.kodlamaio.hrmsDemo6.dataAccess.abstracts.JobSeekerDao;
import com.kodlamaio.hrmsDemo6.entities.concretes.EmailConfirmToJobSeeker;
import com.kodlamaio.hrmsDemo6.entities.concretes.JobAdvertisement;
import com.kodlamaio.hrmsDemo6.entities.concretes.JobSeeker;

@Service
public class JobSeekerManager implements JobSeekerService {

	private JobSeekerDao jobSeekerDao;
	private JobSeekerEmailRegexValidatorService jobSeekerEmailRegexValidatorService;
	private JobSeekerValidationService jobSeekerValidationService;
	private JobAdvertisementService jobAdvertisementService;
	private EmailConfirmToJobSeekerService emailConfirmToJobSeekerService;

	@Autowired
	public JobSeekerManager(JobSeekerDao jobSeekerDao,
			JobSeekerEmailRegexValidatorService jobSeekerEmailRegexValidatorService,
			JobSeekerValidationService jobSeekerValidationService, JobAdvertisementService jobAdvertisementService,
			EmailConfirmToJobSeekerService emailConfirmToJobSeekerService) {
		this.jobSeekerDao = jobSeekerDao;
		this.jobSeekerEmailRegexValidatorService = jobSeekerEmailRegexValidatorService;
		this.jobSeekerValidationService = jobSeekerValidationService;
		this.jobAdvertisementService = jobAdvertisementService;
		this.emailConfirmToJobSeekerService = emailConfirmToJobSeekerService;
	}

	@Override
	public DataResult<List<JobSeeker>> getAll() {
		return new SuccessDataResult<List<JobSeeker>>("Jobseekers listed successfully", this.jobSeekerDao.findAll());
	}

	@Override
	public DataResult<JobSeeker> get(int id) {
		if (this.jobSeekerDao.findById(id).orElse(null) != null) {
			return new SuccessDataResult<JobSeeker>("The specified jobseeker was found successfully.",
					this.jobSeekerDao.findById(id).get());
		} else {
			return new ErrorDataResult<JobSeeker>("The specified jobseeker is not available.");
		}
	}

	@Override
	public Result add(JobSeeker jobSeeker) {
		if (!this.jobSeekerValidationService.isRealPerson(jobSeeker)) {
			return new ErrorResult("Invalid job seeker!");
		} else if (this.jobSeekerDao.existsJobSeekerByNationalityId(jobSeeker.getNationalityId())) {
			return new ErrorResult("There is a jobseeker record with this identification number.");
		} else if (!this.jobSeekerEmailRegexValidatorService.isValidEmail(jobSeeker.getEmail())) {
			return new ErrorResult("Invalid email!");
		} else if (this.jobSeekerDao.existsJobSeekerByEmail(jobSeeker.getEmail())) {
			return new ErrorResult("There is a jobseeker record with this email.");
		} else {
			this.jobSeekerDao.save(jobSeeker);
			this.emailConfirmToJobSeekerService.add(new EmailConfirmToJobSeeker(jobSeeker));
			return new SuccessResult("Jobseeker added successfully.");
		}
	}

	@Override
	public Result delete(int id) {
		this.emailConfirmToJobSeekerService.deleteByJobSeekerId(id);
		this.jobSeekerDao.deleteById(id);
		return new SuccessResult("Jobseeker deleted successfully.");
	}

	@Override
	public Result update(JobSeeker jobSeeker) {
		this.jobSeekerDao.save(jobSeeker);
		this.emailConfirmToJobSeekerService.add(new EmailConfirmToJobSeeker(jobSeeker));
		return new SuccessResult("Jobseeker updated successfully.");
	}

	@Override
	public DataResult<Boolean> existsJobSeekerByNationalityId(String nationalityId) {
		if (this.jobSeekerDao.existsJobSeekerByNationalityId(nationalityId)) {
			return new SuccessDataResult<Boolean>(
					"There is a jobseeker with this identification number: " + nationalityId, true);
		} else {
			return new ErrorDataResult<Boolean>(
					"There is no jobseeker with this identification number." + nationalityId, false);
		}
	}

	@Override
	public DataResult<Boolean> existsJobSeekerByEmail(String email) {
		if (this.jobSeekerDao.existsJobSeekerByEmail(email)) {
			return new SuccessDataResult<Boolean>(
					"There is a jobseeker with this email: " + email, true);
		} else {
			return new ErrorDataResult<Boolean>(
					"There is no jobseeker with this email." + email, false);
		}
	}

	@Override
	public Result likeJobAdvertisement(int jobSeekerId, int jobAdvertisementId) {
		JobSeeker currentJobSeeker = this.jobSeekerDao.findById(jobSeekerId).get();
		List<JobAdvertisement> currentFavouriteJobAdvertisements = currentJobSeeker.getFavouriteJobAdvertisements();
		JobAdvertisement newFavouriteJobAdvertisement = this.jobAdvertisementService.get(jobAdvertisementId).getData();
		if (!currentFavouriteJobAdvertisements.contains(newFavouriteJobAdvertisement)) {
			currentFavouriteJobAdvertisements.add(newFavouriteJobAdvertisement);
			currentJobSeeker.setFavouriteJobAdvertisements(currentFavouriteJobAdvertisements);
			this.jobSeekerDao.save(currentJobSeeker);// updated jobSeeker
			return new SuccessResult("Jobseeker liked job advertisement successfully.");
		} else {
			return new ErrorResult("The job advertisement is already liked!");
		}

	}

	@Override
	public Result dislikeJobAdvertisement(int jobSeekerId, int jobAdvertisementId) {
		JobSeeker currentJobSeeker = this.jobSeekerDao.findById(jobSeekerId).get();
		List<JobAdvertisement> currentFavouriteJobAdvertisements = currentJobSeeker.getFavouriteJobAdvertisements();
		JobAdvertisement jobAdvertisementDislike = this.jobAdvertisementService.get(jobAdvertisementId).getData();
		if (currentFavouriteJobAdvertisements.remove(jobAdvertisementDislike)) {
			currentJobSeeker.setFavouriteJobAdvertisements(currentFavouriteJobAdvertisements);
			this.jobSeekerDao.save(currentJobSeeker);// updated jobSeeker
			return new SuccessResult("Jobseeker disliked job advertisement successfully.");
		} else {
			return new ErrorResult("The job advertisement is not exist in the liked job advertisements!");
		}

	}

	@Override
	public DataResult<List<JobSeeker>> getAllByFavouriteJobAdvertisementsId(int id) {
		return new SuccessDataResult<List<JobSeeker>>(
				"Job seekers that liked the job advertisement listed successfully.",
				this.jobSeekerDao.findByFavouriteJobAdvertisements_Id(id));
	}

//	@Override
//	public boolean hasEmptyField(JobSeeker jobSeeker) {
//		if (jobSeeker.getFirstName().isEmpty() || jobSeeker.getLastName().isEmpty() 
//				|| jobSeeker.getDateOfBirth().toString().isEmpty() || jobSeeker.getEmail().isEmpty() 
//				|| jobSeeker.getNationalityId().isEmpty() || jobSeeker.getPassword().isEmpty() 
//				|| jobSeeker.getGender().isEmpty()) {
//			return false;
//		} else {
//			return true;
//		}
//	}

}
