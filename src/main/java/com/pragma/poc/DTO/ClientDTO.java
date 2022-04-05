package com.pragma.poc.DTO;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class ClientDTO {
	private String id;
	private String type;
	private String name;
	private String surname;
	private String age;
	private String birthCity; 
	private ImageDTO image;
	public ClientDTO(String id, String type, String name, String surname, String age, String birthCity) {
		super();
		this.id = id;
		this.type = type;
		this.name = name;
		this.surname = surname;
		this.age = age;
		this.birthCity = birthCity;
	}
	
}
