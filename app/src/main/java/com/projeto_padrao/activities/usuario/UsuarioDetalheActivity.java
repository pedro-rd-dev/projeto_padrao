package com.projeto_padrao.activities.usuario;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.projeto_padrao.R;
import com.projeto_padrao.models.Usuario;

import static com.projeto_padrao.statics.ConstantesGlobais.SENHAS_DISTINTAS;

public class UsuarioDetalheActivity extends AppCompatActivity {

    private EditText usuario_editar_editText_usuario,usuario_editar_editText_email,usuario_editar_editText_senha,usuario_editar_editText_senha_repita;
    private Button usuario_editar_button_usuario;
    private TextView usuario_editar_textView_aviso_email,usuario_editar_textView_aviso_senha,usuario_editar_textView_aviso_senha_repetida;
    private ProgressBar usuario_editar_progress_nome,usuario_editar_progress_email,usuario_editar_progress_senha,usuario_editar_progress_senha_rapita;
    private Usuario usuarioLogado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.usuario_editar);

        Bundle b = getIntent().getExtras();
        long id_usuario_lista = -1; // or other values
        if(b != null){
            id_usuario_lista = b.getLong("id");
        }

        Log.d("activity","Valor do id recebido: "+ id_usuario_lista);

        identificandoComponentes();
        esconderAvisos();
        usuarioLogado = Usuario.verificaUsuarioLogado();
        Usuario.buscarUsuarioPeloId(UsuarioDetalheActivity.this,usuarioLogado.getKey(),id_usuario_lista);

    }



    private void identificandoComponentes() {

        //-------------------IDENTIFICANDO OS COMPONENTES EM "login.xml"----------//
        this.usuario_editar_editText_usuario = (EditText) findViewById(R.id.usuario_editar_editText_usuario);
        this.usuario_editar_editText_email = (EditText) findViewById(R.id.usuario_editar_editText_email);
        this.usuario_editar_editText_senha = (EditText) findViewById(R.id.usuario_editar_editText_senha);
        this.usuario_editar_editText_senha_repita = (EditText) findViewById(R.id.usuario_editar_editText_senha_repita);

        this.usuario_editar_button_usuario = (Button) findViewById(R.id.usuario_editar_button_atualizar);

        this.usuario_editar_textView_aviso_email = (TextView) findViewById(R.id.usuario_editar_textView_aviso_email);

        this.usuario_editar_textView_aviso_senha = (TextView) findViewById(R.id.usuario_editar_textView_aviso_senha);
        this.usuario_editar_textView_aviso_senha_repetida = (TextView) findViewById(R.id.usuario_editar_textView_aviso_senha_repetida);


        this.usuario_editar_progress_nome = (ProgressBar) findViewById(R.id.usuario_editar_progress_nome);

        this.usuario_editar_progress_email = (ProgressBar) findViewById(R.id.usuario_editar_progress_email);

        this.usuario_editar_progress_senha = (ProgressBar) findViewById(R.id.usuario_editar_progress_senha);

        this.usuario_editar_progress_senha_rapita = (ProgressBar) findViewById(R.id.usuario_editar_progress_senha_rapita);

        //----------------------------------------------------------------------------------//
    }

    public void inicializandoComponentes(Usuario usuario) {
        esconderProgressBar();
        usuario_editar_editText_usuario.setText(usuario.getNome());
        usuario_editar_editText_email.setText(usuario.getEmail());

        usuario_editar_button_usuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarProgressBar();
                String nome = usuario_editar_editText_usuario.getText().toString();
                String email = usuario_editar_editText_email.getText().toString();
                String senha = usuario_editar_editText_senha.getText().toString();
                String senha_repetida = usuario_editar_editText_senha_repita.getText().toString();

                if(verfificaSenhas(senha,senha_repetida)){
                    usuario.setNome(nome);
                    usuario.setEmail(email);
                    usuario.setContext(UsuarioDetalheActivity.this);
                    usuario.setKey(usuarioLogado.getKey());
                    usuario.setPassword(senha);
                    usuario.editarUsuario();
                }
            }
        });
    }

    public void mostrarProgressBar() {
        usuario_editar_progress_nome.setVisibility(View.VISIBLE);
        usuario_editar_progress_email.setVisibility(View.VISIBLE);
        usuario_editar_progress_senha.setVisibility(View.VISIBLE);
        usuario_editar_progress_senha_rapita.setVisibility(View.VISIBLE);
    }

    public void esconderProgressBar() {
        usuario_editar_progress_nome.setVisibility(View.GONE);
        usuario_editar_progress_email.setVisibility(View.GONE);
        usuario_editar_progress_senha.setVisibility(View.GONE);
        usuario_editar_progress_senha_rapita.setVisibility(View.GONE);
    }

    public void esconderAvisos() {
        usuario_editar_textView_aviso_email.setVisibility(View.GONE);
        usuario_editar_textView_aviso_senha.setVisibility(View.GONE);
        usuario_editar_textView_aviso_senha_repetida.setVisibility(View.GONE);

    }

    public void mostrarAvisoEmail(String aviso){
        this.usuario_editar_textView_aviso_email.setVisibility(View.VISIBLE);
        this.usuario_editar_textView_aviso_email.setText(aviso);
    }

    public boolean verfificaSenhas(String senha, String senha_repetida){


        if(!senha.equals(senha_repetida)){
            this.usuario_editar_textView_aviso_senha.setVisibility(View.VISIBLE);
            this.usuario_editar_textView_aviso_senha_repetida.setText(SENHAS_DISTINTAS);
            return false;
        }
        this.usuario_editar_textView_aviso_senha_repetida.setVisibility(View.GONE);
        return true;

    }
}