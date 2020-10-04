package com.projeto_padrao;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.projeto_padrao.models.Android;
import com.projeto_padrao.models.Usuario;
import com.projeto_padrao.observers.GpsObserver;

import static com.projeto_padrao.statics.ConstantesGlobais.NOME_APP;

public class MainActivity extends AppCompatActivity {

    //DECLARANDO OBJETOS
    EditText login_editText_usuario,login_editText_senha;
    Button login_button_usuario;
    TextView login_text_registrar;
    
    String nomeCampo1,nomeCampo2;
    private String nomeCampo3, nomeCampo4;
    private static String nomeCampo5;
    private static final String nomeCampo6 = "Esta constante não muda";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_sem_vetor);
        
        identificandoComponentes();
        inicializandoComponentes();

    }

    private void identificandoComponentes() {

        //-------------------IDENTIFICANDO OS COMPONENTES EM "login_sem_vetor.xml"----------//
        login_editText_usuario = (EditText) findViewById(R.id.login_editText_usuario);
        login_editText_senha = (EditText) findViewById(R.id.login_editText_senha);
        login_button_usuario = (Button) findViewById(R.id.login_button_login);
        login_text_registrar = (TextView) findViewById(R.id.login_text_registrar);
        //----------------------------------------------------------------------------------//
    }

    private void inicializandoComponentes() {
        //-----------------------------CRIANDO AÇÃO NO BOTÃO--------------------------------//

        login_button_usuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usuario = login_editText_usuario.getText().toString();
                String senha = login_editText_senha.getText().toString();

                Usuario usuarioLogado = new Usuario(usuario,senha);
                usuarioLogado.logar(MainActivity.this);
            }
        });

        //-------------------------------------------------------------------------------------///
    }

    @Override
    protected void onStart() {
        Log.d("clico_de_vida", "onStart - O codigo da atividade Começou a ser executado");
        super.onStart();
    }
    @Override
    protected void onResume() {


        Log.d("clico_de_vida", "onResume -Estado de interação com a tela");
        super.onResume();

    }
    @Override
    protected void onPause() {
        Log.d("clico_de_vida", "onPause - iniciou o término da activity");
        super.onPause();
    }
    @Override
    protected void onStop() {
        Log.d("clico_de_vida", "onStop - A atividade não está mais visivel ao usuario");
        super.onStop();
    }
    @Override
    protected void onDestroy() {
        Log.d("clico_de_vida", "onDestroy- A Activity foi completamente destruida" );
        super.onDestroy();
    }
}