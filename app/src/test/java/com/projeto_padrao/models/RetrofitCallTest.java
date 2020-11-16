package com.projeto_padrao.models;


import com.projeto_padrao.api.retrofit.RetrofitConfig;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import java.io.IOException;
import retrofit2.Call;
import retrofit2.Response;

import static org.junit.Assert.assertTrue;

@RunWith(RobolectricTestRunner.class)
public class RetrofitCallTest {

    Usuario usuario = new Usuario("pedroh.mix@gmail.com","123456");

    @Test
    public void login_Success() {

        Call<Usuario> call = new RetrofitConfig().setUserService().logar(usuario);

        try {
            //Magic is here at .execute() instead of .enqueue()
            Response<Usuario> response = call.execute();
            Usuario authResponse = response.body();

            assertTrue(response.isSuccessful() && !authResponse.getKey().isEmpty());

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}