package com.pragma.poc.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.pragma.poc.DTO.ClientDTO;
import com.pragma.poc.entity.Client;


public interface IClientService {
	public Client postClient(ClientDTO client, MultipartFile imageFile) throws Exception;
	public Client updateClient(ClientDTO client, MultipartFile imageFile);
	public List<ClientDTO> getAllClients();
	public ClientDTO getClient(int id) throws Exception;
	public String deleteClient(int id);

}
