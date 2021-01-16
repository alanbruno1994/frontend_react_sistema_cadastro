package com.registrysystem.mobilesystem.activitys;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.beardedhen.androidbootstrap.BootstrapEditText;
import com.registrysystem.mobilesystem.R;
import com.registrysystem.mobilesystem.constants.Constants;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class RemoveProduto extends AppCompatActivity {
    private BootstrapEditText ID;
    private BootstrapButton remove;
    private boolean erroTeste=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remove_produto);
        setTitle("Remove Produto");
        ID = findViewById(R.id.state);
        remove = findViewById(R.id.buttonRemove);
        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ID.getText().toString().length() > 0) {
                        remove.setVisibility(View.INVISIBLE);
                        new Remove_Produto().execute();
                } else {
                    Toast.makeText(RemoveProduto.this, "Por favor, para deletar  você deve preencher o campo!", Toast.LENGTH_SHORT).show();

                }
            }
        });

        OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
            @Override
            public void handleOnBackPressed() {
                Intent it=new Intent(RemoveProduto.this,Produtos.class);
                startActivity(it);
            }
        };
        this.getOnBackPressedDispatcher().addCallback(this, callback);
    }

    private class Remove_Produto extends AsyncTask<Void, Void, Void> {

        @Override
        protected synchronized Void doInBackground(Void... params) {
            Integer id = Integer.parseInt(ID.getText().toString());
            try {
                java.net.URL url = new URL(Constants.getURL() + "/produtos/" + id);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestProperty ("Authorization", Constants.getToken());
                connection.setRequestMethod("DELETE");
                connection.setRequestProperty("Accept", "application/json");
                connection.connect();
                try {
                    String jsonDeResposta = new Scanner(connection.getInputStream()).next();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                connection.disconnect();
            } catch (Exception e) {
                erroTeste=true;
            }
            return null;
        }

        protected void onPostExecute(Void result) {
            if(erroTeste==false) {
                Toast.makeText(RemoveProduto.this, "Operação realizada!", Toast.LENGTH_SHORT).show();
                remove.setVisibility(View.VISIBLE);
                Intent it = new Intent(RemoveProduto.this, Produtos.class);
                startActivity(it);
            }else
                {
                    Toast.makeText(RemoveProduto.this, "A sua conta pode ter sido expirado, você terá que entrar na conta novamente!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(RemoveProduto.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
        }
    }
}