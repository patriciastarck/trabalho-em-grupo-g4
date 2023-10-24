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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.api.g4.dto.ProdutoDTO;
import br.com.api.g4.dto.ProdutoRespostaDTO;
import br.com.api.g4.dto.PromocaoDTO;
import br.com.api.g4.entities.Produto;
import br.com.api.g4.services.EmailService;
import br.com.api.g4.services.ProdutoService;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

	private EmailService emailService;

    @Autowired
    public void setEmailService(EmailService emailService) {
        this.emailService = emailService;
    }

	@Autowired
	ProdutoService produtoService;

	@GetMapping("/count")
	public Integer getCount() {
		return produtoService.getCount();
	}

	@PostMapping("/salvar")
	public void salvar(@RequestBody ProdutoDTO objetoProduto, @RequestParam String nomeDaCategoria,@RequestParam String email) {
		produtoService.salvar(objetoProduto,nomeDaCategoria,email);
	}

	@GetMapping("/{id}")
	public ProdutoRespostaDTO acharId(@PathVariable Integer id) {
		return produtoService.acharId(id);
	}

	@GetMapping("/listar")
	public List<ProdutoRespostaDTO> listar() {
		return produtoService.listar();
	}

	@DeleteMapping("/deletar/{id}")
	public void deletar(@PathVariable Integer id) {
		produtoService.deletar(id);
	}

	@PutMapping("/atualizar/{id}")
	public Produto atualizar(@PathVariable Integer id, @RequestBody ProdutoDTO objetoProduto) {
		return produtoService.atualizar(id, objetoProduto);
	}

	@GetMapping("/promocao")
	public List<PromocaoDTO> promocao() {
		emailService.envioEmailPromo();
		return produtoService.promocao();
	}
	
}