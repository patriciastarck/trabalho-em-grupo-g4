package br.com.api.g4.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.api.g4.dto.PedidoDeProdutoDTO;
import br.com.api.g4.dto.ProdutoDePedidoDTO;
import br.com.api.g4.entities.Pedido;
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

	List<Produto> produtos = new ArrayList<>();

	public Pedido parsePedidoDeProduto(PedidoDeProdutoDTO obj) {
		Pedido pedido = new Pedido();
		List<ProdutoDePedidoDTO> produtosDoPedido = obj.getProdutos();
		Map<Pedido, Integer> itemQuantidade = new HashMap<>();

		for (ProdutoDePedidoDTO itemPedido : produtosDoPedido) {
			Produto produto = produtoRepository.findById(itemPedido.getId()).get();
			itemQuantidade.put(pedido, itemPedido.getQuantidade());
			produto.setItemQuantidade(itemQuantidade);
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
		return pedido;
	}

	// TODO PedidoDTO de retorno com data formatada
	public Pedido acharId(Integer id) {
		return pedidoRepository.findById(id).get();
	}

	// TODO PedidoDTO de retorno com data formatada
	public List<Pedido> listar() {
		return pedidoRepository.findAll();
	}

	public void apagar(Integer id) {
		pedidoRepository.deleteById(id);
	}

	public void apagarLogico(Integer id) {
		Pedido objPedido = acharId(id);
		if (objPedido != null) {
			objPedido.setAtivo(false);
			pedidoRepository.save(objPedido);
		}
	}

}