package com.Springmvc.Repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Springmvc.Model.Telefone;

public interface TelefoneRepositorio extends JpaRepository<Telefone, Long>  {

}
