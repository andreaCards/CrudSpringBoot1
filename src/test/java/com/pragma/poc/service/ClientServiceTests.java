package com.pragma.poc.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;

import com.pragma.poc.DTO.ClientDTO;
import com.pragma.poc.DTO.ImageDTO;
import com.pragma.poc.document.Image;
import com.pragma.poc.entity.Client;
import com.pragma.poc.exceptions.ImageException;
import com.pragma.poc.exceptions.NoDataFoundException;
import com.pragma.poc.exceptions.ResourceNotFoundException;
import com.pragma.poc.mapper.MapperService;
import com.pragma.poc.repository.ClientRepository;
import com.pragma.poc.repository.ImageRepository;
import com.pragma.poc.service.impl.ClientServiceImpl;
import com.pragma.poc.service.impl.ImageServiceImpl;

import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

@SpringBootTest
class ClientServiceTests {
	@Mock
	ClientRepository repository;
	@Mock
	ImageRepository iRepository;
	@InjectMocks
	ClientServiceImpl service;
	@InjectMocks
	ImageServiceImpl iService;
	@Mock
	MapperService mapper;
	
	Integer integer;

	
	MockMultipartFile file = (new MockMultipartFile("foo", "foo.txt", MediaType.TEXT_PLAIN_VALUE,
			"Hello World".getBytes()));
	ClientDTO clientDTO1;
	Client client1;
	ClientDTO clientDTO2;
	Client client2;
	Image image;
	List<ClientDTO> clientsDTO;
	List<Client> clients;
	int id= 1;
	ImageDTO imageDTO;

	@BeforeEach
	void init() throws IOException {
		MockitoAnnotations.openMocks(this);
		client1=mock(Client.class);
		clientDTO1=mock(ClientDTO.class);
		imageDTO=mock(ImageDTO.class);
		image=mock(Image.class);
		clients= mock(ArrayList.class);
		clientsDTO= mock(ArrayList.class);
		clients.add(client1);
		clientsDTO.add(clientDTO1);
	}

	@Test
	public void getAllClientsTest(){
		Mockito.when(repository.findAll()).thenReturn(clients);

	}
	@Test
	public void postClientsTest() throws Exception{
		Mockito.when(mapper.mapperClientDTOtoClient(clientDTO1)).thenReturn(client1);
		Mockito.when(iService.postImage(file,"1")).thenReturn("Image created succesfully!");
		when(repository.save(client1)).thenReturn(client1);
		when(service.postClient(clientDTO1, file)).thenReturn(client1);
		
	}

	@Test
	public void getAllClientsClientsTest(){
		when(repository.findAll()).thenReturn(clients);
		assertThat(service.getAllClients().size()).isEqualTo(clients.size());
		
	}
	
	@Test
	public void getClientTest() throws ResourceNotFoundException, Exception{
		String data=Base64.getEncoder().encodeToString(file.getBytes());
		Mockito.when(mapper.mapperClientDTOtoClient(clientDTO1)).thenReturn(client1);
		Mockito.when(iService.postImage(file,"1")).thenReturn("Image created succesfully!");
		when(repository.save(client1)).thenReturn(client1);
		when(service.postClient(clientDTO1, file)).thenReturn(client1);
		Mockito.when(iService.existsByclientId("1")).thenReturn(true);
		Mockito.when(mapper.mapperClientToClientDTO(client1)).thenReturn(clientDTO1);
		Mockito.when(iRepository.findImageByClientId("1")).thenReturn(image);
		Mockito.when(repository.findById(id)).thenReturn(client1);
	}
	@Test
	void testGetClient() {
		Client client = new Client(1, "CC", "nombre", "apell", 3, "tola", "khgug");
		when(repository.findById(1)).thenReturn(client);
		String data= service.getClients(1).getName();
		assertThat(data).isEqualTo("nombre");
	}

	@Test
	public void deleteClient(){
		service.deleteClient(id);
	}
	
	@Test
	public void existById(){
		Client client = new Client(1, "CC", "nombre", "apell", 3, "tola", "khgug");
		when(repository.save(client)).thenReturn(client);
		when(service.existsByid(1)).thenReturn(true);		
	}
	
	@Test
	public void updateClient(){
		Mockito.when(mapper.mapperClientDTOtoClient(clientDTO1)).thenReturn(client1);
		assertThatThrownBy(()->service.updateClient(clientDTO1, file)).isInstanceOf(NoDataFoundException.class).hasMessage("Client does not exist");
	}
}
