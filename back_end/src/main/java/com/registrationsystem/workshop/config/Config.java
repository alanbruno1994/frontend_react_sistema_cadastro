package com.registrationsystem.workshop.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.registrationsystem.workshop.entities.Fornecedor;
import com.registrationsystem.workshop.entities.Produto;
import com.registrationsystem.workshop.entities.UserSystem;
import com.registrationsystem.workshop.services.FornecedorService;
import com.registrationsystem.workshop.services.ProdutoService;
import com.registrationsystem.workshop.services.UserService;

@Configuration 
public class Config implements CommandLineRunner{
	@Autowired
	private ProdutoService produtoService;
	@Autowired
	private FornecedorService fornecedorService;
	@Autowired
	private UserService userService;
	
	@Override
	public void run(String... args) throws Exception {
		try {
			userService.insertUser(new UserSystem(null,"root","123456"));
			
		}catch(Exception e) 
		{
			
		}
		
		try {
		Fornecedor fornecedor1=new Fornecedor(null,"Silva","000132201","Bahia");
		Fornecedor fornecedor2=new Fornecedor(null,"Simas","000132202","Rio de Janeiro");
		Fornecedor fornecedor3=new Fornecedor(null,"Jorge","000132203","Santacarina");
		fornecedorService.insertFornecedor(fornecedor1);
		fornecedorService.insertFornecedor(fornecedor2);
		fornecedorService.insertFornecedor(fornecedor3);
		
		Produto produto1=new Produto(null,"Biscoito","0001322000132","Comidas",fornecedor1);
		Produto produto2=new Produto(null,"Milho","0001323000132","Comidas",fornecedor2);
		Produto produto3=new Produto(null,"Refrigerante","0001324000132","Liquidos",fornecedor3);
		Produto produto4=new Produto(null,"Refrigerante Limao","0001325000132","Liquidos",fornecedor3);
		produtoService.insertProduto(produto1);
		produtoService.insertProduto(produto2);
		produtoService.insertProduto(produto3);
		produtoService.insertProduto(produto4);
		}catch(Exception e) 
		{
			
		}
	}

}
