package com.jhoni.web_services.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jhoni.web_services.dto.ClienteCreateDTO;
import com.jhoni.web_services.dto.ClienteMapper;
import com.jhoni.web_services.dto.ClienteResponseDTO;
import com.jhoni.web_services.excecao.ClienteNotFoundException;
import com.jhoni.web_services.excecao.ObjetoNaoEncontrado;
import com.jhoni.web_services.excecao.TamanhoExcedido;
import com.jhoni.web_services.modelo.Cliente;
import com.jhoni.web_services.servico.ClienteServico;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
	
	@Autowired
	private ClienteServico clienteServico;
	
	@Autowired
	private ClienteMapper clienteMapper;
	
	@PostMapping
	public ResponseEntity<ClienteResponseDTO> salvar(@RequestBody @Valid ClienteCreateDTO clienteCreateDTO) {
		Cliente cliente = clienteMapper.toEntity(clienteCreateDTO);
		Cliente clienteGravado = clienteServico.gravar(cliente);
		ClienteResponseDTO clienteResponseDTO = clienteMapper.toDTO(clienteGravado);
		return ResponseEntity.status(HttpStatus.CREATED).body(clienteResponseDTO);
		
	}
	
	@GetMapping
	public ResponseEntity<List<ClienteResponseDTO>> buscarTodosClientes(){
		List<Cliente> clientes = clienteServico.buscarTodosClientes();
		List<ClienteResponseDTO> clienteResponseDTO = clienteMapper.toDTO(clientes);
		return ResponseEntity.status(HttpStatus.OK).body(clienteResponseDTO);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Object> BuscarUm(@PathVariable(value = "id") Long id)
																	throws ClienteNotFoundException {
			Cliente clienteGravado = clienteServico.buscarClientePorId(id);
			ClienteResponseDTO clienteResponseDTO = clienteMapper.toDTO(clienteGravado);
			return ResponseEntity.status(HttpStatus.OK).body(clienteResponseDTO);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Object> alterar(@PathVariable(value = "id") Long id,
			@RequestBody @Valid ClienteCreateDTO clienteCreateDTO) throws ClienteNotFoundException {
		Cliente cliente = clienteMapper.toEntity(clienteCreateDTO);
		Cliente clienteGravado = clienteServico.alterarCliente(id, cliente);
		ClienteResponseDTO clienteResponseDTO = clienteMapper.toDTO(clienteGravado);
		return ResponseEntity.status(HttpStatus.OK).body(clienteResponseDTO);
	
	}
	
	@GetMapping("/contar/{id}")
	public ResponseEntity<String> contarCaracteres(@PathVariable Long id) throws ClienteNotFoundException, TamanhoExcedido {
	    
	    Cliente cliente = clienteServico.buscarClientePorId(id);
	    int quantidadeCaracteres = cliente.getRazaoSocial().length();

	    String fraseComSufixo = cliente.getRazaoSocial() + " LTDA";
	    int quantidadeComSufixo = fraseComSufixo.length();
	    
	    if (quantidadeComSufixo > 10) {
	    	throw new TamanhoExcedido("Tamanho excedido.");
		}else {
	    return ResponseEntity.status(HttpStatus.OK).body(fraseComSufixo);}
	}
	
	@GetMapping("/verificar/{id}")
	public ResponseEntity<String> verificarString(@PathVariable Long id) throws ClienteNotFoundException, ObjetoNaoEncontrado {
	    
		String verifica = "xx";
	    Cliente cliente = clienteServico.buscarClientePorId(id);
	    
	    if (cliente == null) {
	        throw new ClienteNotFoundException("Cliente não encontrado.");
	    }
	    
	    String nomeCliente = cliente.getRazaoSocial();
	    
	    if (nomeCliente.contains(verifica)) {
	        return ResponseEntity.status(HttpStatus.OK).body("Contém 'xx'.");
	    } else {
	        throw new ObjetoNaoEncontrado("Objeto não encontrado.");
	    }
	}

	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> apagar(@PathVariable(value = "id") Long id) throws ClienteNotFoundException {
		clienteServico.apagarCliente(id);
		return ResponseEntity.status(HttpStatus.OK).body("Removido com sucesso.");
	
	}
	
}
