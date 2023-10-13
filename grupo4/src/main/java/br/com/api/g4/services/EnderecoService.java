package br.com.api.g4.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.api.g4.entities.Endereco;
import br.com.api.g4.repositories.EnderecoRepository;

@Service
public class EnderecoService {

	@Autowired
	EnderecoRepository enderecoRepository;

	public Integer getCount() {
		return enderecoRepository.contar();
	}
	
	public List<Endereco> salvar(List <Endereco> objetoTeste) {
		return enderecoRepository.saveAll(objetoTeste);
	}

	public Endereco acharId(Integer id) {
		return enderecoRepository.findById(id).get();
	}

	public List<Endereco> listar() {
		return enderecoRepository.findAll();
	}

	public Endereco atualizar(Integer id, Endereco objetoTeste) {
		Endereco registroAntigo = acharId(id);

		if (objetoTeste.getCep() != null) {
			registroAntigo.setCep(objetoTeste.getCep());
		}

		if (objetoTeste.getLogradouro() != null) {
			registroAntigo.setLogradouro(objetoTeste.getLogradouro());
		}
		if (objetoTeste.getComplemento() != null) {
			registroAntigo.setComplemento(objetoTeste.getComplemento());
		}
		if (objetoTeste.getBairro() != null) {
			registroAntigo.setBairro(objetoTeste.getBairro());
		}
	
		if (objetoTeste.getLocalidade() != null) {
		registroAntigo.setLocalidade(objetoTeste.getLocalidade());
		}
		
	
		if (objetoTeste.getUf() != null) {
			registroAntigo.setUf(objetoTeste.getUf());
		}
		
		registroAntigo.setId(id);
		return enderecoRepository.save(registroAntigo);
	
	}
}