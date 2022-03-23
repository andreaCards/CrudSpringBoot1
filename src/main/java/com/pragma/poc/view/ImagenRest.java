package com.pragma.poc.view;

import java.util.List;
import com.pragma.poc.document.Image;
import com.pragma.poc.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("images")
public class ImagenRest {
	
	@Autowired
	private ImageService imgService;
	
	//Obtener todas las imagenes
	@GetMapping("/list")
	public List<Image> getAllImages() {
		return imgService.getAllImages();
	}
	@GetMapping("/{id}")
	public List<Image> findByClientId(@PathVariable("id") Integer id) {
		return imgService.findByClientId(id);
	}
}
