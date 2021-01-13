package com.example.mobilesystem.activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mobilesystem.R;
import com.example.mobilesystem.entities.Fornecedor;
import com.example.mobilesystem.entities.Produto;

import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class CadastrarProduto extends AppCompatActivity {
    private EditText name, code, category, fornecedorCNPJ;
    private Button register;
    private static String URL = "https://app-backendjava.herokuapp.com";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_produto);
        name = findViewById(R.id.name);
        code = findViewById(R.id.code);
        category = findViewById(R.id.category);
        fornecedorCNPJ = findViewById(R.id.cnpj);
        register = findViewById(R.id.buttonRemove);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (name.getText().length() > 0 && code.getText().length() > 0 && category.getText().length() > 0 && fornecedorCNPJ.getText().length() > 0) {
                    if (code.getText().length() == 13) {
                        if (name.getText().length() >= 2) {
                            if (name.getText().length() >= 2) {
                                if (fornecedorCNPJ.getText().length() == 14) {
                                    register.setVisibility(View.INVISIBLE);
                                    new RegisterProduto().execute();

                                } else {

                                    Toast.makeText(CadastrarProduto.this, "O CNPJ deve ter 14 números!", Toast.LENGTH_SHORT).show();
                                }
                            } else {


                                Toast.makeText(CadastrarProduto.this, "A categoria deve ter pelo menos 2 caracteres!", Toast.LENGTH_SHORT).show();

                            }
                        } else {

                            Toast.makeText(CadastrarProduto.this, "O nome do produto deve ter pelo menos 2 caracteres!", Toast.LENGTH_SHORT).show();

                        }
                    } else {
                        Toast.makeText(CadastrarProduto.this, "O código de barras deve ter 13 dígitos!", Toast.LENGTH_SHORT).show();

                    }
                } else {
                    Toast.makeText(CadastrarProduto.this, "Por favor, para realizar o cadastro você deve preencher todos os campos!", Toast.LENGTH_SHORT).show();

                }

            }
        });
    }


    private class RegisterProduto extends AsyncTask<Void, Void, Void> {

        @Override
        protected synchronized Void doInBackground(Void... params) {
            String cnpj = fornecedorCNPJ.getText().toString();

            try {
                Produto produto = new Produto(null, name.getText().toString(), code.getText().toString(), category.getText().toString());
                URL url = new URL(URL + "/produtos/" + cnpj);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                connection.setRequestMethod("POST");

                connection.setRequestProperty("Content-type", "application/json");
                connection.setRequestProperty("Accept", "application/json");

                connection.setDoOutput(true);

                PrintStream printStream = new PrintStream(connection.getOutputStream());
                printStream.println(Produto.Json(produto));

                connection.connect();

                String jsonDeResposta = new Scanner(connection.getInputStream()).next();


            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        protected void onPostExecute(Void result) {
            Toast.makeText(CadastrarProduto.this, "Operação realizada!", Toast.LENGTH_SHORT).show();
            register.setVisibility(View.VISIBLE);
            Intent it=new Intent(CadastrarProduto.this, Produtos.class);
            startActivity(it);
        }
    }
}

