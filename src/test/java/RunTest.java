import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.awt.*;
import java.net.MalformedURLException;

public class RunTest {
    AndroidDriver<MobileElement> androiddriver;
    WebDriverWait wait;
//Test
    @BeforeMethod()//BeforeMethod ile her test case i koşulmadan önce çalışmaktadır.
    public void setUp() throws MalformedURLException {
        System.out.println("Before içerisinde");
        androiddriver = Connected.Parametreler(
                "Z40",
                "150139381909232003",
                "android",
                "com.chat.android",
                ".tabPages.TabControllerActivity",
                Boolean.TRUE,
                Integer.parseInt("4723"),
                0
        );
        wait = new WebDriverWait(androiddriver, 10);
    }

    @AfterMethod //AfterMethod ile her test casei koşulduktan sonra çalışır.
    public void tearDown() {
        System.out.println("After içerisinde");
        //androiddriver.quit();
    }
    @Test //Loglu versiyon değil ise "Loglu versiyon değil" yazan, Loglu versiyon ise Logun durumunu yazan kısım
    public void LogsController() throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOfElementLocated(MobileBy.ByAccessibilityId.AccessibilityId("Diğer seçenekler"))).click();
        wait.until(ExpectedConditions.elementToBeClickable((By.xpath("//android.widget.TextView[@text='Ayarlar']")))).click();
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated((By.xpath("//android.widget.TextView[@text='Log İzni']"))));
            WebElement log_durum = wait.until(ExpectedConditions.elementToBeClickable(By.ByXPath.xpath("//android.widget.LinearLayout[4]/android.widget.LinearLayout/android.widget.Switch")));
            if(log_durum.getText().contains("KAPALI")){
            System.out.println("Log kapalı");
            }
            if(log_durum.getText().contains("AÇIK")){
                System.out.println("Log açık");
            }

        }catch (Exception e) {
            System.out.println("Loglu versiyon değil");
        }
    }

    @Test
    public void AllMessage(){

    }

}
