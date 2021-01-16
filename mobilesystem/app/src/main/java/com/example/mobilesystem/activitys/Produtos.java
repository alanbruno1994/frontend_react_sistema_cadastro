package com.example.mobilesystem.activitys;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mobilesystem.R;
import com.example.mobilesystem.constants.Constants;
import com.example.mobilesystem.entities.Produto;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashSet;
import java.util.Scanner;

public class Produtos extends AppCompatActivity {
    private HashSet<Produto> produtoList = new HashSet<Produto>();
    private TextView id, name, code, category, cnpj;
    private boolean erroTeste=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produtos);
        id = findViewById(R.id.id);
        name = findViewById(R.id.name);
        code = findViewById(R.id.code);
        category = findViewById(R.id.category);
        cnpj = findViewById(R.id.total);
        new GetProdutos().execute();
        setTitle("Listar Produtos");
        //Isso defini a tela de retorno
        OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
            @Override
            public void handleOnBackPressed() {
                Intent intent = new Intent(Produtos.this, MenuSystem.class);
                startActivity(intent);
                finish();
            }
        };
        this.getOnBackPressedDispatcher().addCallback(this, callback);
        Configuration configuration = getResources().getConfiguration();
        if (configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            id.setTextSize(7);
            name.setTextSize(7);
            code.setTextSize(7);
            category.setTextSize(7);
            cnpj.setTextSize(7);
        } else {
            id.setTextSize(5);
            name.setTextSize(5);
            code.setTextSize(5);
            category.setTextSize(5);
            cnpj.setTextSize(5);
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menuregisterproduto, (android.view.Menu) menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.register) {
            Intent intent = new Intent(Produtos.this, CadastrarProduto.class);
            startActivity(intent);
            finish();
        } else if (id == R.id.delete) {
            Intent intent = new Intent(Produtos.this, RemoveProduto.class);
            startActivity(intent);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }


    private class GetProdutos extends AsyncTask<Void, Void, Void> {

        @Override
        protected synchronized Void doInBackground(Void... params) {

            try {
                java.net.URL url = new URL(Constants.getURL() + "/produtos");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.setRequestProperty("Authorization", Constants.getToken());
                connection.setRequestProperty("Accept", "application/json");
                connection.connect();
                Scanner sc = new Scanner(connection.getInputStream(), "UTF-8");
                String jsonDeResposta = "";
                while (sc.hasNext()) {
                    jsonDeResposta += sc.nextLine();
                }
                connection.disconnect();
                HashSet<Produto> list = new ObjectMapper().readValue(jsonDeResposta, new TypeReference<HashSet<Produto>>() {
                });
                produtoList.addAll(list);
            } catch (Exception e) {
                erroTeste=true;

            }
            return null;
        }

        protected void onPostExecute(Void result) {
            if (produtoList != null) {
                String _id = "", _name = "", _code = "", _cnpj = "", _category = "";
                for (Produto produto : produtoList) {

                    try {
                        _id += ("" + produto.getId() + "\n");
                        _name += (produto.getName() + "\n");
                        _code += (produto.getCode() + "\n");
                        _cnpj += (produto.getFornecedor().getCnpj() + "\n");
                        _category += (produto.getCategory() + "\n");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                id.setText(_id);
                name.setText(_name);
                code.setText(_code);
                cnpj.setText(_cnpj);
                category.setText(_category);
            }
            if(erroTeste)
            {
                Toast.makeText(Produtos.this, "A sua conta pode ter sido expirado, você terá que entrar na conta novamente!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Produtos.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }
    }


}