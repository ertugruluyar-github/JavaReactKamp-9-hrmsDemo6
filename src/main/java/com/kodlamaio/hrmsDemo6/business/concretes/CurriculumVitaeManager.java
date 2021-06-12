package com.kodlamaio.hrmsDemo6.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.kodlamaio.hrmsDemo6.business.abstracts.CurriculumVitaeService;
import com.kodlamaio.hrmsDemo6.core.adapters.abstracts.CloudinaryUploadService;
import com.kodlamaio.hrmsDemo6.core.utilities.result.concretes.DataResult;
import com.kodlamaio.hrmsDemo6.core.utilities.result.concretes.ErrorDataResult;
import com.kodlamaio.hrmsDemo6.core.utilities.result.concretes.Result;
import com.kodlamaio.hrmsDemo6.core.utilities.result.concretes.SuccessDataResult;
import com.kodlamaio.hrmsDemo6.core.utilities.result.concretes.SuccessResult;
import com.kodlamaio.hrmsDemo6.dataAccess.abstracts.CurriculumVitaeDao;
import com.kodlamaio.hrmsDemo6.entities.concretes.CurriculumVitae;

@Service
public class CurriculumVitaeManager implements CurriculumVitaeService {

	private CurriculumVitaeDao curriculumVitaeDao;
	private CloudinaryUploadService cloudinaryUploadService;

	@Autowired
	public CurriculumVitaeManager(CurriculumVitaeDao curriculumVitaeDao,
			CloudinaryUploadService cloudinaryUploadService) {
		this.curriculumVitaeDao = curriculumVitaeDao;
		this.cloudinaryUploadService = cloudinaryUploadService;
	}

	@Override
	public DataResult<List<CurriculumVitae>> getAll() {
		return new SuccessDataResult<List<CurriculumVitae>>("Curriculum Vitaes listed succesfully.",
				this.curriculumVitaeDao.findAll());
	}

	@Override
	public DataResult<CurriculumVitae> get(int id) {
		return new SuccessDataResult<CurriculumVitae>("Curriculum Vitae got succesfully.",
				this.curriculumVitaeDao.findById(id).get());
	}

	@Override
	public Result add(CurriculumVitae curriculumVitae) {
		this.curriculumVitaeDao.save(curriculumVitae);
		return new SuccessResult("Curriculum Vitae added succesfully.");
	}

	@Override
	public Result delete(int id) {
		this.curriculumVitaeDao.deleteById(id);
		return new SuccessResult("Curriculum Vitae deleted succesfully.");
	}

	@Override
	public Result update(CurriculumVitae curriculumVitae) {
		this.curriculumVitaeDao.save(curriculumVitae);
		return new SuccessResult("Curriculum Vitae updated succesfully.");
	}

	@Override
	public DataResult<List<CurriculumVitae>> getAllByJobSeekerId(int id) {
		return new SuccessDataResult<List<CurriculumVitae>>("The jobseeker's curriculum vitaes listed succesfully.",
				this.curriculumVitaeDao.findByJobSeeker_id(id));
	}

	@Override
	public DataResult<String> uploadPhoto(int id, MultipartFile file) {
	    if (!file.getResource().toString().startsWith("image")) {
	    	return new ErrorDataResult<String>("Failed to load photo! The file is not an image.", file.getContentType());
	    } else if (!this.curriculumVitaeDao.existsById(id)) {
			return new ErrorDataResult<String>("Failed to load photo! Not found curriculum vitae.", file.getContentType());
		} else {
			String secure_url = this.cloudinaryUploadService.upload(file).get("secure_url").toString();
			CurriculumVitae curriculumVitae = this.curriculumVitaeDao.findById(id).get();
			curriculumVitae.setPhotoLink(secure_url);
			this.curriculumVitaeDao.save(curriculumVitae);
			return new SuccessDataResult<String>("Photo upload successfully.", secure_url);
		}
	}

}
