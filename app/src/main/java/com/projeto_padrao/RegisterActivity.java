package com.projeto_padrao;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.projeto_padrao.models.Aplicacao;
import com.projeto_padrao.models.Usuario;

public class RegisterActivity extends AppCompatActivity {

    private EditText register_editText_usuario,register_editText_senha;
    private Button register_button_usuario;
    private TextView register_text_registrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        identificandoComponentes();
        inicializandoComponentes();

    }
    private void identificandoComponentes() {

        //-------------------IDENTIFICANDO OS COMPONENTES EM "login.xml"----------//
        register_editText_usuario = (EditText) findViewById(R.id.register_editText_usuario);
        register_editText_senha = (EditText) findViewById(R.id.register_editText_senha);
        register_button_usuario = (Button) findViewById(R.id.register_button_login);
        register_text_registrar = (TextView) findViewById(R.id.register_text_logar);
        //----------------------------------------------------------------------------------//
    }

    private void inicializandoComponentes() {
        //----------------------------- BOTÃO DE REGISTRO--------------------------------//

        register_button_usuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usuario = register_editText_usuario.getText().toString();
                String senha = register_editText_senha.getText().toString();

                Usuario usuarioLogado = new Usuario(usuario,senha);
                usuarioLogado.registrar(RegisterActivity.this);
            }
        });


        //----------------------------- BOTÃO DE TRANSIÇÃO PARA O REGISTRO--------------------------------//
        register_text_registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}