package com.registrationsystem.workshop.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.registrationsystem.workshop.entities.Fornecedor;
import com.registrationsystem.workshop.entities.Produto;
import com.registrationsystem.workshop.repository.ProdutoRepository;

@Service
public class ProdutoService {
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	public List<Produto> findAll()
	{
		return produtoRepository.findAll();
	}
	
	public void insertProduto(Produto produto)
	{
		produtoRepository.save(produto);
	}
	
	public Produto findById(Integer id) 
	{
		Optional<Produto> produto= produtoRepository.findById(id);
		return produto.get();
	}	
	
}
