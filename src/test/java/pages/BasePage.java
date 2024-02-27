package pages;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BasePage {
     /*
     * Declaración de una variable estática 'driver' de tipo WebDriver
     * Esta variable va a ser compartida por todas las instancias de BasePage y sus subclases
     */
    protected static WebDriver driver;
    /*
     * Declaración de una variable de instancia 'wait' de tipo WebDriverWait.
     * Se inicializa inmediatamente con una instancia dew WebDriverWait utilizando el 'driver' estático
     * WebDriverWait se usa para poner esperas explícitas en los elementos web
     */
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
 
    /* 
     * Configura el WebDriver para Chrome usando WebDriverManager.
     * WebDriverManager va a estar descargando y configurando automáticamente el driver del navegador
    */
    
    
    static {
        WebDriverManager.chromedriver().setup();
 
        //Inicializa la variable estática 'driver' con una instancia de ChromeDriver
        driver = new ChromeDriver();
    }
    
    private static Actions action;
    /*
     * Este es el constructor de BasePage que acepta un objeto WebDriver como argumento.
     */
    public BasePage(WebDriver driver) {
        BasePage.driver = driver;
    }
 
    //Método estático para navegar a una URL.
    public static void navigateTo(String url) {
        driver.get(url);
    }

     //Método estático para cerrar la instancia del driver. 
     public static void closeBrowser() {
        driver.quit();
    }
    
     // Encuentra y devuelve un WebElement en la página utilizando un locator XPath, esperando a que esté presentente en el DOM
    private WebElement Find(String locator){
        return wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(locator)));
    }
    
    //Hacer clic en un WebElement
    public void clickElement(String locator){
        Find(locator).click();
    }
    
     //Escribir en un WebElement
    public void write(String locator, String keysToSend){
        Find(locator).clear();
        Find(locator).sendKeys(keysToSend);
    }

     //Seleccionar un elemento desde un combo por algun valor del combo
     public void selectFromDropdownByValue(String locator, String value){
        Select dropdown = new Select(Find(locator));
 
        dropdown.selectByValue(value);
    }
 
    //Seleccionar un elemento desde un combo por algun id del combo
    public void selectFromDropdownByIndex(String locator, Integer index){
        Select dropdown = new Select(Find(locator));
 
        dropdown.selectByIndex(index);
    }
 
    //Ver el tamaño que tiene el combo
    public int dropdownSize(String locator){
        Select dropdown = new Select(Find(locator));
 
        List<WebElement> dropdownOptions = dropdown.getOptions();
 
        return dropdownOptions.size();
    }

    //Obtener los valores de un combo
    public List<String> getDropdownValues(String locator) {
        Select dropdown = new Select(Find(locator));
 
        List<WebElement> dropdownOptions = dropdown.getOptions();
        List<String> values = new ArrayList<>();
        for (WebElement option : dropdownOptions) {
            values.add(option.getText());
        }
 
        return values;
 
    }
    
    //Pasar el mouse por un elemento
    public void hoverOverElement(String locator){
        action.moveToElement(Find(locator));
    }
    
    //Hacer doble clic en un elemento
    public void doubleClick(String locator){
        action.doubleClick(Find(locator));
    }

    //Hacer clic derecho en un elemento
    public void rightClick(String locator){
        action.contextClick(Find(locator));
    }
    
    //devolver el valor de una tabla de una celda especifica
    public String getValueFromTable(String locator, int row, int column){
        String cellNeed=locator+"/table/tbody/tr["+row+"]/td["+column+"]";
        return Find(cellNeed).getText();
    }

    //Se esta enviando un texto a la tabla
    public void setValueOnTable(String locator, int row, int column,String StringtoSend){
        String cellToFill=locator+"/table/tbody/tr["+row+"]/td["+column+"]";
        Find(cellToFill).sendKeys(StringtoSend);
    }

    //Para identificar el elemento para cargar algun archivo desde el explorador
    public void verTypeFile(){
       String change_visibility = "$(\"#fileField\").css(\"visibility,\"visible\");";
       String change_display = "$(\"#fileField\").css(\"display,\"block\");";
       String width = "$(\"#fileField\").css(\"width,\"200px\");";
       String height = "$(\"#fileField\").css(\"height,\"200px\");";
       String position = "$(\"#fileField\").css(\"position,\"fixed\");";
       String overflow = "$(\"#fileField\").css(\"overflow,\"visible\");";
       String zIndex = "$(\"#fileField\").css(\"zIndex,\"999999\");";
       String top = "$(\"#fileField\").css(\"top,\"500px\");";
       String bottom = "$(\"#fileField\").css(\"bottom,\"500px\");";
       String left = "$(\"#fileField\").css(\"left,\"500px\");";
       String right = "$(\"#fileField\").css(\"right,\"500px\");";
       String marginBottom = "$(\"#fileField\").css(\"marginBottom,\"100px\");";
       JavascriptExecutor js = (JavascriptExecutor) driver;
       js.executeScript(change_display);
       js.executeScript(change_visibility);
       js.executeScript(width);
       js.executeScript(height);
       js.executeScript(position);
       js.executeScript(overflow);
       js.executeScript(zIndex);
       js.executeScript(top);
       js.executeScript(bottom);
       js.executeScript(left);
       js.executeScript(right);
       js.executeScript(marginBottom);
      
    }

    //Para manejar los popups
    public void switchToiFrame(String iFrameID){
	    driver.switchTo().frame(iFrameID);
    }

    //Para manejar los popups
    public void switchToParentFrame(){
	    driver.switchTo().parentFrame();
    }

    //Para manejar las alertas
    public void disseisAlert(){
	    driver.switchTo().alert().dismiss();;
    }
 
}
