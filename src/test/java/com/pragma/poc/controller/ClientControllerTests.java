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
import com.pragma.poc.entity.Client;
import com.pragma.poc.repository.ClientRepository;
import com.pragma.poc.service.impl.ClientServiceImpl;
import com.pragma.poc.service.impl.ImageServiceImpl;

@SpringBootTest
class ClientControllerTests {
	@InjectMocks
	ClientController controller;
	@Mock
	ClientServiceImpl service;
	@Mock
	ImageServiceImpl iService;
	
	MockMultipartFile file = (new MockMultipartFile("foo", "foo.txt", MediaType.TEXT_PLAIN_VALUE,
		      "Hello World".getBytes()));
	@Mock
	ClientDTO clientDTO1;
	@Mock
	Client client;
	
	List<ClientDTO> listClient= new ArrayList<>();
	private static final int ID=1;
	
	@BeforeEach
	void init() {
		MockitoAnnotations.openMocks(this);
		
	}
	
	@Test
	public void postClientNoTest() throws Exception {
		when(iService.postImage(file,"1")).thenReturn("Image created succesfully!");
		when(service.postClient(clientDTO1, file)).thenReturn(client);
		assertEquals(controller.postClient(file, clientDTO1.getId(), clientDTO1.getType(), clientDTO1.getName(), clientDTO1.getSurname(), clientDTO1.getAge(), clientDTO1.getBirthCity()).getStatusCode(),HttpStatus.CREATED);
	}
	
	@Test
	public void getAllClientsTest() {
		listClient.add(clientDTO1);
		when(service.getAllClients()).thenReturn(listClient);
		assertEquals(controller.getAllClients().getBody().size(),listClient.size());
	}
	
	@Test
	public void findByIdTest() throws Exception {
		when(service.getClient(ID)).thenReturn(clientDTO1);
		assertEquals(controller.findById(ID).getBody(),clientDTO1);
	}
	@Test
	public void deleteTest() {
		when(service.deleteClient(ID)).thenReturn("Client was updated successfully");
		assertEquals(controller.delete(ID).getStatusCode(),HttpStatus.NO_CONTENT);
	}
	@Test
	public void updateClientTest() throws Exception {
		when(iService.postImage(file,"1")).thenReturn("Image created succesfully!");
		when(service.updateClient(clientDTO1, file)).thenReturn(client);
		assertEquals(controller.updateClient("1", file, clientDTO1.getType(), clientDTO1.getName(), clientDTO1.getSurname(), clientDTO1.getAge(), clientDTO1.getBirthCity()).getStatusCode(),HttpStatus.OK);
	}
}
