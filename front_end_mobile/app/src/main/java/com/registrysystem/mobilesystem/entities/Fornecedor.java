package com.registrysystem.mobilesystem.entities;
import java.io.Serializable;
import java.util.Objects;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Fornecedor implements Serializable{
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String name, state, cnpj;
    private Integer totalProdutos;


    public Fornecedor() {

    }

    public Fornecedor(Integer id, String name, String state, String cnpj, Integer totalProdutos) {
        super();
        this.id = id;
        this.name = name;
        this.state = state;
        this.cnpj = cnpj;
        this.totalProdutos = totalProdutos;
    }


    public Integer getId() {
        return id;
    }


    public void setId(Integer id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public String getState() {
        return state;
    }


    public void setState(String state) {
        this.state = state;
    }


    public String getCnpj() {
        return cnpj;
    }


    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }


    public Integer getTotalProdutos() {
        return totalProdutos;
    }


    public void setTotalProdutos(Integer totalProdutos) {
        this.totalProdutos = totalProdutos;
    }


    @Override
    public String toString() {
        return "Fornecedor [id=" + id + ", name=" + name + ", state=" + state + ", cnpj=" + cnpj + ", totalProdutos="
                + totalProdutos + "]";
    }

    public static String Json(Fornecedor fornecedor) throws JsonProcessingException
    {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(fornecedor);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Fornecedor)) return false;
        Fornecedor that = (Fornecedor) o;
        return Objects.equals(cnpj, that.cnpj);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cnpj);
    }
}
