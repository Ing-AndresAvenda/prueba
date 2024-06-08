package com.alkewallet.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.header.Header;

import com.alkewallet.services.CustonUserDetailService;

@Configuration
@EnableWebSecurity // HABILITAMOS LA SEGURIDAD
public class SecurityConfig { // SE CONFIGIRARAR LA SEGURIDAD
	
	@Autowired
	private CustonUserDetailService userDetailsService;
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		
		http
			.authorizeHttpRequests(authorizeRequests ->
				authorizeRequests
					.requestMatchers("/", "/home", "/login", "h2-console/**").permitAll()//PERMITIR ACCESO A TODAS ESAS RUTAS
					.anyRequest().authenticated()
			)
			.formLogin(formLogin ->
				formLogin
					.loginPage("/login")	//NUESTRAPAGINA PERSONALIZXADAD
					.defaultSuccessUrl("/cuenta", true)
					.permitAll()
					
			)
			.headers(headers -> headers.frameOptions().sameOrigin()) // PERMITIMOS QUE H2 SE MUESTR EN UN IFRAME
			.userDetailsService(userDetailsService);
			
		
		return  http.build();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		
		return new BCryptPasswordEncoder();
		
	}
//	
//	@Bean
//	public UserDetailsService userDetailsService() {
//		
//		UserDetails user = User.withDefaultPasswordEncoder()
//				.username("Prueba")
//				.password("1234")
//				.roles("USER")
//				.build();
//		return new InMemoryUserDetailsManager(user);
//	}
}
