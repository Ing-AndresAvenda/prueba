package com.alkewallet.services;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;



import com.alkewallet.dto.CuentaDTO;
import com.alkewallet.model.Cliente;
import com.alkewallet.model.Cuenta;
import com.alkewallet.model.Rol;
import com.alkewallet.model.User;
import com.alkewallet.repository.ClienteRepository;
import com.alkewallet.repository.CuentaRespository;
import com.alkewallet.repository.RolRepository;
import com.alkewallet.repository.UserRepository;

import jakarta.annotation.PostConstruct;

@Service
public class CuentaServices {

	//@Autowired
//	CuentaDAO cuentaDAO;
	
	@Autowired
	private CuentaRespository cuentaRespository;   //INYECTAMOS NUESTRO REPOSITORY
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private RolRepository rolRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	
	private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	
	private static Integer cuentaFalsaPrueba = 1;
	// CREAMOS UNA CUENTA EN BASE DE DATOS AL INICIAR LA APLICACION
	@PostConstruct
	public void init() { // 
//		Cliente cliente = new Cliente();
//		
//		Cuenta cuenta = new Cuenta();
//		//cuenta.setNumeroCuenta(12345); // SE BORRA YA QUE SE ESTA GENERANDO AUTOMATICAMENTE
//		cuenta.setNombreCliente("Andres");
//		cuenta.setSaldoActual(200000.0);
//		
//		cliente.setNombre("Andres Titular");
//		clienteRepository.save(cliente);
//		
//		cliente.setCuenta(cuenta);
//		cuentaRespository.save(cuenta);
		
		 // Crear la cuenta
	    Cuenta cuenta = new Cuenta();
	    cuenta.setNombreCliente("Andres");
	    cuenta.setSaldoActual(200000.0);

	    // Crear el cliente y establecer la relación con la cuenta
	    Cliente cliente = new Cliente();
	    cliente.setNombre("Andres Titular");
	    cliente.setCuenta(cuenta);  // Establecer la relación

	    // Guardar el cliente, lo que también guardará la cuenta debido a CascadeType.ALL
	    clienteRepository.save(cliente);
	    
	    // CREAMOS LOS ROLES
	  Rol adminRol = new Rol();
	  adminRol.setName("ROLE_ADMIN");
	  rolRepository.save(adminRol);
	  
	  Rol userRol = new Rol();
	  userRol.setName("ROLE_USER");
	  rolRepository.save(userRol);
	
	  
	  //CREAMOS LOS USUARIOS
	  User admin = new User();
	  admin.setUsername("admin");
	  admin.setPassword(passwordEncoder.encode("admin"));
	  Set<Rol> adminRoles = new HashSet<>();
	  adminRoles.add(adminRol);
	  admin.setRoles(adminRoles);
	  userRepository.save(admin);
	  
	  User user = new User();
	  user.setUsername("user");
	  user.setPassword(passwordEncoder.encode("user"));
	  Set<Rol> userRoles = new HashSet<>();
	  userRoles.add(userRol);
	  user.setRoles(userRoles);
	  userRepository.save(user);
	  
	}
	
	// USANDO JPA REPOSITORY
		public CuentaDTO getCuenta() {
			
			Cuenta cuenta = cuentaRespository.findById(cuentaFalsaPrueba).orElseThrow(
					()-> new RuntimeException("Cuenta no encontrada")
					);
			return convertToDTO(cuenta);
		}

	public Cuenta depositar(Double montoDeposito) {
		Cuenta cuenta = cuentaRespository.findById(cuentaFalsaPrueba).orElseThrow(
				()-> new RuntimeException("Cuenta no encontrada")
				);
		Double saldoActual = cuenta.getSaldoActual();
		cuenta.setSaldoActual( saldoActual += montoDeposito);
		cuentaRespository.save(cuenta);
		return cuenta;
	}
	
	public Cuenta retirar(Double montoRetiro) {
		Cuenta cuenta = cuentaRespository.findById(cuentaFalsaPrueba).orElseThrow(
				()-> new RuntimeException("Cuenta no encontrada")
				);
		Double saldoActual = cuenta.getSaldoActual();
		cuenta.setSaldoActual( saldoActual -= montoRetiro);
		cuentaRespository.save(cuenta);
		return cuenta;
	}

	public Double verSaldo(Cuenta cuenta) {

		return cuenta.getSaldoActual();
	}

	public String verDatos(Cuenta cuenta) {

		return cuenta.getVerDatos();

	}

//	public CuentaDTO getCuenta() {
//		// TODO LLAMAR AL DAO
//		// Cuenta cuenta = new Cuenta("asdasd", 34534,544554, "");
//		Cuenta cuenta = cuentaDAO.findCuenta();
//		return convertToDTO(cuenta);
//
//	}
//	// USANDO JPA REPOSITORY
//	public CuentaDTO getCuenta() {
//		
//		
//		Cuenta cuenta = cuentaDAO.findCuenta();
//		return convertToDTO(cuenta);
//
//	}

	

	private CuentaDTO convertToDTO(Cuenta cuenta) {
		CuentaDTO dto = new CuentaDTO();
		dto.setNombreCliente(cuenta.getNombreCliente());
		dto.setNumeroCuenta(cuenta.getNumeroCuenta());
		dto.setSaldoActual(cuenta.getSaldoActual());
		return dto;

	}

}
