package br.com.api.g4.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.api.g4.entities.Produto;
import br.com.api.g4.repositories.ProdutoRepository;

@Service
public class ProdutoService {

	@Autowired
	ProdutoRepository ProdutoRepository;

	public Integer getCount() {
		return ProdutoRepository.contar();
	}

	public Produto salvar(Produto objetoProduto) {
		return ProdutoRepository.save(objetoProduto);
	}

	public Produto achar(Integer id) {
		return ProdutoRepository.findById(id).get();
	}

	public List<Produto> todosObjetos() {
		return ProdutoRepository.findAll();
	}

	public void deletar(Integer id) {
		ProdutoRepository.deleteById(id);
	}

	public void deletarlogico(Integer id) {
		Produto objProduto = achar(id);
		if (objProduto != null) {
			objProduto.setAtivo(false);
			ProdutoRepository.save(objProduto);
		}
	}

//	public Produto atualizar(Integer id, Produto objetoProduto) {
//		Produto registroAntigo = achar(id);
//
//		if (objetoProduto.getAtivo() != null) {
//			registroAntigo.setAtivo(objetoProduto.getAtivo());
//		}
//
//		if (objetoProduto.getNome() != null) {
//			registroAntigo.setNome(objetoProduto.getNome());
//		}
//		if (objetoProduto.getDescricao() != null) {
//			registroAntigo.setDescricao(objetoProduto.getDescricao());
//		}
//		if (objetoProduto.getData_fabricacao() != null) {
//			registroAntigo.setData_fabricacao(objetoProduto.getData_fabricacao());
//		}
//		if (objetoProduto.getQntd_estoque() != null) {
//			registroAntigo.setQntd_estoque(objetoProduto.getQntd_estoque());
//		}
//		if (objetoProduto.getValor_unitario() != null) {
//			registroAntigo.setValor_unitario(objetoProduto.getValor_unitario());
//		}
//		registroAntigo.setId(id);
//		return ProdutoRepository.save(registroAntigo);
//		
// 
//	}
}