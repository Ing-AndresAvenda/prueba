package com.alkewallet.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Entity

public class Cuenta {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE) // GENERA EL ID AUTOINCREMENTABLE
	private Integer numeroCuenta;
	private String nombreCliente;
	private Double saldoActual;
	private String verDatos;
	
	@OneToOne(mappedBy = "cuenta", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Cliente cliente;
	
	public Cuenta() {
		super();
	}

	
}
