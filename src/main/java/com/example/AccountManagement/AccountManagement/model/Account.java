package com.example.AccountManagement.AccountManagement.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "account")
@Data

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Account {

	@Id
	private String id;
	
	@JsonIgnore
	@OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
	
	@Column(name = "account_name", nullable = false)
    private String accountName;
	
	@Column(name = "balance", nullable = false)
    private Long balance=0L;
}
