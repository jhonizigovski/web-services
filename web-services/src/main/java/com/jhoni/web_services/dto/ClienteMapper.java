package com.jhoni.web_services.dto;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jhoni.web_services.modelo.Cliente;

@Component
public class ClienteMapper {
	
	@Autowired
	private ModelMapper mapper;
	
	public Cliente toEntity(ClienteCreateDTO dto) {
		Cliente entity = mapper.map(dto, Cliente.class);
		return entity;
	}
	
	public ClienteResponseDTO toDTO(Cliente entity) {
		ClienteResponseDTO dto = mapper.map(entity, ClienteResponseDTO.class);
		return dto;
	}
	
	public List<ClienteResponseDTO> toDTO(List<Cliente> clientes) {
		return clientes.stream()
				.map(cliente -> toDTO(cliente))
				.collect(Collectors.toList());
	}

}
