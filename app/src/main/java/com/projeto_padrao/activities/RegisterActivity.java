package com.projeto_padrao.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.projeto_padrao.R;
import com.projeto_padrao.models.Usuario;

import static com.projeto_padrao.statics.ConstantesGlobais.SENHAS_DISTINTAS;

public class RegisterActivity extends AppCompatActivity {

    private EditText register_editText_usuario,register_editText_senha,register_editText_email,register_editText_senha_repita;
    private Button register_button_usuario;
    private TextView register_text_registrar,register_textView_aviso_email,register_textView_aviso_senha,register_textView_aviso_senha_repetida;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        identificandoComponentes();
        inicializandoComponentes();

    }

    private void identificandoComponentes() {

        //-------------------IDENTIFICANDO OS COMPONENTES EM "login.xml"----------//
        this.register_editText_usuario = (EditText) findViewById(R.id.register_editText_usuario);
        this.register_editText_email = (EditText) findViewById(R.id.register_editText_email);

        this.register_editText_senha = (EditText) findViewById(R.id.register_editText_senha);
        this.register_editText_senha_repita = (EditText) findViewById(R.id.register_editText_senha_repita);

        this.register_button_usuario = (Button) findViewById(R.id.register_button_login);
        this.register_text_registrar = (TextView) findViewById(R.id.register_text_logar);

        this.register_textView_aviso_email = (TextView) findViewById(R.id.register_textView_aviso_email);
        this.register_textView_aviso_senha = (TextView) findViewById(R.id.register_textView_aviso_senha);
        this.register_textView_aviso_senha_repetida = (TextView) findViewById(R.id.register_textView_aviso_senha_repetida);

        esconderAvisos();

        //----------------------------------------------------------------------------------//
    }

    private void esconderAvisos() {
        register_textView_aviso_email.setVisibility(View.GONE);
        register_textView_aviso_senha.setVisibility(View.GONE);
        register_textView_aviso_senha_repetida.setVisibility(View.GONE);
    }

    private void inicializandoComponentes() {
        //----------------------------- BOTÃO DE REGISTRO--------------------------------//

        register_button_usuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                esconderAvisos();

                if(verfificaSenhas()){

                    Usuario usuario = criarObjetoUsuarioPelosCampos();
                    usuario.registrar();
                }

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

    private Usuario criarObjetoUsuarioPelosCampos() {
        String email = register_editText_email.getText().toString();
        String senha = register_editText_senha.getText().toString();
        String nome = register_editText_usuario.getText().toString();
        Usuario usuario = new Usuario(email,senha,nome,RegisterActivity.this);
        return usuario;
    }

    public void mostrarAvisoEmail(String aviso){
        this.register_textView_aviso_email.setVisibility(View.VISIBLE);
        this.register_textView_aviso_email.setText(aviso);
    }

    public void mostrarAvisoSenha(String aviso){
        this.register_textView_aviso_senha.setVisibility(View.VISIBLE);
        this.register_textView_aviso_senha.setText(aviso);
    }

    public boolean verfificaSenhas(){
        String senha = this.register_editText_senha.getText().toString();
        String senha_repetida = this.register_editText_senha_repita.getText().toString();

        if(!senha.equals(senha_repetida)){
            this.register_textView_aviso_senha_repetida.setVisibility(View.VISIBLE);
            this.register_textView_aviso_senha_repetida.setText(SENHAS_DISTINTAS);
            return false;
        }
        this.register_textView_aviso_senha_repetida.setVisibility(View.GONE);
        return true;

    }
}