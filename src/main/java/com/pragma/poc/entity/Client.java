package com.pragma.poc.entity;


import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Entity
@Getter @Setter @AllArgsConstructor @NoArgsConstructor @ToString
public class Client {

    @Id
    private int id;
    private String type;
    private String name;
    private String surname;
    private int age;
    private String birthCity;
	private String Image;
}
