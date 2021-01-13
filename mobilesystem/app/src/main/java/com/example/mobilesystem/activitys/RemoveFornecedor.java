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

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class RemoveFornecedor extends AppCompatActivity {
    private EditText ID;
    private Button remove;
    private static String URL = "https://app-backendjava.herokuapp.com";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remove_fornecedor);
        ID = findViewById(R.id.state);
        remove=findViewById(R.id.buttonRemove);
        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ID.getText().toString().length() > 0) {
                    remove.setVisibility(View.INVISIBLE);
                    new Remove_Fornecedor().execute();
                } else {
                    Toast.makeText(RemoveFornecedor.this, "Por favor, para deletar  você deve preencher o campo!", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }

    private class Remove_Fornecedor extends AsyncTask<Void, Void, Void> {

        @Override
        protected synchronized Void doInBackground(Void... params) {
            Integer id = Integer.parseInt(ID.getText().toString());
            try {
                java.net.URL url = new URL(URL + "/fornecedores/" + id);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("DELETE");
                connection.setRequestProperty("Accept", "application/json");
                connection.connect();
                String jsonDeResposta = new Scanner(connection.getInputStream()).next();
                connection.disconnect();


            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        protected void onPostExecute(Void result) {
            Toast.makeText(RemoveFornecedor.this, "Operação realizada!", Toast.LENGTH_SHORT).show();
            remove.setVisibility(View.VISIBLE);
            Intent it=new Intent(RemoveFornecedor.this, Fornecedores.class);
            startActivity(it);
        }
    }
}