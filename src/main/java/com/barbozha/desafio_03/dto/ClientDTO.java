package com.barbozha.desafio_03.dto;

import java.time.LocalDate;

import com.barbozha.desafio_03.entities.Client;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class ClientDTO {

	private Long id;
	@Size(min = 7, max = 200, message = "Nome precisa ter de 10 a 200 caracteres")
	@NotBlank(message = "Campo requerido")
	@NotNull(message = "O valor do campo name não pode ser nulo")
	private String name;
	
	@Column(unique = true)
	@NotBlank(message = "Campo requerido")
	@Size(min = 11, max = 11, message = "Nome precisa ter de 11 caracteres")
	private String cpf;
	
	@Positive(message = "O preço deve ser positivo")
	@NotBlank(message = "Campo requerido")
	private Double income;
	
	@PastOrPresent(message = "Data inválida")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate birthDate;
	
	@NotBlank(message = "Campo requerido")
	private Integer children;

	public ClientDTO(Long id, String name, String cpf, Double income, LocalDate birthDate, Integer children) {
		this.id = id;
		this.name = name;
		this.cpf = cpf;
		this.income = income;
		this.birthDate = birthDate;
		this.children = children;
	}

	public ClientDTO(Client entity) {
		id = entity.getId();
		name = entity.getName();
		cpf = entity.getCpf();
		income = entity.getIncome();
		birthDate = entity.getBirthDate();
		children = entity.getChildren();
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getCpf() {
		return cpf;
	}

	public Double getIncome() {
		return income;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public Integer getChildren() {
		return children;
	}

}
