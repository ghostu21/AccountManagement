package com.example.AccountManagement.AccountManagement.model;

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
@Table(name = "payment")
@Data

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Payment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "from_account_id", nullable = false)
    private String fromAccountId;
	@Column(name = "to_account_id", nullable = false)
    private String toAccountId;
	@Column(name = "amount", nullable = false)
    private Long amount;
}
