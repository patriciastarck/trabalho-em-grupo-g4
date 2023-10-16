package br.com.api.g4.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

	public Integer getCount() {
		return enderecoRepository.contar();
	}
	
	public Endereco salvar(EnderecoDTO endereco) {
		Endereco viaCep=pesquisarEndereco(endereco.getCep());
		Endereco enderecoNovo=new Endereco();
		enderecoNovo.setBairro(viaCep.getBairro());
		enderecoNovo.setCep(endereco.getCep());
		enderecoNovo.setComplemento(endereco.getComplemento());
		enderecoNovo.setLocalidade(viaCep.getLocalidade());
		enderecoNovo.setLogradouro(viaCep.getLogradouro());
		enderecoNovo.setUf(viaCep.getUf());
		return enderecoRepository.save(enderecoNovo);
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
	public Endereco pesquisarEndereco(String cep) {
		RestTemplate restTemplate = new RestTemplate();
		String uri = "http://viacep.com.br/ws/{cep}/json/";
		Map<String, String> params = new HashMap<>();
		params.put("cep", cep);
		return restTemplate.getForObject(uri, Endereco.class, params);
	}
}