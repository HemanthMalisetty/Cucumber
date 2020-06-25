package stepDefinition;

import cucumber.api.PendingException;
import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;

import java.util.concurrent.TimeUnit;

public class StepDefinition {
    String student_type = "";
    String no_of_rooms = "";
    WebDriver driver;

    @Given("^Browser is launched and user is on application page$")
    public void browser_is_launched_and_user_is_on_application_page(){
        // Write code here that turns the phrase above into concrete actions
        //System.setProperty("webdriver.gecko.driver", "/usr/bin/geckodriver");
        WebDriverManager.firefoxdriver().setup();
        FirefoxBinary firefoxBinary = new FirefoxBinary();
        firefoxBinary.addCommandLineOptions("--headless");
        FirefoxProfile profile=new FirefoxProfile();
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.setBinary(firefoxBinary);
        firefoxOptions.setProfile(profile);
        driver=new FirefoxDriver(firefoxOptions);
        driver.get("https://urldefense.com/v3/__http://webapps.tekstac.com/CucumberHostelFeeCalc/__;!!PIZeeW5wscynRQ!68D4qWWWW3_8w-w-NpYxItoCqTaVK6DHee4X1qmwEGMGXMJQPyKsDDD_fWZzyyToLZhcvpEp-g$ ");
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }

    @When("^User Enters student name, \"([^\"]*)\", \"([^\"]*)\"$")
    public void user_Enters_student_name(String student_type, String no_of_rooms) {
        // Write code here that turns the phrase above into concrete actions
        if (student_type.equals("Day Scholar")){
            driver.findElement(By.xpath("//*[@name='type' and @value = 'day-scholar']")).click();
        }else if(student_type.equals("Hosteller")){
            driver.findElement(By.xpath("//*[@name='type' and @value = 'hosteller']")).click();
        }
        driver.findElement(By.name("number")).sendKeys(no_of_rooms);
    }

    @When("^User clicks on the calculate fee button$")
    public void user_clicks_on_the_calculate_fee_button() {
        // Write code here that turns the phrase above into concrete actions
        driver.findElement(By.name("getFee")).click();
    }

    @Then("^\"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\" should be correctly displayed$")
    public void should_be_correctly_displayed(String college_fee, String hostel_fee, String additional_fee,String total_fee) {
        // Write code here that turns the phrase above into concrete actions
        String collegeFee = driver.findElement(By.xpath("//*[@id = 'feeTable']//td[2]")).getText();
        String hostelfee = driver.findElement(By.xpath("//*[@id='feeTable']//tr[2]/td[2]")).getText();
        String additionalfee = driver.findElement(By.xpath("//*[@id='feeTable']//tr[3]/td[2]")).getText();
        String totalfee = driver.findElement(By.xpath("//*[@id='feeTable']//tr[4]/td[2]")).getText();

        if(college_fee.equals(collegeFee) && hostel_fee.equals(hostelfee) && additional_fee.equals(additionalfee) && total_fee.equals(totalfee)){
            System.out.println("The collegeFee,hostelfee,additionalfee and totalfee are displayed.");
        }
    }
    @After
    public void tearDown() {
        driver.quit();
    }

}
