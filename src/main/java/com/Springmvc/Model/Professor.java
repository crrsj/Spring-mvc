package com.Springmvc.Model;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "professores")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Professor implements Serializable {
   
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
    @NotNull(message = "Nome não pode ser nulo")
    @NotEmpty(message = "Nome não pode ser vazio")
	private String nome;
    @NotNull(message = "Disciplina não pose ser nula")
    @NotEmpty(message = "Disciplina não pode ser vazia")
	private String disciplina;
    @NotNull(message ="Nível não pode ser nulo")
    @NotEmpty(message = "Nível não pode ser vazio")
	private String nivel;
	@OneToMany(mappedBy = "professor",orphanRemoval = true, cascade = CascadeType.ALL)
	private List<Telefone>telefones;
}
