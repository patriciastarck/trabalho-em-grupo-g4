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

import br.com.api.g4.dto.CategoriaDTO;
import br.com.api.g4.entities.Categoria;
import br.com.api.g4.services.CategoriaService;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {

	@Autowired
	CategoriaService categoriaService;

	@GetMapping("/count")
	public Integer getCount() {
		return categoriaService.getCount();
	}

	@PostMapping("/salvar")
	public Categoria salvar(@RequestBody CategoriaDTO objetoTeste) {
		return categoriaService.salvar(objetoTeste);
	}

	@GetMapping("/{id}")
	public Categoria acharId(@PathVariable Integer id) {
		return categoriaService.acharId(id);
	}

	@GetMapping("/listar")
	public List<Categoria> listar() {
		return categoriaService.listar();
	}

	@DeleteMapping("/deletarLogico/{id}")
	public void deletarlogico(@PathVariable Integer id) {
		categoriaService.deletarlogico(id);
	}

	@PutMapping("/atualizar/{id}")
	public Categoria atualizar(@PathVariable Integer id, @RequestBody CategoriaDTO objetoTeste) {
		return categoriaService.atualizar(id, objetoTeste);
	}

	@PutMapping("/reativacaoDeCategoria/{id}")
	public void reativacaoDeCategoria(@PathVariable Integer id) {
		categoriaService.reativacaoDeCategoria(id);
	}
}