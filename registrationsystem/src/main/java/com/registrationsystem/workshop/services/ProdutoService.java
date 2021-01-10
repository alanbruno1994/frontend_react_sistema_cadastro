package com.registrationsystem.workshop.services;

import java.util.List;
import java.util.Optional;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.registrationsystem.workshop.entities.Fornecedor;
import com.registrationsystem.workshop.entities.Produto;
import com.registrationsystem.workshop.repository.ProdutoRepository;
import com.registrationsystem.workshop.services.exceptions.DuplicateElement;
import com.registrationsystem.workshop.services.exceptions.ObjectNotFound;

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
		try {
			produtoRepository.save(produto);
		} catch (ConstraintViolationException e) {
			throw new DuplicateElement("Existe produto com este codígo");
		}catch (Exception e) {
			throw new DuplicateElement("Existe produto com este codígo");
		}
		
	}
	
	public Produto findById(Integer id) 
	{
		Optional<Produto> produto= produtoRepository.findById(id);
		return produto.orElseThrow(()->new ObjectNotFound("Produto não foi encontrado"));
	}	
	
	public void removeById(Integer id) {
		produtoRepository.deleteById(id);		
	}
}
