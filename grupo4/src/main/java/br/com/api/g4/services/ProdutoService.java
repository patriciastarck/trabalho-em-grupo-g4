package br.com.api.g4.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.api.g4.dto.ProdutoDTO;
import br.com.api.g4.dto.ProdutoRespostaDTO;
import br.com.api.g4.dto.PromocaoDTO;
import br.com.api.g4.entities.Categoria;
import br.com.api.g4.entities.Produto;
import br.com.api.g4.repositories.CategoriaRepository;
import br.com.api.g4.repositories.ProdutoRepository;

@Service
public class ProdutoService {

	@Autowired
	ProdutoRepository produtoRepository;
	@Autowired
	CategoriaRepository categoriaRepository;
	

	public Produto parseDeProduto(ProdutoDTO objeto) {
		Produto produto = new Produto();

		produto.setNome(objeto.getNome());
		produto.setDescricao(objeto.getDescricao());
		produto.setDataFabricacao(objeto.getDataFabricacao());
		produto.setQntdEstoque(objeto.getQntdEstoque());
		produto.setValorUnitario(objeto.getValorUnitario());
		produto.setDataFabricacao(objeto.getDataFabricacao());
		
		return produto;
	}
	public ProdutoRespostaDTO parseDeProdutoResposta(Produto objeto) {
		ProdutoRespostaDTO produto = new ProdutoRespostaDTO();

		produto.setNome(objeto.getNome());
		produto.setDescricao(objeto.getDescricao());
		produto.setDataFabricacao(objeto.getDataFabricacao());
		produto.setQntdEstoque(objeto.getQntdEstoque());
		produto.setValorUnitario(objeto.getValorUnitario());
		produto.setDataFabricacao(objeto.getDataFabricacao());
		
		return produto;
	}

//	public void atualizacaoDeEstoque(List<ProdutoDePedidoDTO> produtos) {
//		List<Produto> registroAntigos = new ArrayList<>();
//		Produto registroAntigo = new Produto();
//
//		for (int i = 0; i < produtos.size(); i++) {
//
//			registroAntigo = acharId(produtos.get(i).getId());
//
//			registroAntigo.setQntdEstoque(registroAntigo.getQntdEstoque() - produtos.get(i).getQuantidadePorProduto());
//
//			registroAntigo.setId(produtos.get(i).getId());
//			registroAntigos.add(registroAntigo);
//		}
//		produtoRepository.saveAll(registroAntigos);
//	}

	public Integer getCount() {
		return produtoRepository.contar();
	}

	public void salvar(ProdutoDTO objetoproduto, String nome)
	{
		Optional<Categoria>categoria = categoriaRepository.findByNome(nome);
		Produto produto = parseDeProduto(objetoproduto);
		
		produto.setAtivo(true);
		categoriaRepository.save(categoria.get());
		produtoRepository.save(produto);
	}

	public ProdutoRespostaDTO acharId(Integer id) {
		if (produtoRepository.findById(id).get() != null) {
			
			throw new EntityNotFoundException("Esse produto não existe");
		} else {
			ProdutoRespostaDTO produtoResposta = parseDeProdutoResposta(produtoRepository.findById(id).get());
			return produtoResposta;
		}
	}

	public List<ProdutoRespostaDTO> listar() {
		List<ProdutoRespostaDTO> produtosResposta = new ArrayList<>();
		List<Produto> produtos = produtoRepository.findAll();
		for (Produto produto : produtos) {
			produtosResposta.add(parseDeProdutoResposta(produto));
		}
		return produtosResposta;
	}

	public void deletar(Integer id) {
		if (produtoRepository.findById(id).get() != null) {
			throw new EntityNotFoundException("Esse produto não existe");
		} else {
			produtoRepository.deleteById(id);
		}
	}

	public void deletarlogico(Integer id) {
		if (produtoRepository.findById(id).get() != null) {
			throw new EntityNotFoundException("Esse produto não existe");
		} else {
			Optional<Produto> objProdutos = produtoRepository.findById(id);
			if (objProdutos != null) {
				objProdutos.get().setAtivo(false);
				produtoRepository.save(objProdutos.get());
			}
		}
	}

	public Produto atualizar(Integer id, ProdutoDTO objetoproduto) {
		if (produtoRepository.findById(id).get() != null) {
			throw new EntityNotFoundException("Esse produto não existe");
		} else {
			Optional<Produto> registroAntigo = produtoRepository.findById(id);
			Produto produto = parseDeProduto(objetoproduto);

			if (produto.getAtivo() != null) {
				registroAntigo.get().setAtivo(produto.getAtivo());
			}
			if (produto.getNome() != null) {
				registroAntigo.get().setNome(produto.getNome());
			}
			if (produto.getDescricao() != null) {
				registroAntigo.get().setDescricao(produto.getDescricao());
			}
			if (produto.getDataFabricacao() != null) {
				registroAntigo.get().setDataFabricacao(produto.getDataFabricacao());
			}
			if (produto.getQntdEstoque() != null) {
				registroAntigo.get().setQntdEstoque(produto.getQntdEstoque());
			}
			if (produto.getValorUnitario() != null) {
				registroAntigo.get().setValorUnitario(produto.getValorUnitario());
			}
			registroAntigo.get().setId(id);
			return produtoRepository.save(registroAntigo.get());
		}
	}

	public List<PromocaoDTO> promocao() {
		return produtoRepository.promocao().stream()
				.map(record -> new PromocaoDTO(String.valueOf(record[0]), String.valueOf(record[1])))
				.collect(Collectors.toList());
	}

	public void reativacaoDeProduto(Integer id) {
		if (produtoRepository.findById(id).get() != null) {
			throw new EntityNotFoundException("Esse produto não existe");
		} else {
			Optional<Produto> objTeste = produtoRepository.findById(id);
			if (objTeste != null) {
				objTeste.get().setAtivo(true);
				produtoRepository.save(objTeste.get());
			}
		}
	}
}