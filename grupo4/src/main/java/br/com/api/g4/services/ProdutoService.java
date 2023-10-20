package br.com.api.g4.services;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.api.g4.entities.Produto;
import br.com.api.g4.repositories.ProdutoRepository;

@Service
public class ProdutoService {

	@Autowired
	ProdutoRepository produtoRepository;

	public Integer getCount() {
		return produtoRepository.contar();
	}

	public Produto salvar(Produto objetoproduto) {
		return produtoRepository.save(objetoproduto);
	}

	public Produto acharId(Integer id) {
		return produtoRepository.findById(id).get();
	}

	public List<Produto> listar() {
		return produtoRepository.findAll();
	}

	public void deletar(Integer id) {
		produtoRepository.deleteById(id);
	}

	public void deletarlogico(Integer id) {
		Produto objProduto = acharId(id);
		if (objProduto != null) {
			objProduto.setAtivo(false);
			produtoRepository.save(objProduto);
		}
	}

	public Produto atualizar(Integer id, Produto objetoproduto) {
		Produto registroAntigo = acharId(id);

		if (objetoproduto.getAtivo()!= null) {
			registroAntigo.setAtivo(objetoproduto.getAtivo());
		}
		if (objetoproduto.getNome() != null) {
			registroAntigo.setNome(objetoproduto.getNome());
		}
		if (objetoproduto.getDescricao() != null) {
			registroAntigo.setDescricao(objetoproduto.getDescricao());
		}
		if (objetoproduto.getDataFabricacao() != null) {
			registroAntigo.setDataFabricacao(objetoproduto.getDataFabricacao());
		}
		if (objetoproduto.getQntdEstoque() != null) {
			registroAntigo.setQntdEstoque(objetoproduto.getQntdEstoque());
		}
		if (objetoproduto.getValorUnitario() != null) {
			registroAntigo.setValorUnitario(objetoproduto.getValorUnitario());
		}
		registroAntigo.setId(id);
		return produtoRepository.save(registroAntigo);
	}

	public Map<String,Double> promocao() {
		return produtoRepository.promocao();
	}
}