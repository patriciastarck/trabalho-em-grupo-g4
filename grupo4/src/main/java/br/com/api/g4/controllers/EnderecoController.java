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

import br.com.api.g4.dto.EnderecoDTO;
import br.com.api.g4.entities.Endereco;
import br.com.api.g4.services.EnderecoService;

@RestController
@RequestMapping("/endereco")
public class EnderecoController {

	@Autowired
	EnderecoService enderecoService;

	@GetMapping("/count")
	public Integer getCount() {
		return enderecoService.getCount();
	}

	@PostMapping("/salvar")
	public Endereco salvar(@RequestBody EnderecoDTO endereco) {
		return enderecoService.salvar(endereco);
	}

	@GetMapping("/{id}")
	public Endereco acharId(@PathVariable Integer id) {
		return enderecoService.acharId(id);
	}

	@GetMapping("/listar")
	public List<Endereco> listar() {
		return enderecoService.listar();
	}

	@PutMapping("/atualizar/{id}")
	public Endereco atualizar(@PathVariable Integer id, @RequestBody Endereco objetoEndereco) {
		return enderecoService.atualizar(id, objetoEndereco);
	}
}