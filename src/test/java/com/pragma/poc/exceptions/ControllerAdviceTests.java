package com.pragma.poc.exceptions;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;

import com.pragma.poc.DTO.ClientDTO;
import com.pragma.poc.entity.Client;
import com.pragma.poc.repository.ClientRepository;
import com.pragma.poc.service.impl.ClientServiceImpl;

@SpringBootTest
class ControllerAdviceTests {
	@InjectMocks
	ExceptionHandlerControllerAdvice controller;
	
	@Mock
	ClientServiceImpl service;
	
	MockMultipartFile file = (new MockMultipartFile("foo", "foo.txt", MediaType.TEXT_PLAIN_VALUE,
		      "Hello World".getBytes()));

	ErrorObject error;
	
	List<ClientDTO> listClient= new ArrayList<>();
	private static final int ID=1;
	
	@BeforeEach
	void init() {
		MockitoAnnotations.openMocks(this);
		ErrorObject error =Mockito.mock(ErrorObject.class);
		error.setMessage("Resource not found");
		error.setStatusCode(404);
	}

	
	@Test
	public void handleResourceNotFoundExceptionTest() throws Exception {
		ResponseEntity<ErrorObject> exception = controller.handleResourceNotFoundException(new ResourceNotFoundException("Resource not found"));
		    assertEquals(exception.getStatusCode(), HttpStatus.NOT_FOUND);
		}
	
	@Test
	public void handleNoDataFoundExceptionTest() throws Exception {
		ResponseEntity<ErrorObject> exception = controller.handleNoDataFoundException(new NoDataFoundException("Resource not found"));
		    assertEquals(exception.getStatusCode(), HttpStatus.NO_CONTENT);
		}
	@Test
	public void handleClientServiceExceptionTest() throws Exception {
		ResponseEntity<ErrorObject> exception = controller.handleClientServiceException(new ClientServiceException("error"));
		    assertEquals(exception.getStatusCode(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	@Test
	public void handleBadRequestExceptionTest() throws Exception {
		ResponseEntity<ErrorObject> exception = controller.handleBadRequestException(new BadRequestException("error"));
		    assertEquals(exception.getStatusCode(), HttpStatus.BAD_REQUEST);
		}
	@Test
	public void handleClientAlreadyExistsTest() throws Exception {
		ResponseEntity<ErrorObject> exception = controller.handleClientAlreadyExists(new ClientAlreadyExistsException("error"));
		    assertEquals(exception.getStatusCode(), HttpStatus.CONFLICT);
		}
	
}
