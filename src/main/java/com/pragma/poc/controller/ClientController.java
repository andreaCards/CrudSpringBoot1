package com.pragma.poc.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.pragma.poc.DTO.ClientDTO;
import com.pragma.poc.entity.Client;
import com.pragma.poc.exceptions.BadRequestException;
import com.pragma.poc.exceptions.ClientServiceException;
import com.pragma.poc.exceptions.NoDataFoundException;
import com.pragma.poc.exceptions.ResourceNotFoundException;
import com.pragma.poc.service.impl.ClientServiceImpl;
import com.pragma.poc.service.impl.ImageServiceImpl;

@RestController
@RequestMapping("clients")
public class ClientController {
	
	@Autowired
	private ClientServiceImpl cliService;
	
	@Autowired
	private ImageServiceImpl iService;
	
	@PostMapping("/create")
	public ResponseEntity<Client> postClient(@RequestParam("image") MultipartFile image, @RequestParam("id") String id, @RequestParam("name") String name, @RequestParam("surname") String surname, @RequestParam("type") String type, @RequestParam("age") String age, @RequestParam("birthCity") String birthCity) throws Exception{
			iService.postImage(image, id);
			return new ResponseEntity<Client>(cliService.postClient(new ClientDTO(id, type, name, surname, age, birthCity), image), HttpStatus.CREATED);
	}
	

	@GetMapping("/list")
	public ResponseEntity<List<ClientDTO>> getAllClients() throws ClientServiceException {
			return new ResponseEntity<List<ClientDTO>>(cliService.getAllClients(), HttpStatus.OK);
	}
	

	@GetMapping("/{id}")
	public ResponseEntity<ClientDTO> findById(@PathVariable("id")Integer id) throws Exception {
			ClientDTO cli = cliService.getClient(id);
			return new ResponseEntity<ClientDTO>(cli, HttpStatus.OK);
	}
	

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> delete(@PathVariable("id") int id) {
			return new ResponseEntity<String>(cliService.deleteClient(id),HttpStatus.NO_CONTENT);					
	}
	

	@PutMapping("/update/{id}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Client> updateClient(@PathVariable("id") String id, @RequestParam("image") MultipartFile image, @RequestParam("name") String name, @RequestParam("surname") String surname, @RequestParam("type") String type, @RequestParam("age") String age, @RequestParam("birthCity") String birthCity) throws Exception {
			iService.deleteImage(id);
			iService.postImage(image, id);
			return new ResponseEntity<Client>(cliService.updateClient(new ClientDTO(id, type, name, surname, age, birthCity), image), HttpStatus.OK);			
	}	
}
