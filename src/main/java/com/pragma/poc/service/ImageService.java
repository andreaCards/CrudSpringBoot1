package com.pragma.poc.service;

import com.pragma.poc.document.Image;
import com.pragma.poc.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;



@Service
public class ImageService {

	@Autowired
	ImageRepository imageRepository;

	@Transactional

	public List<Image> getAllImages() {
		List<Image> imageList = new ArrayList<>();
		try{
			imageList = imageRepository.findAll();
		}catch(Exception e){
			throw e;
		}
		return imageList;
	}

	public List<Image> findByClientId(int id) {
		return imageRepository.findImageByClientId(id);
	}

	public String encodeImage(String imagePath) throws Exception{
		FileInputStream imageStream = new FileInputStream(imagePath);
		byte[] data = imageStream.readAllBytes();
		String imageString = Base64.getEncoder().encodeToString(data);
		imageStream.close();
		return imageString;
	}
	public void decodeImage(String txtPath,String savePath) throws Exception{
		byte [] data = Base64.getDecoder().decode(txtPath);
		FileOutputStream fileOutputStream = new FileOutputStream(savePath);
		fileOutputStream.write(data);
		fileOutputStream.close();
	}
}
