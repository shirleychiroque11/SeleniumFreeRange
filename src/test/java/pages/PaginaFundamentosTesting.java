package pages;

public class PaginaFundamentosTesting extends BasePage {
 
    private String clickIntroduccionTestingLink = "/html[1]/body[1]/div[1]/div[4]/div[1]/section[1]/div[1]/div[1]/div[1]/div[1]/div[3]/section[1]/div[2]/div[1]/header[1]/a[1]/h2[1]";
    
    public PaginaFundamentosTesting() {
        super(driver);
    }
 
    public void clickIntroduccionTestingLink() {
        clickElement(clickIntroduccionTestingLink);
    }
 
}