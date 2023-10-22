package br.com.api.g4.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.api.g4.dto.ProdutoDTO;
import br.com.api.g4.dto.ProdutoDePedidoDTO;
import br.com.api.g4.dto.PromocaoDTO;
import br.com.api.g4.entities.Produto;
import br.com.api.g4.repositories.ProdutoRepository;

@Service
public class ProdutoService {

	@Autowired
	ProdutoRepository produtoRepository;

	public Produto parseDeProduto(ProdutoDTO objeto) {
		Produto produto = new Produto();

		produto.setNome(objeto.getNome());
		produto.setDescricao(objeto.getDescricao());
		produto.setDataFabricacao(objeto.getDataFabricacao());
		produto.setQntdEstoque(objeto.getQntdEstoque());
		produto.setValorUnitario(objeto.getValorUnitario());

		return produto;
	}

	public void atualizacaoDeEstoque(List<ProdutoDePedidoDTO> produtos) {
		Produto registroAntigo = new Produto();

		for (int i = 0; i < produtos.size(); i++) {
			registroAntigo = acharId(produtos.get(i).getId());
			registroAntigo.setQntdEstoque(registroAntigo.getQntdEstoque() - produtos.get(i).getQuantidadePorProduto());
			
			registroAntigo.setId(produtos.get(i).getId());
			produtoRepository.save(registroAntigo);
		}
	}

	public Integer getCount() {
		return produtoRepository.contar();
	}

	public Produto salvar(ProdutoDTO objetoproduto) {
		Produto produto = parseDeProduto(objetoproduto);
		produto.setAtivo(true);
		return produtoRepository.save(produto);
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

	public Produto atualizar(Integer id, ProdutoDTO objetoproduto) {
		Produto registroAntigo = acharId(id);
		Produto produto = parseDeProduto(objetoproduto);

		if (produto.getAtivo() != null) {
			registroAntigo.setAtivo(produto.getAtivo());
		}
		if (produto.getNome() != null) {
			registroAntigo.setNome(produto.getNome());
		}
		if (produto.getDescricao() != null) {
			registroAntigo.setDescricao(produto.getDescricao());
		}
		if (produto.getDataFabricacao() != null) {
			registroAntigo.setDataFabricacao(produto.getDataFabricacao());
		}
		if (produto.getQntdEstoque() != null) {
			registroAntigo.setQntdEstoque(produto.getQntdEstoque());
		}
		if (produto.getValorUnitario() != null) {
			registroAntigo.setValorUnitario(produto.getValorUnitario());
		}
		registroAntigo.setId(id);
		return produtoRepository.save(registroAntigo);
	}

	public List<PromocaoDTO> promocao() {
		return produtoRepository.promocao().stream()
				.map(record -> new PromocaoDTO(String.valueOf(record[0]), String.valueOf(record[1])))
				.collect(Collectors.toList());
	}
}