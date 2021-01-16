package com.example.mobilesystem.activitys;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.beardedhen.androidbootstrap.BootstrapEditText;
import com.example.mobilesystem.R;
import com.example.mobilesystem.constants.Constants;
import com.example.mobilesystem.entities.Produto;

import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class CadastrarProduto extends AppCompatActivity {
    private BootstrapEditText name, code, category, fornecedorCNPJ;
    private BootstrapButton register;
    private boolean erroTeste = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_produto);
        name = findViewById(R.id.name);
        code = findViewById(R.id.code);
        category = findViewById(R.id.category);
        fornecedorCNPJ = findViewById(R.id.cnpj);
        register = findViewById(R.id.buttonRemove);
        setTitle("Cadastrar Produto");
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
        OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
            @Override
            public void handleOnBackPressed() {
                Intent it = new Intent(CadastrarProduto.this, Produtos.class);
                startActivity(it);
            }
        };
        this.getOnBackPressedDispatcher().addCallback(this, callback);
    }


    private class RegisterProduto extends AsyncTask<Void, Void, Void> {

        @Override
        protected synchronized Void doInBackground(Void... params) {
            String cnpj = fornecedorCNPJ.getText().toString();

            try {
                Produto produto = new Produto(null, name.getText().toString(), code.getText().toString(), category.getText().toString());
                URL url = new URL(Constants.getURL() + "/produtos/" + cnpj);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                connection.setRequestMethod("POST");
                connection.setRequestProperty("Authorization", Constants.getToken());
                connection.setRequestProperty("Content-type", "application/json");
                connection.setRequestProperty("Accept", "application/json");

                connection.setDoOutput(true);

                PrintStream printStream = new PrintStream(connection.getOutputStream());
                printStream.println(Produto.Json(produto));

                connection.connect();

                String jsonDeResposta = new Scanner(connection.getInputStream()).next();


            } catch (Exception e) {
                erroTeste = true;
            }
            return null;
        }

        protected void onPostExecute(Void result) {
            if (erroTeste == false) {
                Toast.makeText(CadastrarProduto.this, "Operação realizada!", Toast.LENGTH_SHORT).show();
                register.setVisibility(View.VISIBLE);
                Intent it = new Intent(CadastrarProduto.this, Produtos.class);
                startActivity(it);
            }else
                {
                    Toast.makeText(CadastrarProduto.this, "A sua conta pode ter sido expirado, você terá que entrar na conta novamente!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(CadastrarProduto.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
        }
    }
}

