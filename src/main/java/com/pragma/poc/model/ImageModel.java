package com.pragma.poc.model;


public class ImageModel {


	private String id;
	private String image;
	private int clientId;

	public ImageModel() {
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