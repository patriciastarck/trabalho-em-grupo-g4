package br.com.api.g4.services;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.api.g4.dto.UsuarioDTO;
import br.com.api.g4.entities.Endereco;
import br.com.api.g4.entities.Role;
import br.com.api.g4.entities.Usuario;
import br.com.api.g4.enums.TipoRoleEnum;
import br.com.api.g4.repositories.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	UsuarioRepository usuarioRepository;

	public Usuario parseDeUsuario(UsuarioDTO objeto) {
		Usuario usuarioNovo = new Usuario();
		
		usuarioNovo.setNome(objeto.getNome());
		usuarioNovo.setNomeUsuario(objeto.getNomeUsuario());
		usuarioNovo.setEmail(objeto.getEmail());
		
		Set<Role> roles = new HashSet<>();
		for(String role: objeto.getRoles()) {
			TipoRoleEnum rolem = TipoRoleEnum.valueOf(role);
			Role rolen =new Role(rolem);
			roles.add(rolen);
		}
		
		usuarioNovo.setRoles(roles);
		
		usuarioNovo.setPassword(objeto.getPassword());
		usuarioNovo.setCpf(objeto.getCpf());
		usuarioNovo.setDataNascimento(objeto.getDataNascimento());

		return usuarioNovo;
	}

	public Integer getCount() {
		return usuarioRepository.contar();
	}

	public Usuario salvar(UsuarioDTO objetoUsuario) {

		return usuarioRepository.save(parseDeUsuario(objetoUsuario));
	}

	public Usuario acharId(Integer id) {
		return usuarioRepository.findById(id).get();
	}

	public List<Usuario> listar() {
		return usuarioRepository.findAll();
	}

	public void deletarLogico(Integer id) {
		Usuario obgUsuario = acharId(id);
		if (obgUsuario != null) {
			obgUsuario.setAtivo(false);
			usuarioRepository.save(obgUsuario);
		}
	}

	public Usuario atualizar(Integer id, UsuarioDTO objetoUsuario) {
		Usuario registroAntigo = acharId(id);
		Usuario usuario =parseDeUsuario(objetoUsuario);
		
		if (usuario.getAtivo() != null) {
			registroAntigo.setAtivo(usuario.getAtivo());
		}
		if (usuario.getNome() != null) {
			registroAntigo.setNome(usuario.getNome());
		}
		if (usuario.getNomeUsuario() != null) {
			registroAntigo.setNomeUsuario(usuario.getNomeUsuario());
		}
		if (usuario.getEmail() != null) {
			registroAntigo.setEmail(usuario.getEmail());
		}
		if (usuario.getCpf() != null) {
			registroAntigo.setCpf(usuario.getCpf());
		}
		if (usuario.getDataNascimento() != null) {
			registroAntigo.setDataNascimento(usuario.getDataNascimento());
		}
		registroAntigo.setId(id);
		return usuarioRepository.save(registroAntigo);
	}

	EnderecoService listarEndereco;

	public List<Endereco> listarEndereco() {
		// TODO Auto-generated method stub
		return listarEndereco.listar();
	}

	public void recuperarSenha(Integer id, String senha) {
		Usuario objTeste = acharId(id);
		if (objTeste != null) {
			objTeste.setPassword(senha);
			usuarioRepository.save(objTeste);
		}
	}

	public void recuperarConta(Integer id) {
		Usuario obgUsuario = acharId(id);
		if (obgUsuario != null) {
			obgUsuario.setAtivo(true);
			usuarioRepository.save(obgUsuario);
		}
	}

	public Usuario findByEmail(String email) {
		return usuarioRepository.findByEmail(email).get();
	}
}