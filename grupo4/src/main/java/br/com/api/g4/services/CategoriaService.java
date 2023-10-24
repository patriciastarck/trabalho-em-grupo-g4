package br.com.api.g4.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.api.g4.dto.CategoriaDTO;
import br.com.api.g4.dto.CategoriaRespostaDTO;
import br.com.api.g4.entities.Categoria;
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

	public CategoriaRespostaDTO parseDeCategoriaRespostaDTO(Categoria categoria) {
		CategoriaRespostaDTO categoriaResposta = new CategoriaRespostaDTO();

		categoriaResposta.setAtivo(categoria.getAtivo());
		categoriaResposta.setNome(categoria.getNome());
		categoriaResposta.setDescricao(categoria.getDescricao());

		return categoriaResposta;
	}

	public Integer getCount() {
		return categoriaRepository.contar();
	}

	public Categoria salvar(CategoriaDTO objetoTeste) {
		Categoria categoria = parseDeCategoria(objetoTeste);
		categoria.setAtivo(true);
		return categoriaRepository.save(categoria);
	}

	public CategoriaRespostaDTO acharId(Integer id) {
		CategoriaRespostaDTO categoriaResposta = parseDeCategoriaRespostaDTO(categoriaRepository.findById(id).get());

		return categoriaResposta;
	}

	public List<CategoriaRespostaDTO> listar() {
		List<CategoriaRespostaDTO> categoriasResposta = new ArrayList<>();
		List<Categoria> categorias = categoriaRepository.findAll();

		for (Categoria categoria : categorias) {
			categoriasResposta.add(parseDeCategoriaRespostaDTO(categoria));
		}

		return categoriasResposta;
	}

	public void deletarlogico(Integer id) {
		if (categoriaRepository.findById(id).get() != null) {
			throw new EntityNotFoundException("Esse categoria não existe");
		} else {
			Optional<Categoria> objTeste = categoriaRepository.findById(id);
			if (objTeste != null) {
				objTeste.get().setAtivo(false);
				categoriaRepository.save(objTeste.get());
			}
		}
	}

	public Categoria atualizar(Integer id, CategoriaDTO objetoTeste) {
		if (categoriaRepository.findById(id).get() != null) {
			throw new EntityNotFoundException("Esse categoria não existe");
		} else {
			Optional<Categoria> registroAntigo = categoriaRepository.findById(id);
			Categoria objeto = parseDeCategoria(objetoTeste);

			if (objeto.getAtivo() != null) {
				registroAntigo.get().setAtivo(objeto.getAtivo());
			}

			if (objeto.getNome() != null) {
				registroAntigo.get().setNome(objeto.getNome());
			}
			if (objeto.getDescricao() != null) {
				registroAntigo.get().setDescricao(objeto.getDescricao());
			}
			registroAntigo.get().setId(id);
			return categoriaRepository.save(registroAntigo.get());
		}
	}

	public void reativacaoDeCategoria(Integer id) {
		if (categoriaRepository.findById(id).get() != null) {
			throw new EntityNotFoundException("Esse categoria não existe");
		} else {
			Optional<Categoria> objTeste = categoriaRepository.findById(id);
			if (objTeste != null) {
				objTeste.get().setAtivo(true);
				categoriaRepository.save(objTeste.get());
			}
		}
	}
}