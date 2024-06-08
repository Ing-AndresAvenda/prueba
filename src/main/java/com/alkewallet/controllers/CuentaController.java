package com.alkewallet.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.alkewallet.dto.CuentaDTO;
import com.alkewallet.model.Cuenta;
import com.alkewallet.services.CuentaServices;
import org.springframework.web.bind.annotation.PostMapping;



@Controller
@RequestMapping("/cuenta")
public class CuentaController {
	
	@Autowired // NOTACION QUE OPERMITE RECIBIR UNA INSTANCIA INYECTADAD
	private CuentaServices cuentaServices;
	
	@GetMapping
	public String verCuenta(Model model) {
		// HACEMOS LA LLAMADAS 
		
		
		String usuarioActual = obtenerUserLogin();
		model.addAttribute("username", usuarioActual);  // PATRA QIUE PODAMOS TOMAR EL ATRIBUTO 
		
		CuentaDTO cuentaDTO = cuentaServices.getCuenta();
		model.addAttribute("vistaCuentaUsuario", cuentaDTO);
		return "cuentaTemplate"; // NOMBRE DEL HTML QUE SE VA ABRIR
	}

	
	
	@PostMapping("/depositar")
	public String depositar(@RequestParam Double montoDepositar, Model model) {
		
		Cuenta cuenta = cuentaServices.depositar(montoDepositar);
		model.addAttribute("vistaCuentaUsuario", cuenta);
		return "cuentaTemplate";
		
		
	}
	@PostMapping("/retirar")
	public String retirar(@RequestParam Double montoRetiro, Model model) {
		Cuenta cuenta = cuentaServices.retirar(montoRetiro);
		model.addAttribute("vistaCuentaUsuario", cuenta);
		return "cuentaTemplate";
		
		
	}
	
	private String obtenerUserLogin() {
		// NOS TRAE LA INFORMACION DEL USUSARIO LOGEADO
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String usuarioActual = authentication.getName();
		return usuarioActual;
	}
	

}
