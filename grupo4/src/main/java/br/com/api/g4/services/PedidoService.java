package br.com.api.g4.services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.api.g4.dto.PedidoDeProdutoDTO;
import br.com.api.g4.dto.PedidoRespostaDTO;
import br.com.api.g4.dto.ProdutoDePedidoDTO;
import br.com.api.g4.entities.Pedido;
import br.com.api.g4.entities.PedidoProdutoEntry;
import br.com.api.g4.entities.Produto;
import br.com.api.g4.entities.Usuario;
import br.com.api.g4.repositories.PedidoRepository;
import br.com.api.g4.repositories.ProdutoRepository;
import br.com.api.g4.repositories.UsuarioRepository;

@Service
public class PedidoService {

	@Autowired
	PedidoRepository pedidoRepository;
	@Autowired
	ProdutoService produtoService;
	@Autowired
	ProdutoRepository produtoRepository;
	@Autowired
	UsuarioRepository usuarioRepository;

	List<Produto> produtos;

	public Pedido parsePedidoDeProduto(PedidoDeProdutoDTO obj) {
		Pedido pedido = new Pedido();
		List<Integer> quantidade = new ArrayList<>();
		List<Produto> prod = new ArrayList<>();

		for (int i = 0; i < obj.getProdutos().size(); i++) {
			quantidade.add(obj.getProdutos().get(i).getQuantidadePorProduto());
			prod.add(produtoRepository.getReferenceById(obj.getProdutos().get(i).getId()));
		}

		pedido.setProdutos(prod);

		return pedido;
	}

	public Pedido atualizacaoDeEstoque(PedidoDeProdutoDTO obj) {
		produtos = new ArrayList<>();
		Pedido pedido = new Pedido();
		List<ProdutoDePedidoDTO> produtosDoPedido = obj.getProdutos();

		for (ProdutoDePedidoDTO itemPedido : produtosDoPedido) {
			Produto produto = produtoRepository.findById(itemPedido.getId()).get();
			PedidoProdutoEntry pedidoProdutoEntry = new PedidoProdutoEntry();

			pedidoProdutoEntry.setDataHora(LocalDateTime.now());

			if (itemPedido.getQuantidadePorProduto() <= produto.getQntdEstoque()) {
				Integer quantidadeAtualizada = produto.getQntdEstoque() - itemPedido.getQuantidadePorProduto();
				pedidoProdutoEntry.setQuantidade(itemPedido.getQuantidadePorProduto());
				produto.getItemQuantidade().put(pedido, pedidoProdutoEntry);
				produto.setQntdEstoque(quantidadeAtualizada);
				produtos.add(produto);
			}
		}
		return pedido;
	}

	public PedidoRespostaDTO parseDePedidoResposta(Pedido pedido) {
		PedidoRespostaDTO pedidoResposta = new PedidoRespostaDTO();

		pedidoResposta.setAtivo(pedido.getAtivo());
		pedidoResposta.setDataPedido(pedido.getDataPedido());
		pedidoResposta.setProdutos(pedido.getProdutos());

		return pedidoResposta;
	}

	public Integer getCount() {
		return pedidoRepository.contar();
	}

	public Pedido salvar(PedidoDeProdutoDTO objetoPedido, String email) {
		Pedido pedido = atualizacaoDeEstoque(objetoPedido);

		pedido.setAtivo(true);
		pedido.setDataPedido(LocalDate.now());
//		pedido.setProdutos(produtos);
		System.out.println(pedido);
		pedidoRepository.save(pedido);

		Usuario usuario = usuarioRepository.findByEmail(email).get();
		List<Pedido> pedidos = usuario.getPedidos();
		if (pedidos == null) {
			usuario.setPedidos(List.of(pedido));
		} else {
			pedidos.add(pedido);
			usuario.setPedidos(pedidos);
		}
		usuarioRepository.save(usuario);

		return pedido;
	}
//		for (int i = 0; i < usuario.getPedidos().size(); i++) {
//		pedidos.add(pedido);
//		}
//		usuario.setPedidos(pedidos);
//		usuarioRepository.save(usuario);

	public PedidoRespostaDTO acharId(Integer id) {
		PedidoRespostaDTO pedidoResposta = parseDePedidoResposta(pedidoRepository.findById(id).get());
		return pedidoResposta;
	}

	public List<PedidoRespostaDTO> listar() {
		List<PedidoRespostaDTO> pedidosResposta = new ArrayList<>();
		List<Pedido> pedidos = pedidoRepository.findAll();

		for (Pedido pedido : pedidos) {
			pedidosResposta.add(parseDePedidoResposta(pedido));
		}
		return pedidosResposta;

	}

	public void apagarLogico(Integer id) {
		if (pedidoRepository.findById(id).get() != null) {
			throw new EntityNotFoundException("Esse pedido não existe");
		} else {
			Optional<Pedido> objPedido = pedidoRepository.findById(id);
			if (objPedido != null) {

				objPedido.get().setAtivo(false);
				pedidoRepository.save(objPedido.get());
			}
		}
	}

}