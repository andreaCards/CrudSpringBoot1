package com.pragma.poc.service.impl;

import com.pragma.poc.DTO.ImageDTO;
import com.pragma.poc.document.Image;
import com.pragma.poc.mapper.MapperService;
import com.pragma.poc.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;


import java.io.FileOutputStream;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;



@Service
public class ImageServiceImpl {

	@Autowired
	ImageRepository imageRepository;
	
	@Autowired
	MapperService mapper;

	@Transactional

	public List<Image> getAllImages() {
		List<Image> imageList = new ArrayList<>();
		imageList = imageRepository.findAll();
		return imageList;
	}

	public Image findByClientId(String id) {
		return imageRepository.findImageByClientId(id);
	}

	public String encodeImage(MultipartFile file) throws Exception{
		byte[] data = file.getBytes();
		String imageString = Base64.getEncoder().encodeToString(data);
		return imageString;
	}
	public String decodeImage(String base64,String savePath) throws Exception{
		byte [] data = Base64.getDecoder().decode(base64);
		FileOutputStream fileOutputStream = new FileOutputStream(savePath);
		fileOutputStream.write(data);
		fileOutputStream.close();
		return "The image was downloaded";
	}
	public String postImage(MultipartFile imageFile, String id) throws Exception {
		if(!existsByclientId(id)) {
			Image image = new Image();
			image.setClientId(id);
			String imageEncoding = encodeImage(imageFile);
			image.setImage(imageEncoding);
			image.setName(imageFile.getOriginalFilename());
			imageRepository.save(image);
			return "Image created succesfully!";}
		else {
			return "Image exist!";
		}

	}
	public void deleteImage(String id) {
		imageRepository.deleteByClientId(id);
	}
	
	public Boolean existsByclientId(String id) {
		return imageRepository.existsByClientId(id);
	}
	public ImageDTO getImage(String id) throws Exception {
		ImageDTO imageDTO = new ImageDTO();
		if(existsByclientId(id)) {
			Image image = new Image();
			image = findByClientId(id);
			String savePath = "C:\\Users\\andrea.cardenas\\Downloads\\"+image.getName();
			decodeImage(image.getImage(), savePath);
			imageDTO= mapper.mapperImageToImageDTO(image);}
		return imageDTO;
		}
}
