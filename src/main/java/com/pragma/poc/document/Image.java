package com.pragma.poc.document;

import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Document(collection = "images")
public class Image {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String id;
	private String image;
	private int clientId;

	public Image() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public int getClientId() {
		return clientId;
	}

	public void setClientId(int clientId) {
		this.clientId = clientId;
	}

	@Override
	public String toString() {
		return "Image{" +
				"id='" + id + '\'' +
				", image='" + image + '\'' +
				", clientId=" + clientId +
				'}';
	}
}