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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.api.g4.dto.UsuarioDTO;
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

		return usuarioService.getCount();
	}

	@PostMapping("/salvar")
	public Usuario salvar(@RequestBody UsuarioDTO objetousuario) {
		emailService.envioEmailCadastro(objetousuario);
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
	public void deletarLogico(@PathVariable Integer id) {
		emailService.envioEmailDelete(acharId(id));
		usuarioService.deletarLogico(id);
	}

	@PutMapping("/atualizar/{id}")
	public Usuario atualizar(@PathVariable Integer id, @RequestBody Usuario objetousuario) {
		return usuarioService.atualizar(id, objetousuario);
	}

	@GetMapping("/listarEndereco/{id}")
	public List<Endereco> getEndereco(@PathVariable Integer id) {
		return usuarioService.listarEndereco();
	}
	//TODO verificar a mensagem de recuperaçao de senha é valida
	@PutMapping("recuperarSenha/{id}")
	public void recuperarSenha(@PathVariable Integer id, @RequestParam String senha) {
		usuarioService.recuperarSenha(id, senha);
		emailService.envioEmailRecuperacaoSenha(acharId(id));
	}

	@PutMapping("recuperarConta/{id}")
	public void recuperarConta(@PathVariable Integer id) {
		usuarioService.recuperarConta(id);
		emailService.envioEmailRecuperacaoConta(acharId(id));
	}
}