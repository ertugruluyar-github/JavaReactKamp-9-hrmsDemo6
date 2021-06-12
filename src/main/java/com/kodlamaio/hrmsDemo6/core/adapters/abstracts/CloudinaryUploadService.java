package com.kodlamaio.hrmsDemo6.core.adapters.abstracts;

import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

public interface CloudinaryUploadService {
	Map<String, Object> upload(MultipartFile file);
}
