package br.com.api.g4.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.api.g4.entities.Categoria;
import br.com.api.g4.repositories.CategoriaRepository;

@Service
public class CategoriaService {

	@Autowired
	CategoriaRepository categoriaRepository;

	// TODO contar
	
	public Categoria salvar(Categoria objetoTeste) {
		return categoriaRepository.save(objetoTeste);
	}

	public Categoria achar(Integer id) {
		return categoriaRepository.findById(id).get();
	}

	public List<Categoria> todosObjetos() {
		return categoriaRepository.findAll();
	}

	public void deletarlogico(Integer id) {
		Categoria objTeste = achar(id);
		if (objTeste != null) {
			objTeste.setAtivo(false);
			categoriaRepository.save(objTeste);
		}
	}

	public Categoria atualizar(Integer id, Categoria objetoTeste) {
		Categoria registroAntigo = achar(id);

		if (objetoTeste.getAtivo() != null) {
			registroAntigo.setAtivo(objetoTeste.getAtivo());
		}

		if (objetoTeste.getNome() != null) {
			registroAntigo.setNome(objetoTeste.getNome());
		}
		if (objetoTeste.getDescricao() != null) {
			registroAntigo.setDescricao(objetoTeste.getDescricao());
		}
		registroAntigo.setId(id);
		return categoriaRepository.save(registroAntigo);
	}
}
