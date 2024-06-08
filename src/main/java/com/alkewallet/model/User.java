package com.alkewallet.model;

import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Entity
@Table(name="app_user")
public class User {
	
	@Id // LA ID VA SER LA PRIMARY
	@GeneratedValue(strategy = GenerationType.SEQUENCE) // GENERA EL ID AUTOINCREMENTABLE
	private Long id;
	private String username;
	private String password;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(								//UNIMOS LAS TABLAS
			name="user_roles",				
			joinColumns = @JoinColumn(name="user_id"),
			inverseJoinColumns =  @JoinColumn(name="role_id")
	)
	private Set<Rol> roles;

	public User() {
		super();
	}

	
	
}
