package com.projeto_padrao.activities;

import androidx.test.espresso.ViewInteraction;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;
import com.projeto_padrao.R;
import com.projeto_padrao.models.Aplicacao;
import com.projeto_padrao.models.Usuario;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static com.projeto_padrao.Configuracao.childAtPosition;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class LogarTest {

    @Rule
    public ActivityTestRule<StartActivity> mActivityTestRule = new ActivityTestRule<>(StartActivity.class);

    @Test
    public void logarTest() {

        Aplicacao.aguardar(2000);

        if(Usuario.verificaUsuarioLogado()==null) {

            ViewInteraction appCompatEditText = onView(
                    allOf(withId(R.id.login_editText_email),
                            childAtPosition(
                                    allOf(withId(R.id.cronstrain),
                                            childAtPosition(
                                                    withClassName(is("androidx.core.widget.NestedScrollView")),
                                                    0)),
                                    2),
                            isDisplayed()));
            appCompatEditText.perform(replaceText("pedroh.mix@gmail.com"), closeSoftKeyboard());

            ViewInteraction appCompatEditText2 = onView(
                    allOf(withId(R.id.login_editText_senha),
                            childAtPosition(
                                    allOf(withId(R.id.cronstrain),
                                            childAtPosition(
                                                    withClassName(is("androidx.core.widget.NestedScrollView")),
                                                    0)),
                                    3),
                            isDisplayed()));
            appCompatEditText2.perform(replaceText("123456"), closeSoftKeyboard());

            ViewInteraction appCompatButton = onView(
                    allOf(withId(R.id.login_button_login), withText("Login"),
                            childAtPosition(
                                    allOf(withId(R.id.cronstrain),
                                            childAtPosition(
                                                    withClassName(is("androidx.core.widget.NestedScrollView")),
                                                    0)),
                                    12),
                            isDisplayed()));
            appCompatButton.perform(click());
            Aplicacao.aguardar(3000);

        }

    }

}
