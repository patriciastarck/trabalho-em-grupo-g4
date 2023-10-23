package br.com.api.g4.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.api.g4.dto.EnderecoDTO;
import br.com.api.g4.entities.Endereco;
import br.com.api.g4.repositories.EnderecoRepository;

@Service
public class EnderecoService {

	@Autowired
	EnderecoRepository enderecoRepository;

	public Endereco parseDeEndereco(EnderecoDTO endereco) {
		Endereco viaCep = pesquisarEndereco(endereco.getCep());
		Endereco enderecoNovo = new Endereco();

		enderecoNovo.setCep(endereco.getCep());
		enderecoNovo.setComplemento(endereco.getComplemento());
		enderecoNovo.setNumero(endereco.getNumero());
		enderecoNovo.setBairro(viaCep.getBairro());
		enderecoNovo.setLocalidade(viaCep.getLocalidade());
		enderecoNovo.setLogradouro(viaCep.getLogradouro());
		enderecoNovo.setUf(viaCep.getUf());

		return enderecoNovo;
	}

	public Integer getCount() {
		return enderecoRepository.contar();
	}

	public Endereco salvar(EnderecoDTO endereco) {
		Endereco enderecoNovo = parseDeEndereco(endereco);
		enderecoNovo.setAtivo(true);
		return enderecoRepository.save(enderecoNovo);
	}

	public Endereco acharId(Integer id) {
		if (enderecoRepository.existsById(id)) {
			return enderecoRepository.findById(id).get();
		} else {
			throw new EntityNotFoundException("Esse ID não está cadastrado no banco.");
		}
	}

	public List<Endereco> listar() {
		return enderecoRepository.findAll();
	}

	public void deletarlogico(Integer id) {
		Endereco objTeste = acharId(id);
		if (objTeste != null) {
			objTeste.setAtivo(false);
			enderecoRepository.save(objTeste);
		}
	}

	public Endereco atualizar(Integer id, EnderecoDTO objetoTeste) {
		Endereco registroAntigo = acharId(id);
		Endereco endereco = parseDeEndereco(objetoTeste);

		if (endereco.getCep() != null) {
			registroAntigo.setCep(endereco.getCep());
		}
		if (endereco.getLogradouro() != null) {
			registroAntigo.setLogradouro(endereco.getLogradouro());
		}
		if (endereco.getComplemento() != null) {
			registroAntigo.setComplemento(endereco.getComplemento());
		}
		if (endereco.getBairro() != null) {
			registroAntigo.setBairro(endereco.getBairro());
		}
		if (endereco.getLocalidade() != null) {
			registroAntigo.setLocalidade(endereco.getLocalidade());
		}
		if (endereco.getUf() != null) {
			registroAntigo.setUf(endereco.getUf());
		}
		if (endereco.getAtivo() != null) {
			registroAntigo.setAtivo(endereco.getAtivo());
		}
		if (endereco.getNumero() != null) {
			registroAntigo.setNumero(endereco.getNumero());
		}
		registroAntigo.setId(id);
		return enderecoRepository.save(registroAntigo);
	}

	public Endereco pesquisarEndereco(String cep) {
		RestTemplate restTemplate = new RestTemplate();
		String uri = "http://viacep.com.br/ws/{cep}/json/";
		Map<String, String> params = new HashMap<>();
		params.put("cep", cep);
		return restTemplate.getForObject(uri, Endereco.class, params);
	}

	public void reativacaoDeEndereco(Integer id) {
		Endereco objTeste = acharId(id);
		if (objTeste != null) {
			objTeste.setAtivo(true);
			enderecoRepository.save(objTeste);
		}
	}
}