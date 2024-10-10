import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import utils.Utils;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;


import static org.junit.jupiter.api.Assertions.assertEquals;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)

public class FormAutomation {
    WebDriver driver;

//setup


    @BeforeAll
    public void setup(){
        driver = new EdgeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }


    //Input Field Automation-practice-form
    @Test
    public void formAutomation() throws InterruptedException {
        driver.get("https://www.digitalunite.com/practice-webform-learners");
        driver.findElement(By.id("edit-name")).sendKeys("Shorna");


        driver.findElement(By.id("edit-number")).sendKeys("01732458825");
        driver.findElement(By.id("edit-date")).sendKeys("10/06/2024");
        driver.findElement(By.id("edit-email")).sendKeys("shorna23@gmail.com");
        driver.findElement(By.id("edit-tell-us-a-bit-about-yourself-")).sendKeys("Hello,I am a SQA Engineer");


        String relativePath = "\\src\\test\\resources\\postman-pass.png";
        String absolutePath = System.getProperty("user.dir")+relativePath;

        Utils.scroll(driver,500);
        System.out.println(absolutePath );

        driver.findElement(By.id("edit-uploadocument-upload")).sendKeys( absolutePath);
        Thread.sleep(5000);

             driver.findElement(By.id("edit-age")).click();
        driver.findElement(By.id("edit-submit")).click();




//        List <WebElement> checkBox = driver.findElements(By.cssSelector("[type = checkbox]"));
//        Actions check = new Actions(driver);
//        check.click(checkBox.get(0)).perform();

//      List <WebElement> submitElem = driver.findElements(By.cssSelector("[type = submit]"));
//        Actions actions = new Actions(driver);
//        actions.click(submitElem.get(1)).perform();




       //Assertion
      WebElement successMessage =  driver.findElement(By.id("block-pagetitle-2"));
       String actualMessage = successMessage.getText();
        String expectedMessage = "Thank you for your submission!";
        assertEquals(expectedMessage,actualMessage );


    }



    @AfterAll
    public void finishTest(){

        driver.quit();
    }



}




