package com.registrationsystem.workshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.registrationsystem.workshop.entities.Produto;

public interface ProdutoRepository extends JpaRepository<Produto,Integer>{
	
	@Query(value="SELECT * FROM PRODUTO p WHERE p.code = ?1", nativeQuery = true)
	Produto findProdutoByCode(String code);
	
}
