package com.alkewallet.model;

import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data    //LOMBOK  CREEA LOS GETTER Y SETTER Y CONSTRUCTORES
@AllArgsConstructor
@Entity
public class Rol {
	
	@Id // LA ID VA SER LA PRIMARY
	@GeneratedValue(strategy = GenerationType.SEQUENCE) // GENERA EL ID AUTOINCREMENTABLE
	private Long id;
	private String name;
	@ManyToMany(mappedBy = "roles")
	private Set<User> user;
	
	
	public Rol() {
		super();
	}
	
	

}
