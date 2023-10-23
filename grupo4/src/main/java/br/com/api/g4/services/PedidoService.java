package br.com.api.g4.services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.api.g4.dto.PedidoDeProdutoDTO;
import br.com.api.g4.dto.ProdutoDePedidoDTO;
import br.com.api.g4.entities.Pedido;
import br.com.api.g4.entities.PedidoProdutoEntry;
import br.com.api.g4.entities.Produto;
import br.com.api.g4.repositories.PedidoRepository;
import br.com.api.g4.repositories.ProdutoRepository;

@Service
public class PedidoService {

	@Autowired
	PedidoRepository pedidoRepository;
	@Autowired
	ProdutoService produtoService;
	@Autowired
	ProdutoRepository produtoRepository;

	List<Produto> produtos;

	public Pedido parsePedidoDeProduto(PedidoDeProdutoDTO obj) {
		produtos = new ArrayList<>();
		Pedido pedido = new Pedido();
		List<ProdutoDePedidoDTO> produtosDoPedido = obj.getProdutos();

		for (ProdutoDePedidoDTO itemPedido : produtosDoPedido) {
			Produto produto = produtoRepository.findById(itemPedido.getId()).get();

			PedidoProdutoEntry pedidoProdutoEntry = new PedidoProdutoEntry();
			pedidoProdutoEntry.setDataHora(LocalDateTime.now());
			pedidoProdutoEntry.setQuantidade(itemPedido.getQuantidadePorProduto());

			produto.getItemQuantidade().put(pedido, pedidoProdutoEntry);

			produtos.add(produto);
		}

		return pedido;
	}

	public Integer getCount() {
		return pedidoRepository.contar();
	}

	public Pedido salvar(PedidoDeProdutoDTO objetoPedido) {
		Pedido pedido = parsePedidoDeProduto(objetoPedido);
		pedido.setAtivo(true);
		pedido.setDataPedido(LocalDate.now());
		pedidoRepository.save(pedido);
		pedido.setProdutos(produtos);

		produtoService.atualizacaoDeEstoque(objetoPedido.getProdutos());

		return pedido;
	}

	// TODO PedidoDTO de retorno com data formatada
	public Pedido acharId(Integer id) {
		if (pedidoRepository.findById(id).get() != null) {
			throw new EntityNotFoundException("Esse pedido não existe");
		} else {
			return pedidoRepository.findById(id).get();
		}
	}

	// TODO PedidoDTO de retorno com data formatada
	public List<Pedido> listar() {
		return pedidoRepository.findAll();
	}

	public void apagarLogico(Integer id) {
		if (pedidoRepository.findById(id).get() != null) {
			throw new EntityNotFoundException("Esse pedido não existe");
		} else {
			Pedido objPedido = acharId(id);
			if (objPedido != null) {
				objPedido.setAtivo(false);
				pedidoRepository.save(objPedido);
			}
		}
	}

	public Pedido atualizar(Integer id, PedidoDeProdutoDTO objetoPedido) {
		if (pedidoRepository.findById(id).get() != null) {
			throw new EntityNotFoundException("Esse pedido não existe");
		} else {
			Pedido registroAntigo = acharId(id);
			Pedido pedido = parsePedidoDeProduto(objetoPedido);
			if (pedido.getAtivo() != null) {
				registroAntigo.setAtivo(pedido.getAtivo());
			}
			if (pedido.getProdutos() != null) {
				registroAntigo.setProdutos(pedido.getProdutos());
			}
			registroAntigo.setId(id);
			return pedidoRepository.save(registroAntigo);
		}
	}
}