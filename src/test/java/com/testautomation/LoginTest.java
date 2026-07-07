package com.testautomation;

import static org.junit.Assert.assertTrue;

import java.time.Duration;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginTest {

    @Test
    public void shouldLoginSuccessfully() throws InterruptedException {

        WebDriver driver = new ChromeDriver();

        try {
            driver.manage().window().maximize();
            driver.get("https://www.saucedemo.com/");

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            // Kullanıcı adı alanını ID locator ile bul ve doldur.
            wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.id("user-name")))
                    .sendKeys("standard_user");

            // Parola alanını ID locator ile bul ve doldur.
            driver.findElement(By.id("password"))
                    .sendKeys("secret_sauce");

            // Login butonunun tıklanabilir olmasını bekle ve tıkla.
            wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.id("login-button")))
                    .click();

            // Başarılı giriş sonrasındaki URL'yi bekle.
            wait.until(
                    ExpectedConditions.urlContains("inventory.html"));

            // Login işleminin başarılı olduğunu doğrula.
            assertTrue(
                    "Login sonrası inventory sayfası açılmadı.",
                    driver.getCurrentUrl().contains("inventory.html"));

            // Sonuç ekranını görebilmek için geçici bekleme.
            Thread.sleep(5000);

        } finally {
            driver.quit();
        }
    }
}