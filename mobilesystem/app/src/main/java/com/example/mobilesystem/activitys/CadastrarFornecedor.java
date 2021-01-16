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
import com.example.mobilesystem.entities.Fornecedor;

import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class CadastrarFornecedor extends AppCompatActivity {
    private BootstrapEditText name, state, fornecedorCNPJ;
    private BootstrapButton register;
    private boolean erroTeste=false;
   //"https://app-backendjava.herokuapp.com";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_fornecedor);
        name = findViewById(R.id.name);
        state = findViewById(R.id.state);
        fornecedorCNPJ = findViewById(R.id.cnpj);
        register = findViewById(R.id.buttonRemove);
        setTitle("Cadastrar Fornecedor");
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
        OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
            @Override
            public void handleOnBackPressed() {
                System.out.println("Teste ");
                Intent it=new Intent(CadastrarFornecedor.this,Fornecedores.class);
                startActivity(it);
            }
        };
        this.getOnBackPressedDispatcher().addCallback(this, callback);
    }

    private class RegisterFornecedor extends AsyncTask<Void, Void, Void> {

        @Override
        protected synchronized Void doInBackground(Void... params) {
            String cnpj = fornecedorCNPJ.getText().toString();

            try {
                Fornecedor fornecedor = new Fornecedor(null, name.getText().toString(), state.getText().toString(),cnpj,null);
                java.net.URL url = new URL(Constants.getURL()+"/fornecedores/");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestProperty ("Authorization", Constants.getToken());
                connection.setRequestMethod("POST");

                connection.setRequestProperty("Content-type", "application/json"); connection.setRequestProperty("Accept", "application/json");

                connection.setDoOutput(true);

                PrintStream printStream = new PrintStream(connection.getOutputStream()); printStream.println(Fornecedor.Json(fornecedor));

                connection.connect();
                try {
                    String jsonDeResposta = new Scanner(connection.getInputStream()).next();
                }catch (Exception e)
                {
                    e.printStackTrace();
                }


            } catch (Exception e) {
                e.printStackTrace();
                erroTeste=true;
            }
            return null;
        }

        protected void onPostExecute(Void result) {
           if(erroTeste==false) {
               Toast.makeText(CadastrarFornecedor.this, "Operação realizada!", Toast.LENGTH_SHORT).show();
               register.setVisibility(View.VISIBLE);
               Intent it = new Intent(CadastrarFornecedor.this, Fornecedores.class);
               startActivity(it);
           }else
               {
                   Toast.makeText(CadastrarFornecedor.this, "A sua conta pode ter sido expirado, você terá que entrar na conta novamente!", Toast.LENGTH_SHORT).show();
                   Intent intent = new Intent(CadastrarFornecedor.this, MainActivity.class);
                   startActivity(intent);
                   finish();
               }
        }
    }
}