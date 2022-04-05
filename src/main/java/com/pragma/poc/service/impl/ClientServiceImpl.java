package com.pragma.poc.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.pragma.poc.DTO.ClientDTO;
import com.pragma.poc.DTO.ImageDTO;
import com.pragma.poc.document.Image;
import com.pragma.poc.entity.Client;
import com.pragma.poc.exceptions.ClientAlreadyExistsException;
import com.pragma.poc.exceptions.ClientServiceException;
import com.pragma.poc.exceptions.ImageException;
import com.pragma.poc.exceptions.NoDataFoundException;
import com.pragma.poc.exceptions.ResourceNotFoundException;

import com.pragma.poc.mapper.MapperService;
import com.pragma.poc.repository.ImageRepository;
import com.pragma.poc.service.IClientService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pragma.poc.repository.ClientRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;



@Service
public class ClientServiceImpl implements IClientService {
	
	@Autowired
	ClientRepository clientRepository;

	@Autowired
	ImageRepository imageRepository;

	@Autowired
	ImageServiceImpl imageService;
	
	@Autowired
	MapperService mapper;


	@Transactional
	public Client postClient(ClientDTO clientDTO, MultipartFile imageFile) throws ClientAlreadyExistsException{
			Client client = mapper.mapperClientDTOtoClient(clientDTO);
			if(!existsByid(client.getId())){
				client.setImage(imageFile.getOriginalFilename());
				clientRepository.save(client);
				return client;
				}
			throw new ClientAlreadyExistsException("Client Already Exists");	
	}
	
	public Client updateClient(ClientDTO clientDTO, MultipartFile imageFile)throws NoDataFoundException{
		Client client = mapper.mapperClientDTOtoClient(clientDTO);
		if(existsByid(client.getId())){
			client.setImage(imageFile.getOriginalFilename());
			clientRepository.save(client);
			return client;
		}
		throw new NoDataFoundException("Client does not exist");		
	}
	
	public List<ClientDTO> getAllClients() throws NoDataFoundException{
		List<Client> clientList = new ArrayList<>();
		List<ClientDTO> clients = new ArrayList<>();
		clientList = clientRepository.findAll();
		if(clientList.size() > 0 ){
			clientList.stream().forEach(c ->{
				ClientDTO clientDTO = new ClientDTO();
				ImageDTO imageDTO = new ImageDTO();
				Image image=imageRepository.findImageByClientId(String.valueOf(c.getId()));
				if (image!=null){
					imageDTO= mapper.mapperImageToImageDTO(image);
					clientDTO= mapper.mapperClientToClientDTO(c);
					clientDTO.setImage(imageDTO);
					clients.add(clientDTO);
				}});
			return clients;
			}
		
		return clients;
	}
	
	public ClientDTO getClient(int id) throws ResourceNotFoundException, Exception{
		Client client = clientRepository.findById(id);
		ClientDTO clientDTO = new ClientDTO();
		if(client!=null ){
			clientDTO= mapper.mapperClientToClientDTO(client);		
			clientDTO.setImage(imageService.getImage(clientDTO.getId()));	
			return clientDTO;
			}
		throw new ResourceNotFoundException("Client does not exist");
	}
	
	
	public String deleteClient(int id)  {
		clientRepository.deleteById(id);
		imageRepository.deleteByClientId(String.valueOf(id));
		return "Remove succesfully!";
	}
	
	public Client getClients(int id)throws ResourceNotFoundException{
		Client client = clientRepository.findById(id);
		return client;
	}
	
	public Boolean existsByid(int id) {
		return clientRepository.existsById(id);
	}
}
