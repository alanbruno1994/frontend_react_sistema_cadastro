package com.registrationsystem.workshop.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.registrationsystem.workshop.entities.Fornecedor;
import com.registrationsystem.workshop.entities.Produto;
import com.registrationsystem.workshop.services.ProdutoService;

@RestController
@RequestMapping(value="/produtos")
public class ProdutoResources {

	@Autowired
	private ProdutoService produtoService;
	
	@GetMapping
	public ResponseEntity<List<Produto>> findAll()
	{
		List<Produto> list=produtoService.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value="/{id}")
	public ResponseEntity<Produto> findId(@PathVariable  Integer Id)
	{
		Produto produto=produtoService.findById(Id);
		return ResponseEntity.ok().body(produto);
	}
	
}
