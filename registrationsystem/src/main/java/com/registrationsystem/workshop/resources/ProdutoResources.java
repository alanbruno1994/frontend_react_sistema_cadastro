package com.registrationsystem.workshop.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.registrationsystem.workshop.entities.Fornecedor;
import com.registrationsystem.workshop.entities.Produto;
import com.registrationsystem.workshop.services.FornecedorService;
import com.registrationsystem.workshop.services.ProdutoService;

@RestController
@RequestMapping(value="/produtos")
public class ProdutoResources {

	@Autowired
	private ProdutoService produtoService;
	
	@Autowired
	private FornecedorService fornecedorService;
	
	@GetMapping
	public ResponseEntity<List<Produto>> findAll()
	{
		List<Produto> list=produtoService.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value="/{Id}")
	public ResponseEntity<Produto> findId(@PathVariable  Integer Id)
	{
	
		Produto produto=produtoService.findById(Id);
		return ResponseEntity.ok().body(produto);
	}
	
	@PostMapping(value="/{Id}")
	public ResponseEntity<Void> isertFornecedor(@RequestBody Produto produto,@PathVariable  Integer Id)
	{
		Fornecedor fornecedor=fornecedorService.findById(Id);
		produto.setFornecedor(fornecedor);
		produtoService.insertProduto(produto);
		return ResponseEntity.noContent().build();
	}
	

}
