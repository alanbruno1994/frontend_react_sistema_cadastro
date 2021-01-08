package com.registrationsystem.workshop.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.registrationsystem.workshop.entities.Fornecedor;
import com.registrationsystem.workshop.repository.FornecedorRepository;

@Service
public class FornecedorService {

	@Autowired
	private FornecedorRepository produtoRepository;
	
	public List<Fornecedor> findAll()
	{
		return produtoRepository.findAll();
	}
	
	public void insertFornecedor(Fornecedor fornecedor)
	{
		produtoRepository.save(fornecedor);
	}
	
	public Fornecedor findById(Integer id) 
	{
		Optional<Fornecedor> fornecedor= produtoRepository.findById(id);
		return fornecedor.get();
	}
	
}
