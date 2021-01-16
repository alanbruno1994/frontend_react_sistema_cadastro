package com.registrysystem.mobilesystem.activitys;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.registrysystem.mobilesystem.R;

public class MenuSystem extends AppCompatActivity {
    private BootstrapButton produtos,fornecedores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        produtos=findViewById(R.id.buttonProdutos);
        fornecedores=findViewById(R.id.buttonFornecedores);
        setTitle("Menu");
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