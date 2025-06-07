package com.project.blog.services.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

import com.project.blog.services.FileService;

public class FileServiceImpl implements FileService {

	@Override
	public String uploadImage(String path, MultipartFile file) throws IOException {

		// -- File name
		String name = file.getOriginalFilename();

		// -- Full path
		String filePath = path + File.pathSeparator + name;
		
		String randomID = UUID.randomUUID().toString();
		randomID
		
		// -- Create Folder if not created
		File f = new File(path);
		if (!f.exists()) {
			f.mkdir();
		}
		
		//-- File copy
		Files.copy(file.getInputStream(), Paths.get(filePath));
		
		return null;
	}

	@Override
	public InputStream getResource(String path, String fileName) throws FileNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

}
