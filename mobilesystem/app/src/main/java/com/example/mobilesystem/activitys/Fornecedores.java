package com.example.mobilesystem.activitys;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.mobilesystem.R;
import com.example.mobilesystem.entities.Fornecedor;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashSet;
import java.util.Scanner;

public class Fornecedores extends AppCompatActivity {
    private HashSet<Fornecedor> fornecedoresList=new HashSet<>();
    private TextView id,name,state,cnpj,total;
    private static String URL = "https://app-backendjava.herokuapp.com";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fornecedores);
        id=findViewById(R.id.id);
        name=findViewById(R.id.name);
        state=findViewById(R.id.state);
        cnpj=findViewById(R.id.cnpj);
        total=findViewById(R.id.total);
        OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
            @Override
            public void handleOnBackPressed() {
                Intent intent = new Intent(Fornecedores.this, MenuSystem.class);
                startActivity(intent);
                finish();
            }
        };
        this.getOnBackPressedDispatcher().addCallback(this, callback);
        new GetFornecedores().execute();
    }

    private class GetFornecedores extends AsyncTask<Void, Void, Void> {

        @Override
        protected synchronized Void doInBackground(Void... params) {

            try {
                java.net.URL url = new URL(URL + "/fornecedores");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.setRequestProperty("Accept", "application/json");
                connection.connect();
                Scanner sc = new Scanner(connection.getInputStream(), "UTF-8");
                String jsonDeResposta = "";
                while (sc.hasNext()) {
                    jsonDeResposta += sc.nextLine();
                }
                connection.disconnect();
                HashSet<Fornecedor> list = new ObjectMapper().readValue(jsonDeResposta, new TypeReference<HashSet<Fornecedor>>() {
                });
                fornecedoresList.addAll(list);
            }catch(Exception e)
            {
                e.printStackTrace();
            }
            return  null;
        }
        protected void onPostExecute(Void result) {
            if(fornecedoresList!=null) {
                String _id="",_name="",_state="",_cnpj="",_total="";
                for (Fornecedor fornecedor : fornecedoresList) {
                    try {
                        _id+=(""+fornecedor.getId()+"\n");
                        _name+=(fornecedor.getName()+"\n");
                        _state+=(fornecedor.getState()+"\n");
                        _cnpj+=(fornecedor.getCnpj()+"\n");
                        _total+=(""+fornecedor.getTotalProdutos()+"\n");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                id.setText(_id);
                name.setText(_name);
                state.setText(_state);
                cnpj.setText(_cnpj);
                total.setText(_total);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menuregisterfornecedor, (android.view.Menu) menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.register) {
            Intent intent = new Intent(Fornecedores.this, CadastrarFornecedor.class);
            startActivity(intent);
            finish();
        } else if(id == R.id.delete)
        {
            Intent intent = new Intent(Fornecedores.this, RemoveFornecedor.class);
            startActivity(intent);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}