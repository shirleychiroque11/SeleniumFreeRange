package steps;

import java.util.List;

import org.testng.Assert;

import java.util.Arrays;

import io.cucumber.java.en.*;
import pages.PaginaPrincipal;
import pages.PaginaCursos;
import pages.PaginaFundamentosTesting;
import pages.PaginaRegistro;
import org.testng.asserts.SoftAssert;


public class FreeRangeSteps {
    PaginaPrincipal landingPage = new PaginaPrincipal();
    PaginaCursos cursosPage = new PaginaCursos();
    PaginaFundamentosTesting fundamentosPage = new PaginaFundamentosTesting();
    PaginaRegistro registro = new PaginaRegistro();
    SoftAssert soft = new SoftAssert();//Para ejecutar varios aserciones a la vez sin detener la ejecucion y al final colocar soft.assertAll();
            
    @Given("I navigate to www.freerangetesters.com")
    public void iNavigateToFRT() {
        landingPage.navigateToFreeRangeTesters();
    }

    @When("I go to {word} using the navigation bar")
    public void navigationBarUse(String section) {
        landingPage.clickOnSectionNavigationBar(section);
    }

    @When("I select Elegir Plan")
    public void selectElegirPlan() {
        landingPage.clickOnElegirPlanButton();
    }
    
    @And("select Introduccion al Testing")
    public void navigateToIntro() {
        cursosPage.clickFundamentosTestingLink();
        fundamentosPage.clickIntroduccionTestingLink();
    }

    @Then("I can validate the options in the checkout page")
    public void validateCheckoutPlans() {
        List<String> lista = registro.returnPlanDropdownValues();
       // System.out.println("Elementos que retorna"+lista);
        List<String> listaEsperada = Arrays.asList("Academia: $16.99 / mes • 11 productos","Academia: $176 / año • 11 productos","Free: Gratis • 1 producto");
        //System.out.println("Elementos para validar"+listaEsperada);
        Assert.assertEquals(listaEsperada, lista);
    }

}