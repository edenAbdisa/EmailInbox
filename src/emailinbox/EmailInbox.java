/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package emailinbox;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
/**
 *
 * @author Eden
 */
public class EmailInbox {
    public static void main(String[] args) throws InterruptedException, IOException { 
        System.setProperty("webdriver.chrome.driver","C:\\chromedriver.exe");

        DesiredCapabilities desiredCapabilities = DesiredCapabilities.chrome();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("test-type");
        options.addArguments("--start-maximized");
        options.addArguments("--disable-web-security");
        options.addArguments("--allow-running-insecure-content");
        desiredCapabilities.setCapability("chrome.binary","./src//lib//chromedriver");
        desiredCapabilities.setCapability(ChromeOptions.CAPABILITY, options);
        desiredCapabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
        
        WebDriver webDriver=new ChromeDriver(desiredCapabilities);
   
        webDriver.navigate().to("https://mail.google.com"); 
        webDriver.findElement(By.id("identifierId")).sendKeys("edenabdisa90@gmail.com",Keys.ENTER);

        Thread.sleep(3000);

        webDriver.findElement(By.name("password")).sendKeys("user password",Keys.ENTER);

        Thread.sleep(4000);

        List<WebElement> listUnreadEmail = webDriver.findElements(By.className("zE"));

        File file = new File("ListUnreadEmail.txt");
        try (FileWriter fileWriter = new FileWriter(file)) {
            for (WebElement unreadEmail:listUnreadEmail) {
                
                String sender = unreadEmail.findElement(By.className("yW")).getText();
                String subject = unreadEmail.findElement(By.className("y6")).getText();                
                
                fileWriter.write("Sender:- ");
                fileWriter.write(sender);
                fileWriter.write(" Subject:- ");
                fileWriter.write(subject);
                fileWriter.write("\n");
            }
            
            fileWriter.flush();
        }
        Thread.sleep(7000);
        webDriver.quit();
    }
}
  

