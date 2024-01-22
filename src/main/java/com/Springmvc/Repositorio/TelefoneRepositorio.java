package com.Springmvc.Repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.Springmvc.Model.Telefone;

public interface TelefoneRepositorio extends JpaRepository<Telefone, Long>  {
	@Query("select t from Telefone t where t.professor.id = ?1")
	public List<Telefone>getTelefones(Long idprofessor);

}
