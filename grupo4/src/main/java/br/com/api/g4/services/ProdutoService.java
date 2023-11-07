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
import br.com.api.g4.entities.Usuario;
import br.com.api.g4.repositories.CategoriaRepository;
import br.com.api.g4.repositories.ProdutoRepository;
import br.com.api.g4.repositories.UsuarioRepository;

@Service
public class ProdutoService {

	@Autowired
	ProdutoRepository produtoRepository;
	@Autowired
	CategoriaRepository categoriaRepository;
	@Autowired
	UsuarioRepository usuarioRepository;

	public Produto parseDeProduto(ProdutoDTO objeto) {
		Produto produto = new Produto();

		produto.setNome(objeto.getNome());
		produto.setDescricao(objeto.getDescricao());
		produto.setDataFabricacao(objeto.getDataFabricacao());
		produto.setQntdEstoque(objeto.getQntdEstoque());
		produto.setValorUnitario(objeto.getValorUnitario());
		produto.setDataFabricacao(objeto.getDataFabricacao());
		produto.setImagem(objeto.getImagem());
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

	public Integer getCount() {
		return produtoRepository.contar();
	}

	public void salvar(ProdutoDTO objetoproduto) {
		Produto produtoNovo = parseDeProduto(objetoproduto);

		produtoNovo.setAtivo(true);

//		Optional<Categoria> categoria = categoriaRepository.findByNome(nome);
//		categoria.get().getProdutos().add(produtoNovo);
//
//		Optional<Usuario> usuario = usuarioRepository.findByEmail(email);
//		usuario.get().getProdutos().add(produtoNovo);

		produtoRepository.save(produtoNovo);
//		categoriaRepository.save(categoria.get());
//		usuarioRepository.save(usuario.get());
	}

	public ProdutoRespostaDTO acharId(Integer id) {
		if (produtoRepository.findById(id).get() == null) {

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
		if (produtoRepository.findById(id).get() == null) {
			throw new EntityNotFoundException("Esse produto não existe");
		} else {
			produtoRepository.deleteById(id);
		}
	}

	public Produto atualizar(Integer id, ProdutoDTO objetoproduto) {
		if (produtoRepository.findById(id).get() == null) {
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


}