package com.registrationsystem.workshop.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.registrationsystem.workshop.entities.Fornecedor;
import com.registrationsystem.workshop.services.FornecedorService;
import com.registrationsystem.workshop.services.JwtToken;


@RestController
@RequestMapping(value="/fornecedores")
public class FornecedorResources {
	
	@Autowired
	private JwtToken jwtToken;
	
	@Autowired
	private FornecedorService fornecedorService;
	

	@GetMapping
	public ResponseEntity<List<Fornecedor>> findAll(@RequestHeader String Authorization)
	{
		jwtToken.authorize(Authorization);
		List<Fornecedor> list=fornecedorService.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value="/{Id}")
	public ResponseEntity<Fornecedor> findId(@PathVariable  Integer Id,@RequestHeader String Authorization)
	{
		jwtToken.authorize(Authorization);
	    Fornecedor fornecedor=fornecedorService.findById(Id);
		return ResponseEntity.ok().body(fornecedor);
	}
	
	@PostMapping
	public ResponseEntity<Void> isertFornecedor(@RequestBody Fornecedor fornecedor,@RequestHeader String Authorization)
	{
		jwtToken.authorize(Authorization);
		fornecedorService.insertFornecedor(fornecedor);
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping(value="/{Id}")
	public ResponseEntity<Void> deleteById(@PathVariable  Integer Id,@RequestHeader String Authorization)
	{	
		jwtToken.authorize(Authorization);
		fornecedorService.removeById(Id);
		return ResponseEntity.noContent().build();
	}

}
