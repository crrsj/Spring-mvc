package com.Springmvc.Repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.Springmvc.Model.Professor;

public interface ProfessorRepositorio extends JpaRepository<Professor, Long> {
	
	@Query("SELECT p FROM Professor p WHERE p.nome like %?1%")
	List<Professor>findProfessorByName(String nome);
	

}
