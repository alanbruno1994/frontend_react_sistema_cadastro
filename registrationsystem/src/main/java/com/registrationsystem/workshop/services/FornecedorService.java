package com.registrationsystem.workshop.services;

import java.util.List;
import java.util.Optional;

import org.hibernate.engine.jdbc.spi.SqlExceptionHelper;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.registrationsystem.workshop.entities.Fornecedor;
import com.registrationsystem.workshop.repository.FornecedorRepository;
import com.registrationsystem.workshop.services.exceptions.DuplicateElement;
import com.registrationsystem.workshop.services.exceptions.ObjectNotFound;

@Service
public class FornecedorService {

	@Autowired
	private FornecedorRepository produtoRepository;

	public List<Fornecedor> findAll() {
		return produtoRepository.findAll();
	}

	public void insertFornecedor(Fornecedor fornecedor) {
		try {
			produtoRepository.save(fornecedor);
		} catch (ConstraintViolationException e) {
			throw new DuplicateElement("Existe fornecedor com este CNPJ");
		}catch (Exception e) {
			throw new DuplicateElement("Existe fornecedor com este CNPJ");
		}
	}

	public Fornecedor findById(Integer id) {
		Optional<Fornecedor> fornecedor = produtoRepository.findById(id);
		return fornecedor.orElseThrow(() -> new ObjectNotFound("Fornecedor não foi encontrado"));
	}

}
