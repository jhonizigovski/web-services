package com.jhoni.web_services.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class ClienteCreateDTO {
	
	@NotBlank(message = "A razão social deve ser informada")
	@Size(min = 2, message = "A razão social deve ter no mínimo 2 caracteres")
	private String razaoSocial;
	
	@Min(value = 1, message = "A empresa deve ter no minimo 1 funcionário.")
	private int funcionario;
	
	@Min(value = 1, message = "O número minimo é 1.")
	private int y;

	

	public String getRazaoSocial() {
		return razaoSocial;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

	public int getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(int funcionario) {
		this.funcionario = funcionario;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	

}
