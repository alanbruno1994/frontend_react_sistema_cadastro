package com.registrationsystem.workshop.resources;

import java.util.List;
import java.util.stream.Collectors;

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
import com.registrationsystem.workshop.services.exceptions.ObjectNotFound;

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
	
	@GetMapping(value="/cnpj/{cnpj}")
	public ResponseEntity<List<Produto>> findId(@PathVariable  String cnpj)
	{
		List<Produto> produtosReference=produtoService.findAll();
		List<Produto> produtos=produtosReference.stream().filter(x->x.getFornecedor().getCnpj().equals(cnpj)).collect(Collectors.toList());	
		return ResponseEntity.ok().body(produtos);
	}
	
	@PostMapping(value="/{cnpj}")
	public ResponseEntity<Void> isertFornecedor(@RequestBody Produto produto,@PathVariable  String cnpj)
	{
		List<Fornecedor> fornecedores=fornecedorService.findAll();
		for(Fornecedor fornecedor:fornecedores) 
		{
			if(fornecedor.getCnpj().equals(cnpj))
			{
				produto.setFornecedor(fornecedor);
				break;
			}
		}
		if(produto.getFornecedor()==null) 
		{
			throw new ObjectNotFound("Não foi encontrado este fornecedor");
		}		
		produtoService.insertProduto(produto);
		return ResponseEntity.noContent().build();
	}
	

}
