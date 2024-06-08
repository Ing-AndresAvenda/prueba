package com.alkewallet.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alkewallet.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	User findByUsername(String username); // GENERAMOS UN METODO ABSTRACTO
//	user findByEmail

}


//IM[PLEMENTACION PARA ACCESO A DATOS]