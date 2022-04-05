package com.pragma.poc.mapper;

import org.modelmapper.ModelMapper;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pragma.poc.DTO.ClientDTO;
import com.pragma.poc.DTO.ImageDTO;
import com.pragma.poc.document.Image;
import com.pragma.poc.entity.Client;

@Service
public class MapperService {
	public static ModelMapper modelMapper = new ModelMapper();
	
	@Transactional
	public Client mapperClientDTOtoClient(ClientDTO clientDTO) {
		Client client= modelMapper.map(clientDTO, Client.class);
		return client;
	}
	
	public ClientDTO mapperClientToClientDTO(Client client) {
		ClientDTO clientDTO= modelMapper.map(client, ClientDTO.class);
		return clientDTO;
	}
	
	public ImageDTO mapperImageToImageDTO(Image image) {
		ImageDTO imageDTO= modelMapper.map(image, ImageDTO.class);
		return imageDTO;
	}

}
