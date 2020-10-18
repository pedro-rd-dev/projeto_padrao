package com.projeto_padrao;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.projeto_padrao.models.Usuario;
import com.projeto_padrao.models.resposta.UsuarioErro;
import com.projeto_padrao.retrofit.RetrofitConfig;

import org.json.JSONObject;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    //DECLARANDO OBJETOS
    private EditText login_editText_email,login_editText_senha;
    private Button login_button_usuario;
    private TextView login_text_registrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        //this.getLifecycle().addObserver(new ActivityObserver());
        identificandoComponentes();
        inicializandoComponentes();

    }

    private void identificandoComponentes() {

        //-------------------IDENTIFICANDO OS COMPONENTES EM "login.xml"----------//
        login_editText_email = (EditText) findViewById(R.id.login_editText_email);
        login_editText_senha = (EditText) findViewById(R.id.login_editText_senha);
        login_button_usuario = (Button) findViewById(R.id.login_button_login);
        login_text_registrar = (TextView) findViewById(R.id.login_text_registrar);




    }

    private void inicializandoComponentes() {
        //----------------------------- BOTÃO DE LOGIN--------------------------------//

        login_button_usuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = login_editText_email.getText().toString();
                String senha = login_editText_senha.getText().toString();

                Usuario usuarioLogado = new Usuario(email,senha);
                usuarioLogado.logar(LoginActivity.this);

                Log.d("autenticação","  \nUSUARIO: "+ email + "\nSENHA:"+ senha);


            }
        });

        //------------------------- BOTÃO DE TRANSIÇÃO PARA O REGISTRO--------------------------//
        login_text_registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }
}