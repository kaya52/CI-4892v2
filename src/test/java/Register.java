import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.awt.*;
import java.net.MalformedURLException;
import java.net.URL;

public class Register {
    DesiredCapabilities desiredcapabilities = new DesiredCapabilities();
    AndroidDriver<MobileElement> androiddriver;
    WebDriverWait wait;
    @BeforeMethod()//BeforeMethod ile her test case i koşulmadan önce çalışmaktadır.
    public void setUp() throws MalformedURLException {
        System.out.println("Before içerisinde");
        FileDialog dialog = new FileDialog((Frame)null, "Select File to Open");
        dialog.setMode(FileDialog.LOAD);
        dialog.setVisible(true);
        String file = dialog.getDirectory() + dialog.getFile();
        desiredcapabilities.setCapability("deviceName", "Z40");
        desiredcapabilities.setCapability("udid", "150139381909232003");
        desiredcapabilities.setCapability("platformName", "Android");
        desiredcapabilities.setCapability(MobileCapabilityType.APP, file);
        desiredcapabilities.setCapability("appPackage", "com.chat.android");
        desiredcapabilities.setCapability("appActivity", "com.chat.android.membership.SplashScreen");
        androiddriver = new AndroidDriver<>(new URL("http://0.0.0.0:" + 4723 + "/wd/hub"), desiredcapabilities);
        wait = new WebDriverWait(androiddriver, 10);
    }
    @AfterMethod //AfterMethod ile her test casei koşulduktan sonra çalışır.
    public void tearDown() {
        System.out.println("After içerisinde");
        androiddriver.quit();
    }
    @Test //Loglu versiyon değil ise "Loglu versiyon değil" yazan, Loglu versiyon ise Logun durumunu yazan kısım
    public void LogsController() throws InterruptedException {
        try {
            androiddriver.switchTo().alert().accept();
            androiddriver.switchTo().alert().accept();
            androiddriver.switchTo().alert().accept();
        } catch (Exception e) {
        }
        Thread.sleep(2000);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.chat.android:id/phone"))).sendKeys("5527158688");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.chat.android:id/okButton"))).click();
        WebElement el7 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.chat.android:id/smsCode")));
        Thread.sleep(1000);
        androiddriver.openNotifications();
        String otp = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(@text,'Dogrulama')]"))).getText();
        System.out.println(otp.substring(19,25));
        androiddriver.pressKey(new KeyEvent(AndroidKey.BACK));
        Thread.sleep(500);
        el7.sendKeys(otp.substring(19,25));
        Thread.sleep(10000);
        try {
            androiddriver.switchTo().alert().dismiss();
        }catch (Exception e){}
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.chat.android:id/finish_button"))).click();
        try {
            androiddriver.switchTo().alert().dismiss();
            androiddriver.switchTo().alert().dismiss();
            androiddriver.switchTo().alert().dismiss();
            androiddriver.switchTo().alert().dismiss();
            androiddriver.switchTo().alert().dismiss();
            androiddriver.switchTo().alert().dismiss();
        }catch (Exception e){}
    }
}
