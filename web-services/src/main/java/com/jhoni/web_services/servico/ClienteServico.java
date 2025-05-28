package com.jhoni.web_services.servico;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jhoni.web_services.excecao.ClienteNotFoundException;
import com.jhoni.web_services.modelo.Cliente;
import com.jhoni.web_services.repositorio.ClienteRepositorio;

@Service
public class ClienteServico {
	
	@Autowired
	private ClienteRepositorio clienteRepositorio;
	
	public Cliente gravar(Cliente cliente) {
		return clienteRepositorio.save(cliente);
	}
	
	public List<Cliente> buscarTodosClientes() {
		return clienteRepositorio.findAll();
		
	}
	
	public Cliente buscarClientePorId(Long id) throws ClienteNotFoundException {
		Optional<Cliente> opt = clienteRepositorio.findById(id);
		if (opt.isPresent()) {
			return opt.get();
		} else {
			throw new ClienteNotFoundException("Cliente com id: " + id + " não existe.");
		}
	}
	
	public Cliente alterarCliente(Long id, Cliente cliente) throws ClienteNotFoundException {
		Cliente clienteGravado = buscarClientePorId(id);
		clienteGravado.setFuncionario(cliente.getFuncionario());
		clienteGravado.setY(cliente.getY());
		clienteGravado.setRazaoSocial(cliente.getRazaoSocial());
		return clienteRepositorio.save(clienteGravado);
		
	}
	
	public void apagarCliente(Long id) throws ClienteNotFoundException {
		Cliente cliente = buscarClientePorId(id);
		clienteRepositorio.delete(cliente);
	}

}
