package com.pragma.poc.document;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Document(collection = "images")
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class Image {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String id;
	private String image;
	private String name;
	private String clientId;
}