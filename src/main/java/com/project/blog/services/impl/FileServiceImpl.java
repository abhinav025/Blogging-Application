package com.project.blog.services.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.project.blog.services.FileService;

@Service
public class FileServiceImpl implements FileService {

	@Override
	public String uploadImage(String path, MultipartFile file) throws IOException {

		// -- File name
		String name = file.getOriginalFilename();

		
		//-- Random name generate
		String randomID = UUID.randomUUID().toString();
		String fileName1 = randomID.concat(name.substring(name.lastIndexOf(".")));
		
		
		// -- Full path
//		String filePath = path + File.pathSeparator + fileName1;
		String filePath = path + File.separator + fileName1;
		
		
		
		
		// -- Create Folder if not created
		File f = new File(path);
		if (!f.exists()) {
			f.mkdir();
		}
		
		//-- File copy
		Files.copy(file.getInputStream(), Paths.get(filePath));
		
		return fileName1;
	}

	@Override
	public InputStream getResource(String path, String fileName) throws FileNotFoundException {
		
		String fullPath = path + File.separator + fileName;
	    File file = new File(fullPath);
	    
	    if (!file.exists()) {
	        throw new FileNotFoundException("File not found at path: " + fullPath);
	    }

	 // db logic to return input stream
	    return new FileInputStream(file);
		
	}

}
