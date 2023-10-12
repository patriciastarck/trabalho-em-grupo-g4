package br.com.api.g4.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.api.g4.entities.Pedido;
import br.com.api.g4.repositories.PedidoRepository;

@Service
public class PedidoService {

	@Autowired
	PedidoRepository pedidoRepository;

//	public Integer getCount() {
//		return pedidoRepository.contar();
//	}

	public Pedido salvar(Pedido objetoPedido) {
		return pedidoRepository.save(objetoPedido);
	}

	public Pedido acharId(Integer id) {
		return pedidoRepository.findById(id).get();
	}

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
	
	public Pedido atualizar(Integer id, Pedido objetoPedido) {
		Pedido registroAntigo = acharId(id);

		if (objetoPedido.getAtivo() != null) {
			registroAntigo.setAtivo(objetoPedido.getAtivo());
		}

		if (objetoPedido.getData_pedido() != null) {
			registroAntigo.setData_pedido(objetoPedido.getData_pedido());
		}
		registroAntigo.setId(id);
		return pedidoRepository.save(registroAntigo);
	}

}
