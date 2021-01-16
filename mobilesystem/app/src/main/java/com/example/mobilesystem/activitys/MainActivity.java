package com.example.mobilesystem.activitys;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.beardedhen.androidbootstrap.BootstrapEditText;
import com.example.mobilesystem.R;
import com.example.mobilesystem.constants.Constants;
import com.example.mobilesystem.entities.UserSystem;

import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {

    private BootstrapButton Login;

    private BootstrapEditText name, password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Login = findViewById(R.id.buttonProdutos);
        name=findViewById(R.id.name);
        password=findViewById(R.id.password);
        setTitle("Login");
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (name.getText().length() > 0 && password.getText().length() > 0) {
                       Login.setVisibility(View.INVISIBLE);
                       new Login().execute();
                }

            }
        });
        OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
            @Override
            public void handleOnBackPressed() {

            }
        };
        this.getOnBackPressedDispatcher().addCallback(this, callback);
    }



    private class Login extends AsyncTask<Void, Void, Void>
    {


        @Override
        protected Void doInBackground(Void... voids) {
            try {
                System.out.println("<<<<<<<  "+name.getText().toString()+" "+password.getText().toString());
                UserSystem user = new UserSystem(name.getText().toString(), password.getText().toString());
                URL url = new URL(Constants.getURL() + "/users");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                connection.setRequestMethod("POST");

                connection.setRequestProperty("Content-type", "application/json");
                connection.setRequestProperty("Accept", "application/json");

                connection.setDoOutput(true);

                PrintStream printStream = new PrintStream(connection.getOutputStream());
                printStream.println(UserSystem.Json(user));

                connection.connect();

                Scanner sc = new Scanner(connection.getInputStream(), "UTF-8");
                String jsonDeResposta = "";
                while (sc.hasNext()) {
                    jsonDeResposta += sc.nextLine();
                }

                Constants.setToken(jsonDeResposta);
                if (jsonDeResposta.contains("Bearer")) {
                    Intent intent = new Intent(MainActivity.this, MenuSystem.class);
                    startActivity(intent);
                    finish();
                }
            }catch (Exception e)
            {
                e.printStackTrace();
            }
            return null;
        }
    }
}