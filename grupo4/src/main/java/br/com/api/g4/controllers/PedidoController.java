package br.com.api.g4.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.api.g4.entities.Pedido;
import br.com.api.g4.services.PedidoService;

@RestController
@RequestMapping("/pedido")
public class PedidoController {

	@Autowired
	PedidoService pedidoService;

/*
	@GetMapping("/count")
	public Integer getCount() {
		return pedidoService.getCount();
	}*/
		
	@PostMapping("/salvar")
	public Pedido salvar(@RequestBody Pedido objetoPedido) {
		return pedidoService.salvar(objetoPedido);
	}

	@GetMapping("/{id}")
	public Pedido acharId(@PathVariable Integer id) {
		return pedidoService.acharId(id);
	}

	@GetMapping("/listar")
	public List<Pedido> listar() {
		return pedidoService.listar();
	}

	@DeleteMapping("/deletar/{id}")
	public void deletar(@PathVariable Integer id) {
		pedidoService.apagar(id);
	}
	
	@DeleteMapping("/deletarLogico/{id}")
	public void deletarLogico(@PathVariable Integer id) {
		pedidoService.apagarLogico(id);
	}
	
	@PutMapping("/atualizar/{id}")
	public Pedido atualizar(@PathVariable Integer id, @RequestBody Pedido objetoTeste) {
		return pedidoService.atualizar(id, objetoTeste);
	}
}