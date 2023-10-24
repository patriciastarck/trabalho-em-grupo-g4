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

	public Integer getCount() {
		return usuarioRepository.contar();
	}

	public Usuario salvar(UsuarioDTO objetoUsuario) {

		return usuarioRepository.save(parseDeUsuario(objetoUsuario));
	}

	public Usuario acharId(Integer id) {
		if (usuarioRepository.findById(id).get() != null) {
			throw new EntityNotFoundException("Esse usuario não existe");
		} else {
			return usuarioRepository.findById(id).get();
		}
	}

	public List<UsuarioRespostaDTO> listar() {
		List<UsuarioRespostaDTO> infoUsuario = new ArrayList<>();
		List<Usuario>usuarios = usuarioRepository.findAll();
		List<Endereco>enderecos = enderecoRepository.findAll();
		
		for(Usuario usuario : usuarios) {
			infoUsuario.add(converteUsuarioDTO(usuario));
		}
		for(Endereco endereco : enderecos) {
			
			infoUsuario.add(converteUsuarioDTO2(endereco));
		}
		return infoUsuario;
	}
	
	public UsuarioRespostaDTO converteUsuarioDTO(Usuario usuario) {
		UsuarioRespostaDTO usuarioConvertido = new UsuarioRespostaDTO();
		usuarioConvertido.setNome(usuario.getNome());
		usuarioConvertido.setNomeUsuario(usuario.getNomeUsuario());
		usuarioConvertido.setEmail(usuario.getEmail());
		
		return usuarioConvertido;
	}
	
	public UsuarioRespostaDTO converteUsuarioDTO2(Endereco endereco) {
		UsuarioRespostaDTO usuarioConvertido = new UsuarioRespostaDTO();
		usuarioConvertido.setCep(endereco.getCep());
		usuarioConvertido.setLocalidade(endereco.getLocalidade());
		usuarioConvertido.setBairro(endereco.getBairro());
		usuarioConvertido.setLogradouro(endereco.getLogradouro());
		usuarioConvertido.setComplemento(endereco.getComplemento());
		usuarioConvertido.setNumero(endereco.getNumero());
		
		return usuarioConvertido;
	}
	

	public void deletarLogico(Integer id) {
		if (usuarioRepository.findById(id).get() != null) {
			throw new EntityNotFoundException("Esse usuario não existe");
		} else {
			Usuario obgUsuario = acharId(id);
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
			Usuario registroAntigo = acharId(id);
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
			Usuario objTeste = acharId(id);
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
			Usuario obgUsuario = acharId(id);
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