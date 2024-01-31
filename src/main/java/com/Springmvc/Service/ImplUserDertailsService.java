package com.Springmvc.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.Springmvc.Model.Usuario;
import com.Springmvc.Repositorio.UsuarioRepository;
@Service
public class ImplUserDertailsService implements UserDetailsService{
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = usuarioRepository.findUserByLogin(username);
		if(usuario == null) {
			throw new UsernameNotFoundException("Usuário não foi encontrado");
		}
		return usuario;
	}

	
}
