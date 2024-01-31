package com.Springmvc.Repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.Springmvc.Model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>  {
    @Query("select u from Usuario u where u.login = ?1")
	Usuario findUserByLogin(String login);
}
