package com.kodlamaio.hrmsDemo6.core.adapters.concretes;


import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.kodlamaio.hrmsDemo6.core.adapters.abstracts.CloudinaryUploadService;
import com.kodlamaio.hrmsDemo6.externalServices.cloudinary.CloudinaryUpload;

@Service
public class CloudinaryUploadAdapter implements CloudinaryUploadService {

	@Override
	public Map<String, Object> upload(MultipartFile file) {
		CloudinaryUpload cloudinaryUpload = new CloudinaryUpload();
		Map<String, Object> uploadResult = cloudinaryUpload.upload(file);
		return uploadResult;
	}

}
