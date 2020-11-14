package com.projeto_padrao.models;

import com.projeto_padrao.BuildConfig;
import com.projeto_padrao.activities.StartActivity;
import com.projeto_padrao.api.retrofit.RetrofitConfig;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.util.ActivityController;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
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