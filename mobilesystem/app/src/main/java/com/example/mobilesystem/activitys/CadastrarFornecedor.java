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

import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class CadastrarFornecedor extends AppCompatActivity {
    private EditText name, state, fornecedorCNPJ;
    private Button register;
    private static String URL = "https://app-backendjava.herokuapp.com";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_fornecedor);
        name = findViewById(R.id.name);
        state = findViewById(R.id.state);
        fornecedorCNPJ = findViewById(R.id.cnpj);
        register = findViewById(R.id.buttonRemove);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (name.getText().length() > 0 && fornecedorCNPJ.getText().length() > 0 && state.getText().length() > 0) {
                    if (fornecedorCNPJ.getText().length() == 14) {
                        if (state.getText().length() >= 3) {
                            if (name.getText().length() >= 12) {
                                    register.setVisibility(View.INVISIBLE);
                                    new RegisterFornecedor().execute();
                            }else
                            {

                                Toast.makeText(CadastrarFornecedor.this, "O nome deve ter pelo menos 12 dígitos!", Toast.LENGTH_SHORT).show();
                            }
                        } else {

                            Toast.makeText(CadastrarFornecedor.this, "O estado deve ter pelo menos 3 dígitos!", Toast.LENGTH_SHORT).show();
                        }
                    } else {

                        Toast.makeText(CadastrarFornecedor.this, "O CNPJ deve ter 14 números!", Toast.LENGTH_SHORT).show();

                    }
                } else {

                    Toast.makeText(CadastrarFornecedor.this, "Por favor, para realizar o cadastro você deve preencher todos os campos!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private class RegisterFornecedor extends AsyncTask<Void, Void, Void> {

        @Override
        protected synchronized Void doInBackground(Void... params) {
            String cnpj = fornecedorCNPJ.getText().toString();

            try {
                Fornecedor fornecedor = new Fornecedor(null, name.getText().toString(), state.getText().toString(),cnpj,null);
                java.net.URL url = new URL(URL+"/fornecedores/");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                connection.setRequestMethod("POST");

                connection.setRequestProperty("Content-type", "application/json"); connection.setRequestProperty("Accept", "application/json");

                connection.setDoOutput(true);

                PrintStream printStream = new PrintStream(connection.getOutputStream()); printStream.println(Fornecedor.Json(fornecedor));

                connection.connect();

                String jsonDeResposta = new Scanner(connection.getInputStream()).next();


            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        protected void onPostExecute(Void result) {
            Toast.makeText(CadastrarFornecedor.this, "Operação realizada!", Toast.LENGTH_SHORT).show();
            register.setVisibility(View.VISIBLE);
            Intent it=new Intent(CadastrarFornecedor.this,Fornecedores.class);
            startActivity(it);
        }
    }
}