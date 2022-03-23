package com.pragma.poc.service;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.pragma.poc.entity.Client;
import com.pragma.poc.model.ClientModel;
import com.pragma.poc.repository.ClientRepository;

@SpringBootTest
class ClientServiceTests {
	@Mock
	ClientRepository repository;

	@InjectMocks
	ClientService service;
	private static final int ID = 1;
	ClientModel clientModel = mock(ClientModel.class);
	Client client = mock(Client.class);
	@BeforeEach
	public void init() {
		MockitoAnnotations.openMocks(this);

	}
	@Test
	void contextLoads() {
	}
	@Test
	public void getClientTest(){
	    service.getClient(ID);
	    verify(repository).findById(ID);
	}

	@Test
	public void getAllClients(){
	    service.getAllClients();
	    verify(repository).findAll();
	}

	@Test
	public void postClient(){
	    service.postClient(clientModel);
	    verify(repository).save(client);
	}
	
	@Test
	public void updateClient(){
	    service.updateClient(clientModel);
	    verify(repository).save(client);
	}
	@Test
	public void deleteClient(){
	    service.deleteClient(ID);
	    verify(repository).deleteById(ID);
	}

}
