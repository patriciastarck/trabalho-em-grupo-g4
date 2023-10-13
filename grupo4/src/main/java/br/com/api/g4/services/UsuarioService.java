package br.com.api.g4.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.api.g4.entities.Endereco;
import br.com.api.g4.entities.Usuario;
import br.com.api.g4.repositories.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	UsuarioRepository usuarioRepository;

	public Integer getCount() {
		return usuarioRepository.contar();
	}

	public List<Usuario> salvar(List<Usuario> objetoUsuario) {
		return usuarioRepository.saveAll(objetoUsuario);
	}

	public Usuario acharId(Integer id) {
		return usuarioRepository.findById(id).get();
	}

	public List<Usuario> listar() {
		return usuarioRepository.findAll();
	}

	public void apagar(Integer id) {
		Usuario obgUsuario = acharId(id);
		if (obgUsuario != null) {
			obgUsuario.setAtivo(false);
			usuarioRepository.save(obgUsuario);
		}
	}

	public Usuario atualizar(Integer id, Usuario objetoUsuario) {
		Usuario registroAntigo = acharId(id);
		if (objetoUsuario.getAtivo() != null) {
			registroAntigo.setAtivo(objetoUsuario.getAtivo());
		}
		if (objetoUsuario.getNome() != null) {
			registroAntigo.setNome(objetoUsuario.getNome());
		}
		if (objetoUsuario.getNomeUsuario() != null) {
			registroAntigo.setNomeUsuario(objetoUsuario.getNomeUsuario());
		}
		if (objetoUsuario.getEmail() != null) {
			registroAntigo.setEmail(objetoUsuario.getEmail());
		}
		if (objetoUsuario.getCpf() != null) {
			registroAntigo.setCpf(objetoUsuario.getCpf());
		}
		if (objetoUsuario.getDataNascimento() != null) {
			registroAntigo.setDataNascimento(objetoUsuario.getDataNascimento());
		}
		registroAntigo.setId(id);
		return usuarioRepository.save(registroAntigo);
	}

	EnderecoService listarEndereco;
	
	public List<Endereco> listarEndereco() {
		// TODO Auto-generated method stub
	return listarEndereco.listar();
	}
	
	
	
}