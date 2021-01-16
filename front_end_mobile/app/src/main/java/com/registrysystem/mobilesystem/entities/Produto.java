package com.registrysystem.mobilesystem.entities;

import java.io.Serializable;
import java.util.Objects;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Produto implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String name, code, category;
    private Fornecedor fornecedor;


    public Produto() {

    }


    /**
     * @param name
     *
     */


    public Produto(String name) {
        this.name = name;
        this.code = code;
        this.category = category;
    }


    public Produto(Integer id, String name, String code, String category, Fornecedor fornecedor) {
        super();
        this.id = id;
        this.name = name;
        this.code = code;
        this.category = category;
        this.fornecedor = fornecedor;
    }


    public Produto(Integer id, String name, String code, String category) {
        super();
        this.id = id;
        this.name = name;
        this.code = code;
        this.category = category;
    }


    public Fornecedor getFornecedor() {
        return fornecedor;
    }


    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
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


    public String getCode() {
        return code;
    }


    public void setCode(String code) {
        this.code = code;
    }


    public String getCategory() {
        return category;
    }


    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Produtos [id=" + id + ", name=" + name + ", code=" + code + ", category=" + category + "," + this.fornecedor + "]";
    }

    public static String Json(Produto produto) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(produto);
    }

    public static Produto instanceOf(Object object) {
        if (object instanceof Produto) {
            return (Produto) object;
        }
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Produto)) return false;
        Produto produto = (Produto) o;
        return Objects.equals(code, produto.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code);
    }
}
