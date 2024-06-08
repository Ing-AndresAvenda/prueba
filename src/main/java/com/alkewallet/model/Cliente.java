package com.alkewallet.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class Cliente { // ENTIDAD DE NEGOCIO, REFLEJO DE UNA BASE DE DATOS

	@Id // LA ID VA SER LA PRIMARY
	@GeneratedValue(strategy = GenerationType.SEQUENCE) // GENERA EL ID AUTOINCREMENTABLE
	private Integer id;

	private String nombre;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER) // CONFIGURACION DE LA RELACION 1 A 1
	@JoinColumn(name = "cuenta_id", referencedColumnName = "numeroCuenta")
	private Cuenta cuenta;
	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Cuenta getCuenta() {
		return cuenta;
	}

	public void setCuenta(Cuenta cuenta) {
		this.cuenta = cuenta;
	}

}
