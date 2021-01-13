package com.example.mobilesystem.services.util;

import android.os.AsyncTask;

import com.example.mobilesystem.entities.Fornecedor;
import com.example.mobilesystem.entities.Produto;
import com.example.mobilesystem.services.Request;

import java.io.IOException;

public class AsyncRequest extends AsyncTask<Object, Void, Object> {
    private Request request=new Request();

    public AsyncRequest()
    {

    }


    /**
     *
     * @param objects
     * @return
     */

    @Override
    protected Object doInBackground(Object... objects) {
        Integer caso = (Integer) objects[0];
        switch (caso) {
            case 1://search by code produto
                try {
                    String code = (String) objects[1];
                    return Request.getProdutoByCode(code);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                break;
            case 2://search by id produto
                try {
                    Integer id = (Integer) objects[1];
                    return Request.getProdutoByID(id);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                break;
            case 3://search by id fornecedor
                try {
                    Integer id = (Integer) objects[1];
                    return Request.getFornecedorByID(id);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                break;
            case 4://search by produtos
                try {
                     request.getProdutos();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case 5://search by fornecedores
                try {
                    return Request.getFornecedores();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                break;
            case 6://insert fornecedor
                try {
                    Fornecedor fornecedor = (Fornecedor) objects[1];
                    Request.insertFornecedor(fornecedor);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                break;
            case 7://insert produto
                try {
                    Produto produto = (Produto) objects[1];
                    String cnpj=(String)objects[2];
                    Request.insertProduto(produto,cnpj);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            case 8://delete fornecedor by id
                try {
                    Integer id = (Integer) objects[1];
                    Request.deleteByIdFornecedor(id);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                break;
            case 9://delete produto
                try {
                    Integer id = (Integer) objects[1];
                    Request.deleteByIdProduto(id);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                break;
            case 10://search by cnpj produto
                try {
                    String cnpj = (String) objects[1];
                    return Request.getProdutoByCNPJ(cnpj);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                break;
        }
        return null;
    }

    public  Request getRequest()
    {
        return request;
    }
}
