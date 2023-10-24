package br.com.api.g4.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.api.g4.dto.UsuarioDTO;
import br.com.api.g4.dto.UsuarioRespostaDTO;
import br.com.api.g4.entities.Endereco;
import br.com.api.g4.entities.Role;
import br.com.api.g4.entities.Usuario;
import br.com.api.g4.enums.TipoRoleEnum;
import br.com.api.g4.repositories.EnderecoRepository;
import br.com.api.g4.repositories.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	UsuarioRepository usuarioRepository;
	@Autowired
	EnderecoService listarEndereco;
	@Autowired
	EnderecoRepository enderecoRepository;

	public Usuario parseDeUsuario(UsuarioDTO objeto) {
		Usuario usuarioNovo = new Usuario();

		usuarioNovo.setNome(objeto.getNome());
		usuarioNovo.setNomeUsuario(objeto.getNomeUsuario());
		usuarioNovo.setEmail(objeto.getEmail());

		Set<Role> roles = new HashSet<>();
		for (String role : objeto.getRoles()) {
			TipoRoleEnum rolem = TipoRoleEnum.valueOf(role);
			Role rolen = new Role(rolem);
			roles.add(rolen);
		}

		usuarioNovo.setRoles(roles);

		usuarioNovo.setPassword(objeto.getPassword());
		usuarioNovo.setCpf(objeto.getCpf());
		usuarioNovo.setDataNascimento(objeto.getDataNascimento());

		return usuarioNovo;
	}

	public UsuarioRespostaDTO parseDeUsuarioResposta(Usuario usuario) {
		UsuarioRespostaDTO usuarioResposta = new UsuarioRespostaDTO();

		usuarioResposta.setNome(usuario.getNome());
		usuarioResposta.setNomeUsuario(usuario.getNomeUsuario());
		usuarioResposta.setEmail(usuario.getEmail());
		usuarioResposta.setEndereco(usuario.getEndereco());

		return usuarioResposta;
	}

	public Integer getCount() {
		return usuarioRepository.contar();
	}

	public Usuario salvar(UsuarioDTO objetoUsuario) {

		return usuarioRepository.save(parseDeUsuario(objetoUsuario));
	}

	public UsuarioRespostaDTO acharId(Integer id) {
		UsuarioRespostaDTO UsuarioResposta = parseDeUsuarioResposta(usuarioRepository.findById(id).get());
		return UsuarioResposta;
	}

	public List<UsuarioRespostaDTO> listar() {
		List<UsuarioRespostaDTO> usuarioResposta = new ArrayList<>();
		List<Usuario> usuarios = usuarioRepository.findAll();

		for (Usuario usuario : usuarios) {
			usuarioResposta.add(parseDeUsuarioResposta(usuario));
		}
		return usuarioResposta;
	}

	public void deletarLogico(Integer id) {
		if (usuarioRepository.findById(id).get() != null) {
			throw new EntityNotFoundException("Esse usuario não existe");
		} else {
			Usuario obgUsuario = usuarioRepository.findById(id).get();
			if (obgUsuario != null) {
				obgUsuario.setAtivo(false);
				usuarioRepository.save(obgUsuario);
			}
		}
	}

	public Usuario atualizar(Integer id, UsuarioDTO objetoUsuario) {
		if (usuarioRepository.findById(id).get() != null) {
			throw new EntityNotFoundException("Esse usuario não existe");
		} else {
			Usuario registroAntigo = usuarioRepository.findById(id).get();
			Usuario usuario = parseDeUsuario(objetoUsuario);

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
	}

	public List<Endereco> listarEndereco() {
		return listarEndereco.listar();
	}

	public void recuperarSenha(Integer id, String senha) {
		if (usuarioRepository.findById(id).get() != null) {
			throw new EntityNotFoundException("Esse usuario não existe");
		} else {
			Usuario objTeste = usuarioRepository.findById(id).get();
			if (objTeste != null) {
				objTeste.setPassword(senha);
				usuarioRepository.save(objTeste);
			}
		}
	}

	public void recuperarConta(Integer id) {
		if (usuarioRepository.findById(id).get() != null) {
			throw new EntityNotFoundException("Esse usuario não existe");
		} else {
			Usuario obgUsuario = usuarioRepository.findById(id).get();
			if (obgUsuario != null) {
				obgUsuario.setAtivo(true);
				usuarioRepository.save(obgUsuario);
			}
		}
	}

	public Usuario findByEmail(String email) {
		return usuarioRepository.findByEmail(email).get();
	}
}