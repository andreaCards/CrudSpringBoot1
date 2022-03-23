package com.pragma.poc.entity;


import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
public class Client {

    @Id
    private int id;
    private String type;
    private String name;
    private String surname;
    private int age;
    private String birthCity;

    public Client() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getBirthCity() {
        return birthCity;
    }

    public void setBirthCity(String birthCity) {
        this.birthCity = birthCity;
    }


	@Override
	public String toString() {
		return "ClientModel{" +
				"id=" + id +
				", type=" + type +
				", name='" + name + '\'' +
				", surname='" + surname + '\'' +
				", age=" + age +
				", birthCity='" + birthCity + '\'' +
				'}';
	}
}
