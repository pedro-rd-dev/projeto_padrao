package com.projeto_padrao.models;

import com.projeto_padrao.api.retrofit.RetrofitConfig;
import com.projeto_padrao.models.eventos.Evento;
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
public class EventoTest {

    private static Usuario usuarioTeste = new Usuario("pedro","pedroh.mix@gmail.com","123456");
    private static Favorito favorito = new Favorito();
    private static Evento evento = new Evento();

    @Test
    public void A_listarEventosTest() {

        RetrofitCallTest retrofitCallTest = new RetrofitCallTest();
        usuarioTeste = retrofitCallTest.B_login_Success();

        Call<List<Evento>> call = new RetrofitConfig().setEventoService().listarEventos("Token " + usuarioTeste.getKey());

        try {
            //Magic is here at .execute() instead of .enqueue()
            Response<List<Evento>> response = call.execute();
            List<Evento> eventos = response.body();

            evento = eventos.get(0);

            if (response.isSuccessful()){
                assertNotNull(eventos);
            }else {
                fail();
            }


        } catch (IOException e) {
            fail();
            e.printStackTrace();
        }

    }

    @Test
    public void B_adicionarFavoritoTest(){


        favorito.setUsuario(1L);

        Call<Favorito> call = new RetrofitConfig().setEventoService().adicionarFavoritos("Token "+usuarioTeste.getKey(),favorito);
        try {
            Response<Favorito> response = call.execute();
            Favorito favorito = response.body();


            if(response.isSuccessful()){
                assertNotNull(favorito);
            }else{
                fail();
            }
        }catch (IOException e){
            fail();
            e.printStackTrace();
        }

    }

    @Test
    public void C_listarFavoritoTest(){


        Call<List<Favorito>> call = new RetrofitConfig().setEventoService().listarFavoritos("Token " + usuarioTeste.getKey());

        try {
            Response<List<Favorito>> response = call.execute();
            List<Favorito> favoritos = response.body();



            if(response.isSuccessful()){
                assertNotNull(favoritos);
            }else{
                fail();
            }
        }catch (IOException e){
            fail();
            e.printStackTrace();
        }

    }

    @Test
    public void D_removerFavoritoTest(){
        Call<Favorito> call = new RetrofitConfig().setEventoService().deletarFavorito("Token "+usuarioTeste.getKey(), favorito.getId());

        try {
            Response<Favorito> response = call.execute();
            Favorito favorito = response.body();

            if(response.isSuccessful()){
                assertNotNull(favorito);
            }else{
                fail();
            }
        }catch (IOException e){
            fail();
            e.printStackTrace();
        }

    }
}
