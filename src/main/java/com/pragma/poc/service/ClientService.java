package com.pragma.poc.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import com.pragma.poc.document.Image;
import com.pragma.poc.entity.Client;
import com.pragma.poc.repository.ImageRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pragma.poc.model.ClientModel;
import com.pragma.poc.model.ImageModel;
import com.pragma.poc.repository.ClientRepository;
import org.springframework.transaction.annotation.Transactional;



@Service
public class ClientService {
	
	@Autowired
	ClientRepository clientRepository;

	@Autowired
	ImageRepository imageRepository;

	@Autowired
	ImageService imageService;


	@Transactional
	public String postClient(ClientModel clientModel) {
		if(!clientRepository.existsById(clientModel.getId())) {
			Client client = new Client();
			BeanUtils.copyProperties(clientModel,client);
			try {
				clientRepository.save(client);
				clientModel.getImages().stream().forEach(i -> {
					Image image = new Image();
					i.setClientId(clientModel.getId());
					try {
						String imageEncoding = imageService.encodeImage(i.getImage());
						i.setImage(imageEncoding);
					} catch (Exception e) {
						System.out.println(e);
					}
					BeanUtils.copyProperties(i, image);
					try {
						imageRepository.save(image);
					} catch (Exception e) {
						throw e;
					}
				});
			} catch(Exception e){
				throw e;
			}
			return "Client created succesfully! "+ client.getId();
			}else {
				return "Duplicated resource";
		}
	}

	public String updateClient(ClientModel clientModel) {
		if (clientRepository.existsById(clientModel.getId())) {
			Client client = clientRepository.findById(clientModel.getId());
			BeanUtils.copyProperties(clientModel, client);
			try {
				clientRepository.save(client);
				clientModel.getImages().stream().forEach(i -> {
					try {
						Image image = imageRepository.findImageByClientId(clientModel.getId()).get(0);
						if (Objects.nonNull(image)) {
							i.setClientId(clientModel.getId());
							try {
								String imageEncoding = imageService.encodeImage(i.getImage());
								i.setImage(imageEncoding);
							} catch (Exception e) {
								System.out.println(e);
							}
							BeanUtils.copyProperties(i, image);
							try {
								imageRepository.save(image);
							} catch (Exception e) {
								throw e;
							}
						}
					} catch (Exception e) {
						throw e;
					}
				});
			} catch (Exception e) {
				throw e;
			}
			return "Client was updated successfully";
		} else {
			return "Client does not exist";
		}
	}

	public List<ClientModel> getAllClients() {
		List<ClientModel> clients = new ArrayList<>();
		List<Client> clientList = new ArrayList<>();
		try{
			clientList = clientRepository.findAll();
		}catch(Exception e){
			throw e;
		}
		if(clientList.size() > 0 ){
			clientList.stream().forEach(c ->{
				ClientModel clientModel = new ClientModel();
				clientModel.setId(c.getId());
				clientModel.setType(c.getType());
				clientModel.setName(c.getName());
				clientModel.setSurname(c.getSurname());
				clientModel.setAge(c.getAge());
				clientModel.setBirthCity(c.getBirthCity());
				List<Image> imageList = new ArrayList<>();
				List<ImageModel> images = new ArrayList<>();
				try{
					imageList = imageRepository.findImageByClientId(c.getId());
				}catch (Exception e){
					throw e;
				}
				if (imageList.size() > 0){
					imageList.stream().forEach(i ->{
						String savePath = "C:\\Users\\andrea.cardenas\\Downloads\\newImage"+i.getId()+".jpg";
						ImageModel imageModel = new ImageModel();
						try{
							imageService.decodeImage(i.getImage(), savePath);
						}catch (Exception e){
						System.out.println(e);
						}
						BeanUtils.copyProperties(i, imageModel);
						imageModel.setImage(savePath);
						images.add(imageModel);
					});
				}
				clientModel.setImages(images);
				clients.add(clientModel);
			});
		}
		return clients;
	}
	public ClientModel getClient(int id) {
		ClientModel clientModel = new ClientModel();
		Client client = clientRepository.findById(id);
		if(client!= null ){
			clientModel.setId(client.getId());
			clientModel.setType(client.getType());
			clientModel.setName(client.getName());
			clientModel.setSurname(client.getSurname());
			clientModel.setAge(client.getAge());
			clientModel.setBirthCity(client.getBirthCity());
			List<Image> imageList = new ArrayList<>();
			List<ImageModel> images = new ArrayList<>();
			try{
				imageList = imageRepository.findImageByClientId(client.getId());
			}catch (Exception e){
				throw e;
			}
			if (imageList.size() > 0){
				imageList.stream().forEach(i ->{
					String savePath = "C:\\Users\\andrea.cardenas\\Downloads\\newImage"+i.getId()+".jpg";
					ImageModel imageModel = new ImageModel();
					try{
						imageService.decodeImage(i.getImage(), savePath);
					}catch (Exception e){
						System.out.println(e);
					}
					BeanUtils.copyProperties(i, imageModel);
					imageModel.setImage(savePath);
					images.add(imageModel);
				});
			}
			clientModel.setImages(images);
		}
		return clientModel;
	}

	public String deleteClient(int id) {
		if (clientRepository.existsById(id)) {
			try {
				clientRepository.deleteById(id);
				try {
					imageRepository.deleteByClientId(id);
				} catch (Exception e) {
					throw e;
				}
			} catch (Exception e) {
				throw e;
			}
			return "Remove succesfully!";
		} else {
			return "Return does not exist";
		}
	}

}
