package com.pragma.poc.controller;

import java.util.List;

import com.pragma.poc.document.Image;
import com.pragma.poc.service.impl.ImageServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("images")
public class ImageController {
	
	@Autowired
	private ImageServiceImpl imgService;
	
	//Obtener todas las imagenes
	@GetMapping("/list")
	public ResponseEntity<List<Image>> getAllImages() {
		return new ResponseEntity<List<Image>>(imgService.getAllImages(), HttpStatus.OK);
	}
	@GetMapping("/{id}")
	public ResponseEntity<Image> findByClientId(@PathVariable("id") String id) {
		return new ResponseEntity<Image>(imgService.findByClientId(id), HttpStatus.OK);
	}
}
