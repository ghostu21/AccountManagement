package com.example.AccountManagement.AccountManagement.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Entity
@Table(name = "user")
@Data

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "email", nullable = false, unique = true)
	private String email;

	@Column(name = "password", nullable = false)
	private String password;

	@Column(name = "mobile", nullable = false, unique = true)
	private String mobile;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "dob", nullable = false)
	private Date dob;

	@Column(name = "created_time", nullable = false)
	private Date createdTime;

	@Column(name = "last_modified_time", nullable = false)
	private Date lastModifiedTime;
	
	private String role;

}
