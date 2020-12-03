package com.projeto_padrao.models;


import com.projeto_padrao.api.retrofit.RetrofitConfig;
import com.projeto_padrao.models.chamados.Chamado;
import com.projeto_padrao.models.chamados.Impressora;
import com.projeto_padrao.models.eventos.Favorito;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ChamadoTest {

    private static Usuario usuarioteste = new Usuario("pedroh.mix@gmail.com","123456");
    private static Chamado chamado = new Chamado();
    private static Impressora impressora=new Impressora();


    @Test
    public void A_ListarChamadosTest(){
        RetrofitCallTest retrofitCallTest = new RetrofitCallTest();
        usuarioteste = retrofitCallTest.B_login_Success();

        Call<List<Chamado>> call = new RetrofitConfig().setChamadoService().listarChamados("Token " + usuarioteste.getKey());
        try {
            Response<List<Chamado>> response = call.execute();
            List<Chamado> chamados = response.body();



            if(response.isSuccessful()){
                assertNotNull(chamados);
            }else{
                fail();
            }
        }catch (IOException e){
            fail();
            e.printStackTrace();
        }

    }
    @Test
    public void B_ListarImpressoraTest(){
        Call<List<Impressora>> call = new RetrofitConfig().setImpressoraService().listarImpressoras("Token "+usuarioteste.getKey());
        try {
            Response<List<Impressora>> response = call.execute();
            List<Impressora> impressoras  = response.body();



            if(response.isSuccessful()){
                assertNotNull(impressoras);
            }else{
                fail();
            }
        }catch (IOException e){
            fail();
            e.printStackTrace();
        }

    }
}
