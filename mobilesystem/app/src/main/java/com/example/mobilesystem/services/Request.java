package com.example.mobilesystem.services;


import com.example.mobilesystem.entities.Fornecedor;
import com.example.mobilesystem.entities.Produto;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;


import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.io.PrintStream;
import java.net.HttpURLConnection;

import java.net.URL;
import java.util.List;
import java.util.Observable;
import java.util.Scanner;




public class Request extends Observable {

    private static String URL = "https://app-backendjava.herokuapp.com";
    private List<Produto> produtos;

    public static  synchronized Produto getProdutoByCode(String code) throws IOException, InterruptedException {
        java.net.URL url = new URL(URL+"/produtos/codes/" + code);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Accept", "application/json");
        connection.connect();
        String jsonDeResposta = new Scanner(connection.getInputStream()).next();
        connection.disconnect();
        return new ObjectMapper().readValue(jsonDeResposta, new TypeReference<Produto>() {
        });
    }




    public static synchronized Produto getProdutoByID(Integer id) throws IOException, InterruptedException {
        java.net.URL url = new URL(URL + "/produtos/" + id);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Accept", "application/json");
        connection.connect();
        String jsonDeResposta = new Scanner(connection.getInputStream()).next();
        connection.disconnect();
        System.out.println("convert: "+jsonDeResposta);
        Produto produto= new ObjectMapper().readValue(jsonDeResposta, new TypeReference<Produto>() {
        });
        System.out.println("convertido em : "+produto);
        return  produto;

    }

    public static synchronized Fornecedor getFornecedorByID(Integer id) throws IOException, InterruptedException {
        java.net.URL url = new URL(URL + "/fornecedores/" + id);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Accept", "application/json");
        connection.connect();
        String jsonDeResposta = new Scanner(connection.getInputStream()).next();
        connection.disconnect();
        return new ObjectMapper().readValue(jsonDeResposta, new TypeReference<Fornecedor>() {
        });


    }

    public void getProdutos() throws IOException, InterruptedException, ParseException, ParseException {

        java.net.URL url = new URL(URL + "/produtos");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Accept", "application/json");
        connection.connect();
        Scanner sc= new Scanner(connection.getInputStream(),"UTF-8");
        String jsonDeResposta = "";
        while(sc.hasNext())
        {
            jsonDeResposta+=sc.nextLine();
        }
        System.out.println(jsonDeResposta);

        connection.disconnect();
        List<Produto> list=new ObjectMapper().readValue(jsonDeResposta, new TypeReference<List<Produto>>() {
        });
        produtos=list;
        setChanged();
        notifyObservers();
    }

    public static synchronized void deleteByIdFornecedor(Integer id) throws IOException, InterruptedException {



    }

    public static synchronized void deleteByIdProduto(Integer id) throws IOException, InterruptedException {
        java.net.URL url = new URL(URL +"/produtos/" + id);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("DELETE");
        connection.setRequestProperty("Accept", "application/json");
        connection.connect();
        String jsonDeResposta = new Scanner(connection.getInputStream()).next();
        connection.disconnect();

    }

    public static synchronized Produto getProdutoByCNPJ(String cnpj) throws IOException, InterruptedException {
        java.net.URL url = new URL(URL + "/produtos/cnpj/" + cnpj);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Accept", "application/json");
        connection.connect();
        String jsonDeResposta = new Scanner(connection.getInputStream()).next();
        connection.disconnect();
        Produto produto= new ObjectMapper().readValue(jsonDeResposta, new TypeReference<Produto>() {
        });
        return  produto;
    }

    public static synchronized List<Fornecedor> getFornecedores() throws IOException, InterruptedException {
      java.net.URL url = new URL(URL + "/fornecedores");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Accept", "application/json");
        connection.connect();
        String jsonDeResposta = new Scanner(connection.getInputStream()).next();
        connection.disconnect();
        return new ObjectMapper().readValue(jsonDeResposta, new TypeReference<List<Fornecedor>>() {
        });

    }

    public static synchronized void insertProduto(Produto produto,String cnpj) throws IOException, InterruptedException {
        URL url = new URL(URL+"/produtos/"+cnpj);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        connection.setRequestMethod("POST");

        connection.setRequestProperty("Content-type", "application/json"); connection.setRequestProperty("Accept", "application/json");

        connection.setDoOutput(true);

        PrintStream printStream = new PrintStream(connection.getOutputStream()); printStream.println(Produto.Json(produto));

        connection.connect();

        String jsonDeResposta = new Scanner(connection.getInputStream()).next();


    }


    public static synchronized void insertFornecedor(Fornecedor fornecedor) throws IOException, InterruptedException {
        java.net.URL url = new URL(URL+"/fornecedores/");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        connection.setRequestMethod("POST");

        connection.setRequestProperty("Content-type", "application/json"); connection.setRequestProperty("Accept", "application/json");

        connection.setDoOutput(true);

        PrintStream printStream = new PrintStream(connection.getOutputStream()); printStream.println(Fornecedor.Json(fornecedor));

        connection.connect();

        String jsonDeResposta = new Scanner(connection.getInputStream()).next();

    }

    public List<Produto> produtoList()
    {
        return  produtos;
    }

}
