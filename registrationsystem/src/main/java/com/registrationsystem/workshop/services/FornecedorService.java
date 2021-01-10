package com.registrationsystem.workshop.services;

import java.util.List;
import java.util.Optional;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.registrationsystem.workshop.entities.Fornecedor;
import com.registrationsystem.workshop.repository.FornecedorRepository;
import com.registrationsystem.workshop.services.exceptions.CompositeException;
import com.registrationsystem.workshop.services.exceptions.DuplicateElement;
import com.registrationsystem.workshop.services.exceptions.ObjectNotFound;

@Service
public class FornecedorService {

	@Autowired
	private FornecedorRepository fornecedorRepository;

	public List<Fornecedor> findAll() {
		return fornecedorRepository.findAll();
	}

	public void insertFornecedor(Fornecedor fornecedor) {
		try {
			fornecedorRepository.save(fornecedor);
		} catch (ConstraintViolationException e) {
			throw new DuplicateElement("Existe fornecedor com este CNPJ");
		} catch (Exception e) {
			throw new DuplicateElement("Existe fornecedor com este CNPJ");
		}
	}

	public Fornecedor findById(Integer id) {
		Optional<Fornecedor> fornecedor = fornecedorRepository.findById(id);
		return fornecedor.orElseThrow(() -> new ObjectNotFound("Fornecedor não foi encontrado"));
	}

	public void removeById(Integer id) {
		try {
			fornecedorRepository.deleteById(id);
		} catch (ConstraintViolationException e) {
			throw new CompositeException("Este fornecedor tem produto, por tanto você deve deletar os produtos para que ele seja apagado!");
		}catch (DataIntegrityViolationException e) {
			throw new CompositeException("Este fornecedor tem produto, por tanto você deve deletar os produtos para que ele seja apagado!");
		}
	}

}
