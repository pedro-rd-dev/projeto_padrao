package com.projeto_padrao.models;

import com.projeto_padrao.api.retrofit.RetrofitConfig;

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
public class AgendamentoTest {

    //private static Usuario usuarioTeste = new Usuario("Yan","YSM@gmail.com","123456");
    private static Usuario usuarioTeste = new Usuario("pedroh.mix@gmail.com","123456");
    //private static Usuario usuarioTeste = new Usuario("Y1","yteste@gmail.com","123456");
    // private static Usuario usuarioTeste = new Usuario("Y2","yteste@gmail.com","123456");
    // private static Usuario usuarioTeste = new Usuario("Y3","yteste@gmail.com","123456");
    // private static Usuario usuarioTeste = new Usuario("Y4","yteste@gmail.com","123456");

    private static Agendamento agendamento = new Agendamento();

    /*
    LoginActivityTest loginActivityTest = new LoginActivityTest();
        loginActivityTest.loginActivityTest();
     */



    @Test
    public void A_listarAgendTest() {


        RetrofitCallTest retrofitCallTest = new RetrofitCallTest();
        usuarioTeste = retrofitCallTest.B_login_Success();


        Call<List<Agendamento>> call = new RetrofitConfig().setAgendService().listarAgendRemoto("Token " +usuarioTeste.getKey());

        try {
            //Magic is here at .execute() instead of .enqueue()
            Response<List<Agendamento>> response = call.execute();
            List<Agendamento> agendamentos = response.body();

            agendamento = agendamentos.get(0);

            if (response.isSuccessful()){
                assertNotNull(agendamentos);
            }else {
                fail();
            }


        } catch (IOException e) {
            fail();
            e.printStackTrace();
        }

    }

    @Test
    public void B_editarAgendamento(){


        agendamento.setUsuario(9L);

        Call<Agendamento> call = new RetrofitConfig().setAgendService().addUserAgend("Token "+usuarioTeste.getKey(),agendamento.getId(),agendamento);
        try {
            Response<Agendamento> response = call.execute();
            Agendamento agendamentos = response.body();


            if(response.isSuccessful()){
                assertNotNull(agendamentos);
            }else{
                fail();
            }
        }catch (IOException e){
            fail();
            e.printStackTrace();
        }

    }

    @Test
    public void C_listarAgenduserTest(){


        Call<List<Agendamento>> call = new RetrofitConfig().setAgendService().listarAgendporUser("Token " + usuarioTeste.getKey());

        try {
            Response<List<Agendamento>> response = call.execute();
            List<Agendamento> agendamentos = response.body();



            if(response.isSuccessful()){
                assertNotNull(agendamentos);
            }else{
                fail();
            }
        }catch (IOException e){
            fail();
            e.printStackTrace();
        }

    }

    @Test
    public void D_editMeuAgendamento(){

        agendamento.setUsuario(null);
        Call<Agendamento> call = new RetrofitConfig().setAgendService().removerUserAgend("Token "+usuarioTeste.getKey(),agendamento.getId(),agendamento);

        try {
            Response<Agendamento> response = call.execute();
            Agendamento agendamentos = response.body();

            if(response.isSuccessful()){
                assertNotNull(agendamentos);
            }else{
                fail();
            }
        }catch (IOException e){
            fail();
            e.printStackTrace();
        }

    }

}
