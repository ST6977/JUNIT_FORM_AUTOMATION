import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Utils;

import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class RegistrationAutomation {
    WebDriver driver;


    @BeforeAll
    public void setup(){
       driver = new EdgeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }



    @Test
    public void registrationFormAutomation() throws InterruptedException {
        driver.get("https://demo.wpeverest.com/user-registration/guest-registration-form/");
        driver.findElement(By.id("first_name")).sendKeys("Jihan");
        driver.findElement(By.id("last_name")).sendKeys("Sarwar");
        driver.findElement(By.id("user_email")).sendKeys("jihan@gmail.com");
        driver.findElement(By.id("radio_1665627729_Female")).click();
        driver.findElement(By.id("user_pass")).sendKeys("EMRAN@***&&&&");

        Utils.scroll(driver,500);



        //Nationality
        driver.findElement(By.id("input_box_1665629217")).sendKeys("Bangladeshi");

        //phonenumber
        List<WebElement> addressElem = driver.findElements(By.id("phone_1665627880"));
        addressElem.get(1).sendKeys("01754678709");


        //datepicker
        JavascriptExecutor js = (JavascriptExecutor) driver;

       // Wait for the element to be present
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement dateInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@data-id='date_box_1665628538' and @type='text']")));

         // Set the date value
        js.executeScript("arguments[0].value = '2024-10-14';", dateInput);



        // select country
        Select countryDropdown = new Select(driver.findElement(By.id("country_1665629257"))); // Replace with actual ID or locator

        // Select Bangladesh by visible text
        countryDropdown.selectByVisibleText("Bangladesh");
        countryDropdown.selectByValue("BD");


        //Terms and condition
        driver.findElement(By.id("privacy_policy_1665633140")).click();




            //button
        WebElement submitButton = driver.findElement(By.cssSelector("button[type='submit']"));
        submitButton.click();

        Thread.sleep(5000);


        WebElement successMessage =  driver.findElement(By.id("ur-submit-message-node"));
        String actualMessage = successMessage.getText();
        String expectedMessage = "User successfully registered.";
        assertEquals(expectedMessage,actualMessage );



    }


   @AfterAll
    public void finishTest(){

        driver.quit();
    }
}
