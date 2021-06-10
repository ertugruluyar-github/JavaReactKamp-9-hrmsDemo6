package com.kodlamaio.hrmsDemo6.externalServices.cloudinary;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

public class CloudinaryUpload {

	@SuppressWarnings("unchecked")
	public Map<String, Object> upload(File file) {
		Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
				"cloud_name", "Your cloud_name",
				"api_key", "Your api_key code",
				"api_secret", "Your api_secret code"));
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
