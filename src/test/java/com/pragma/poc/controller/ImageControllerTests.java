package com.pragma.poc.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;

import com.pragma.poc.DTO.ClientDTO;
import com.pragma.poc.document.Image;
import com.pragma.poc.entity.Client;
import com.pragma.poc.repository.ClientRepository;
import com.pragma.poc.service.impl.ImageServiceImpl;

@SpringBootTest
class ImageControllerTests {
	@InjectMocks
	ImageController controller;
	@Mock
	ImageServiceImpl service;
	
	MockMultipartFile file = (new MockMultipartFile("foo", "foo.txt", MediaType.TEXT_PLAIN_VALUE,
		      "Hello World".getBytes()));
	@Mock
	Image image;
	
	List<Image> listImage= new ArrayList<>();
	private static final String ID="1";
	
	@BeforeEach
	void init() {
		MockitoAnnotations.openMocks(this);
	}

	
	@Test
	public void getAllImagesTest() {
		listImage.add(image);
		when(service.getAllImages()).thenReturn(listImage);
		assertEquals(controller.getAllImages().getBody().size(),listImage.size());
	}
	
	@Test
	public void findByClientIdTest() {
		when(service.findByClientId(ID)).thenReturn(image);
		assertEquals(controller.findByClientId(ID).getBody(),image);
	}
	
}
