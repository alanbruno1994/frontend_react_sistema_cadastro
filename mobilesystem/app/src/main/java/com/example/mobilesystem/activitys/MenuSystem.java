package com.example.mobilesystem.activitys;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.mobilesystem.R;

public class MenuSystem extends AppCompatActivity {
    private Button produtos,fornecedores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        produtos=findViewById(R.id.buttonProdutos);
        fornecedores=findViewById(R.id.buttonFornecedores);
        produtos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it=new Intent(MenuSystem.this,Produtos.class);
                startActivity(it);
            }
        });
        fornecedores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it=new Intent(MenuSystem.this,Fornecedores.class);
                startActivity(it);
            }
        });
        OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
            @Override
            public void handleOnBackPressed() {
                

            }
        };
        this.getOnBackPressedDispatcher().addCallback(this, callback);
    }
}