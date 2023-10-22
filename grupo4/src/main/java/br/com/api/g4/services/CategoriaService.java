package br.com.api.g4.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.api.g4.dto.CategoriaDTO;
import br.com.api.g4.entities.Categoria;
import br.com.api.g4.entities.Produto;
import br.com.api.g4.repositories.CategoriaRepository;

@Service
public class CategoriaService {

	@Autowired
	CategoriaRepository categoriaRepository;

	public Categoria parseDeCategoria(CategoriaDTO objeto) {
		Categoria categoria = new Categoria();
		
		categoria.setDescricao(objeto.getDescricao());
		categoria.setNome(objeto.getNome());
		
		return categoria;
	}
	
	public Integer getCount() {
		return categoriaRepository.contar();
	}

	public Categoria salvar(CategoriaDTO objetoTeste) {
		Categoria categoria = parseDeCategoria(objetoTeste);
		categoria.setAtivo(true);
		return categoriaRepository.save(categoria);
	}

	public Categoria acharId(Integer id) {
		return categoriaRepository.findById(id).get();
	}

	public List<Categoria> listar() {
		return categoriaRepository.findAll();
	}

	public void deletarlogico(Integer id) {
		Categoria objTeste = acharId(id);
		if (objTeste != null) {
			objTeste.setAtivo(false);
			categoriaRepository.save(objTeste);
		}
	}

	public Categoria atualizar(Integer id, CategoriaDTO objetoTeste) {
		Categoria registroAntigo = acharId(id);
		Categoria objeto = parseDeCategoria(objetoTeste);
		
		if (objeto.getAtivo() != null) {
			registroAntigo.setAtivo(objeto.getAtivo());
		}

		if (objeto.getNome() != null) {
			registroAntigo.setNome(objeto.getNome());
		}
		if (objeto.getDescricao() != null) {
			registroAntigo.setDescricao(objeto.getDescricao());
		}
		registroAntigo.setId(id);
		return categoriaRepository.save(registroAntigo);
	}
	public void reativacaoDeCategoria(Integer id) {
		Categoria objTeste = acharId(id);
		if (objTeste != null) {
			objTeste.setAtivo(true);
			categoriaRepository.save(objTeste);
		}
	}
}