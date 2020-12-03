package com.projeto_padrao.models;

import com.projeto_padrao.api.retrofit.RetrofitConfig;
import com.projeto_padrao.models.remedio.Recomendacao;
import com.projeto_padrao.models.remedio.Remedio;

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
public class RemedioTest {
    private static Usuario usuarioteste = new Usuario("pedroh.mix@gmail.com","123456");
    private static Remedio remedio = new Remedio();
    private static Recomendacao recomendacao = new Recomendacao();



    @Test
    public void A_listarRemedio(){

        RetrofitCallTest retrofitCallTest = new RetrofitCallTest();
        usuarioteste = retrofitCallTest.B_login_Success();

        Call<List<Remedio>> call = new RetrofitConfig().setRemedioService().listarRemedio("Token " + usuarioteste.getKey());
        try {
            //Magic is here at .execute() instead of .enqueue()
            Response<List<Remedio>> response = call.execute();
            List<Remedio> remedios = response.body();

            if (response.isSuccessful()){
                assertNotNull(remedios);
            }else {
                fail();
            }


        } catch (IOException e) {
            fail();
            e.printStackTrace();
        }

    }

    @Test
    public void B_listarRecomendação(){

        Call<List<Recomendacao>> call = new RetrofitConfig().setRecomendacaoService().listarRecomendacao("Token " + usuarioteste.getKey());
        try {
            //Magic is here at .execute() instead of .enqueue()
            Response<List<Recomendacao>> response = call.execute();
            List<Recomendacao> recomendacaos = response.body();

            recomendacao = recomendacaos.get(0);

            if (response.isSuccessful()){
                assertNotNull(recomendacaos);
            }else {
                fail();
            }


        } catch (IOException e) {
            fail();
            e.printStackTrace();
        }

    }
    @Test
    public void C_editarRecomendaçao(){
        recomendacao.setIntervalo(8);
        Call<Recomendacao> call = new RetrofitConfig().setRecomendacaoService().editarRecomendacao("Token " + usuarioteste.getKey(), recomendacao.getId(), recomendacao);
        try {
            Response<Recomendacao> response = call.execute();
            Recomendacao recomendacao = response.body();


            if(response.isSuccessful()){
                assertNotNull(recomendacao);
            }else{
                fail();
            }
        }catch (IOException e){
            fail();
            e.printStackTrace();
        }
    }
}
