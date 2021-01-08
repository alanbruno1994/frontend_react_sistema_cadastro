package com.registrationsystem.workshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.registrationsystem.workshop.entities.Produto;

public interface ProdutoRepository extends JpaRepository<Produto,Integer>{

}
