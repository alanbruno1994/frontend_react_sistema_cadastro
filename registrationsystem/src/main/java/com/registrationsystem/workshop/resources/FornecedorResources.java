package com.registrationsystem.workshop.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.registrationsystem.workshop.entities.Fornecedor;
import com.registrationsystem.workshop.services.FornecedorService;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping(value="/fornecedores")
public class FornecedorResources {
	
	@Autowired
	private FornecedorService fornecedorService;
	
	@CrossOrigin(origins = "http://localhost:8080/fornecedores")
	@GetMapping
	public ResponseEntity<List<Fornecedor>> findAll()
	{
		List<Fornecedor> list=fornecedorService.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value="/{Id}")
	public ResponseEntity<Fornecedor> findId(@PathVariable  Integer Id)
	{
	    Fornecedor fornecedor=fornecedorService.findById(Id);
		return ResponseEntity.ok().body(fornecedor);
	}
	
	@PostMapping
	public ResponseEntity<Void> isertFornecedor(@RequestBody Fornecedor fornecedor)
	{
		fornecedorService.insertFornecedor(fornecedor);
		return ResponseEntity.noContent().build();
	}

}