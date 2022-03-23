package com.pragma.poc.view;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pragma.poc.model.ClientModel;
import com.pragma.poc.service.ClientService;

@RestController
@RequestMapping("clients")
public class ClienteRest {
	
	@Autowired
	private ClientService cliService;
	
	//Obtener todos los clientes
	@GetMapping("/list")
	public List<ClientModel> getAllClients() {
		return cliService.getAllClients();
	}
	
	//Crear cliente
	@PostMapping("/create")
	public String postClient(@RequestBody ClientModel cliente) {
		return cliService.postClient(cliente);
	}

	//Actualizar un cliente
	@PutMapping("/update/{id}")
	public String update(@PathVariable int id, @RequestBody ClientModel cliente) {
		return cliService.updateClient(cliente);
	}
	
	//Eliminar un cliente
	@DeleteMapping("/delete/{id}")
	public String delete(@PathVariable("id") int id) {
		return cliService.deleteClient(id);
	}
	
	@GetMapping("/{id}")
	public ClientModel findById(@PathVariable("id")Integer id) {
		return cliService.getClient(id);
	}
		
}
