package com.mpk.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class User {
	@Id
	@GeneratedValue
	private Long id;
	private String firstName;
	private String lastName;
	@Column(unique = true)
	private String username;
	private String password;
	@Column(unique = true)
	private String mail;
	private String phone;
	private Boolean active;
	private String role;
	@OneToOne(optional = true)
	private Driver driver;
}
