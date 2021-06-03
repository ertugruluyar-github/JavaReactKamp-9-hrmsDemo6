package com.kodlamaio.hrmsDemo6.core.adapters.abstracts;

import java.io.File;
import java.util.Map;

public interface CloudinaryUploadService {
	Map<String, Object> upload(File file);
}
