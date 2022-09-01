package com.ceshiren;

import com.github.javafaker.App;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.List;

import static java.lang.Thread.sleep;
import static java.lang.invoke.MethodHandles.lookup;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.slf4j.LoggerFactory.getLogger;

public class SearchAliTest {
    static final Logger logger =getLogger(lookup().lookupClass());
    @Test
    //adb shell pm list package -3 拿到app
    public void openSettings() throws MalformedURLException, InterruptedException {

        DesiredCapabilities desiredCapabilities=new DesiredCapabilities();
        desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME,"Android");
        desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION,"6.0.1");
        desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME,"huyihao");
        desiredCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME,"UiAutomator2");
        desiredCapabilities.setCapability(MobileCapabilityType.UDID,"127.0.0.1:7555");
        desiredCapabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE,"com.xueqiu.android");
        desiredCapabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY,".view.WelcomeActivityAlias");
        desiredCapabilities.setCapability(MobileCapabilityType.NO_RESET,"true");

        AndroidDriver driver = new AndroidDriver(
                new URL("http://0.0.0.0:4723/wd/hub"),
                desiredCapabilities);

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

        WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(20),Duration.ofSeconds(6));
        logger.info("启动 安卓手机端 --雪球");
        //sleep(3000);
        //显示等待
        logger.info("启动安卓手机 应用 雪球");

        long start=System.currentTimeMillis();
        wait.until(webDriver-> {
            return driver.findElement(AppiumBy.id("com.xueqiu.android:id/home_search"));
        });
        long end=System.currentTimeMillis();
        logger.info("打开app加载到首页的时间为：{}",(end-start));

        WebElement searchEle=driver.findElement(AppiumBy.id("com.xueqiu.android:id/home_search"));
        searchEle.click();

        start=System.currentTimeMillis();
        wait.until(webDriver-> {
            return driver.findElement(AppiumBy.id("com.xueqiu.android:id/search_input_text"));
        });
        end=System.currentTimeMillis();
        logger.info("跳转到了搜索页面的时间为：{}",(end-start));

        WebElement inputEle= driver.findElement(AppiumBy.id("com.xueqiu.android:id/search_input_text"));
        inputEle.sendKeys("alibaba");

        start=System.currentTimeMillis();
        wait.until(webDriver-> {
            return driver.findElement(AppiumBy.id("com.xueqiu.android:id/name"));
        });
        end=System.currentTimeMillis();
        logger.info("获取搜索列表的时间为：{}",(end-start));

        //1.获取list集合
        List<WebElement> searchList=driver.findElements(AppiumBy.id("com.xueqiu.android:id/name"));
        String text=searchList.get(0).getText();

        //2.获取单个元素，取得list集合中的第一个
        WebElement searchTextEle=driver.findElement(AppiumBy.id("com.xueqiu.android:id/name"));
        String text1=searchTextEle.getText();

        logger.info("list集合获取文本结果：{}",text.equals("阿里巴巴")?"搜索成功":"搜索失败");
        logger.info("获取单个元素的文本结果：{}",text1.equals("阿里巴巴")?"搜索成功":"搜索失败");
       // logger.info("阿里巴巴现在的价格{}",text2);

        WebElement element=driver.findElement(AppiumBy.xpath("//*[@text=\"阿里巴巴\"]"));
        element.click();

        WebElement searchTextEle2=driver.findElement(AppiumBy.id("com.xueqiu.android:id/current_price"));
        String text2=searchTextEle2.getText();

        driver.quit();
        assertTrue(text.equals("阿里巴巴"));


    }
}
