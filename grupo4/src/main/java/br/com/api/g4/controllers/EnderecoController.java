package br.com.api.g4.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.api.g4.entities.Endereco;
import br.com.api.g4.services.EnderecoService;

@RestController
@RequestMapping("/usuario")
public class EnderecoController {

	@Autowired
	EnderecoService usuarioService;

	@GetMapping("/count")
	public Integer getCount() {
		return usuarioService.getCount();
	}

	@PostMapping("/salvar")
	public List<Endereco> salvar(@RequestBody List<Endereco> objetousuario) {
		return usuarioService.salvar(objetousuario);
	}

	@GetMapping("/{id}")
	public Endereco acharId(@PathVariable Integer id) {
		return usuarioService.acharId(id);
	}

	@GetMapping("/listar")
	public List<Endereco> listar() {
		return usuarioService.listar();
	}

	@PutMapping("/atualizar/{id}")
	public Endereco atualizar(@PathVariable Integer id, @RequestBody Endereco objetousuario) {
		return usuarioService.atualizar(id, objetousuario);
	}
}