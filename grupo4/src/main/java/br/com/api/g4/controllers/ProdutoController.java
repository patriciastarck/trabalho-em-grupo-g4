package br.com.api.g4.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.api.g4.dto.ProdutoDTO;
import br.com.api.g4.dto.PromocaoDTO;
import br.com.api.g4.entities.Produto;
import br.com.api.g4.services.EmailService;
import br.com.api.g4.services.ProdutoService;

@RestController 
@RequestMapping("/produto")
public class ProdutoController {
	
	@Autowired
	EmailService emailService;
	
	@Autowired
	ProdutoService produtoService;

	@GetMapping("/count")
	public Integer getCount() {
		return produtoService.getCount();
	}
 

	@PostMapping("/salvar")
	public Produto salvar(@RequestBody ProdutoDTO objetoProduto) {
		return produtoService.salvar(objetoProduto);
	}

	@GetMapping("/{id}")
	public Produto acharId(@PathVariable Integer id) {
		return produtoService.acharId(id);
	}

	@GetMapping("/listar")
	public List<Produto> listar() {
		return produtoService.listar();
	}

	@DeleteMapping("/deletar/{id}")
	public void deletar(@PathVariable Integer id) {
		produtoService.deletar(id);
	}

	@DeleteMapping("/deletarLogico/{id}")
	public void deletarlogico(@PathVariable Integer id) {
		produtoService.deletarlogico(id);
	}

	@PutMapping("/atualizar/{id}")
	public Produto atualizar(@PathVariable Integer id, @RequestBody ProdutoDTO objetoProduto) {
		return produtoService.atualizar(id, objetoProduto);
	}
	
	@GetMapping("/promocao")
	public List<PromocaoDTO> promocao() {
		return produtoService.promocao();
	}
}