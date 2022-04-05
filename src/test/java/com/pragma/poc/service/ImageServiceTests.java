package com.pragma.poc.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;

import java.util.Base64;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;

import com.pragma.poc.DTO.ClientDTO;
import com.pragma.poc.document.Image;
import com.pragma.poc.entity.Client;
import com.pragma.poc.repository.ClientRepository;
import com.pragma.poc.repository.ImageRepository;
import com.pragma.poc.service.impl.ClientServiceImpl;
import com.pragma.poc.service.impl.ImageServiceImpl;

import org.springframework.mock.web.MockMultipartFile;

@SpringBootTest
class ImageServiceTests {
	@Mock
	ImageRepository repository;
	@InjectMocks
	ImageServiceImpl service;
	
	MockMultipartFile file = (new MockMultipartFile("foo", "foo.txt", MediaType.TEXT_PLAIN_VALUE,
		      "Hello World".getBytes()));
	@Mock
	Image image;
	
	@BeforeEach
	void init() {
		MockitoAnnotations.openMocks(this);
	}
	@Test
	public void getAllClientsTest(){
	    service.getAllImages();
	    verify(repository).findAll();
	}
	
	@Test
	public void findByClientIdTest(){
	    service.findByClientId(image.getClientId());
	    verify(repository).findImageByClientId(image.getClientId());
	}

	@Test
	public void encodeImageTest() throws Exception{
		byte[] data = file.getBytes();
		assertEquals(service.encodeImage(file), Base64.getEncoder().encodeToString(data));
	}
	
	@Test
	public void postImageTest() throws Exception{
	    Mockito.when(service.postImage(file, image.getClientId())).thenReturn("Image created succesfully!");
	}
	@Test
	public void postImageRepitedTest() throws Exception{
		repository.save(image);
	    Mockito.when(service.postImage(file, image.getClientId())).thenReturn("Image exist!");
	}
	
	@Test
	public void decodeImageTest() throws Exception{
		byte[] data = file.getBytes();
		assertEquals(service.decodeImage(Base64.getEncoder().encodeToString(data), "path"),"The image was downloaded");
	}
	
	@Test
	public void deleteImageTest() throws Exception{
		service.deleteImage(image.getClientId());
		verify(repository).deleteByClientId(image.getClientId());
	}
}
