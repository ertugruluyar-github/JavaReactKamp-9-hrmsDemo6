package com.kodlamaio.hrmsDemo6.externalServices.cloudinary;

import java.io.IOException;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

public class CloudinaryUpload {

	@SuppressWarnings("unchecked")
	public Map<String, Object> upload(MultipartFile file) {
		Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
				"cloud_name", "hrmsdemo",//use yourself
				"api_key", "635127775344211",//use yourself
				"api_secret", "VT6SQEdS-PrVw5nXBFHoSwxyjgs"));//use yourself
		try {
			@SuppressWarnings("rawtypes")
			Map params = ObjectUtils.asMap(
					"folder", "cv/photos/",
				    "public_id", null,
				    "overwrite", true,
				    "notification_url", null,
				    "resource_type", "image"         
				);
			Map<String, Object> uploadResult = cloudinary.uploader().upload(file, params);
			return uploadResult;
			//System.out.println(uploadResult.get("secure_url"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return ObjectUtils.emptyMap();
	}

}
