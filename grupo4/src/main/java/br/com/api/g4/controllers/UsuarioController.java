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

import br.com.api.g4.entities.Endereco;
import br.com.api.g4.entities.Usuario;
import br.com.api.g4.services.EmailService;
import br.com.api.g4.services.UsuarioService;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

	@Autowired
	UsuarioService usuarioService;
	
	@Autowired
	EmailService emailService;

	@GetMapping("/count")
	public Integer getCount() {
		emailService.envioEmailCadastro(acharId(1));
		return usuarioService.getCount();
	}

	@PostMapping("/salvar")
	public List<Usuario> salvar(@RequestBody List<Usuario> objetousuario) {
		return usuarioService.salvar(objetousuario);
	}

	@GetMapping("/{id}")
	public Usuario acharId(@PathVariable Integer id) {
		return usuarioService.acharId(id);
	}

	@GetMapping("/listar")
	public List<Usuario> listar() {
		return usuarioService.listar();
	}

	@DeleteMapping("/deletarLogico/{id}")
	public void apagar(@PathVariable Integer id) {
		usuarioService.apagar(id);
	}

	@PutMapping("/atualizar/{id}")
	public Usuario atualizar(@PathVariable Integer id, @RequestBody Usuario objetousuario) {
		return usuarioService.atualizar(id, objetousuario);
	}
	
	@GetMapping("/listarEndereco/{id}")
	public List<Endereco> getEndereco (@PathVariable Integer id){
		return usuarioService.listarEndereco();
	}
	
}