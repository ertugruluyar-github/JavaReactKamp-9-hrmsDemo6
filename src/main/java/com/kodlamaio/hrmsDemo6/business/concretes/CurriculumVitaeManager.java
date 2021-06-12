package com.kodlamaio.hrmsDemo6.business.concretes;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	public Result add(CurriculumVitae language) {
		this.curriculumVitaeDao.save(language);
		return new SuccessResult("Curriculum Vitae added succesfully.");
	}

	@Override
	public Result delete(int id) {
		this.curriculumVitaeDao.deleteById(id);
		return new SuccessResult("Curriculum Vitae deleted succesfully.");
	}

	@Override
	public Result update(CurriculumVitae language) {
		this.curriculumVitaeDao.save(language);
		return new SuccessResult("Curriculum Vitae updated succesfully.");
	}

	@Override
	public DataResult<List<CurriculumVitae>> getAllByJobSeekerId(int id) {
		return new SuccessDataResult<List<CurriculumVitae>>("The jobseeker's curriculum vitaes listed succesfully.",
				this.curriculumVitaeDao.findByJobSeeker_id(id));
	}

	@Override
	public DataResult<String> uploadPhoto(int id, String filePath) {
		File file = new File(filePath);
		if (!file.exists()) {
			file = new File("", filePath.substring(1));
		}
		Object object = this.cloudinaryUploadService.upload(file).get("secure_url");
		if ((object == null)) {
			return new ErrorDataResult<String>("Failed to load photo! Not found image.",
					String.valueOf(file.getPath() + "    " + file.getAbsolutePath()));
		} else if (!this.curriculumVitaeDao.existsById(id)) {
			return new ErrorDataResult<String>("Failed to load photo! Not found curriculum vitae.", null);
		} else {
			String secure_url = object.toString();
			CurriculumVitae c = this.curriculumVitaeDao.findById(id).get();
			c.setPhotoLink(secure_url);
			this.update(c);
			return new SuccessDataResult<String>("Photo upload successfully.", secure_url);
		}
	}

}
