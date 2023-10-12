package br.com.api.g4.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.api.g4.entities.Produto;
import br.com.api.g4.services.ProdutoService;

@RestController 
@RequestMapping("/Produto")

public class ProdutoController {
	@Autowired
	ProdutoService ProdutoService;

	/*
	@GetMapping("/count")
	public Integer getCount() {
		return ProdutoService.getCount();
	}
 */

	@PostMapping("/salvar")
	public Produto salvar(@RequestBody Produto objetoProduto) {
		return ProdutoService.salvar(objetoProduto);
	}

	@GetMapping("/{id}")
	public Produto achar(@PathVariable Integer id) {
		return ProdutoService.achar(id);
	}

	@GetMapping("/lista")
	public List<Produto> todosObjetos() {
		return ProdutoService.todosObjetos();
	}

	@DeleteMapping("/deletar/{id}")
	public void deletar(@PathVariable Integer id) {
		ProdutoService.deletar(id);
	}

	@DeleteMapping("/deletarLogico/{id}")
	public void deletarlogico(@PathVariable Integer id) {
		ProdutoService.deletarlogico(id);
	}

//	@PutMapping("/atualizar/{id}")
//	public Produto atualizar(@PathVariable Integer id, @RequestBody Produto objetoProduto) {
//		return ProdutoService.atualizar(id, objetoProduto);
//	}
}
