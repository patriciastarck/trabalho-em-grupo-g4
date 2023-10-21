package br.com.api.g4.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.api.g4.dto.PedidoDTO;
import br.com.api.g4.entities.Pedido;
import br.com.api.g4.repositories.PedidoRepository;

@Service
public class PedidoService {

	@Autowired
	PedidoRepository pedidoRepository;

	public Pedido parseDePedido(PedidoDTO objeto) {
		Pedido pedido = new Pedido();
		
		pedido.setProdutos(objeto.getProdutos());
		
		return pedido;
	}
	
	public Integer getCount() {
		return pedidoRepository.contar();
	}

	public Pedido salvar(PedidoDTO objetoPedido) {
		Pedido pedido = parseDePedido(objetoPedido);
		pedido.setAtivo(true);
		pedido.setDataPedido(LocalDate.now());
		return pedidoRepository.save(pedido);
	}
	//TODO PedidoDTO de retorno com data formatada
	public Pedido acharId(Integer id) {
		return pedidoRepository.findById(id).get();
	}
	//TODO PedidoDTO de retorno com data formatada
	public List<Pedido> listar() {
		return pedidoRepository.findAll();
	}

	public void apagar(Integer id) {
		pedidoRepository.deleteById(id);
	}
	
	public void apagarLogico(Integer id) {
//		Pedido objPedido = pedidoRepository.findById(id).get();
		Pedido objPedido = acharId(id);
		if(objPedido != null) {
			objPedido.setAtivo(false);
			pedidoRepository.save(objPedido);
		}		
	}
	
	public Pedido atualizar(Integer id, PedidoDTO objetoPedido) {
		Pedido registroAntigo = acharId(id);
		Pedido pedido = parseDePedido(objetoPedido);
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