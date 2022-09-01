package com.ceshiren;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.slf4j.Logger;

import java.net.MalformedURLException;
import java.net.URL;

import static java.lang.Thread.sleep;
import static java.lang.invoke.MethodHandles.lookup;
import static org.slf4j.LoggerFactory.getLogger;

public class MusicTest {

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

        desiredCapabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE,"com.netease.cloudmusic");

        desiredCapabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY,".activity.LoadingActivity");

        desiredCapabilities.setCapability(MobileCapabilityType.NO_RESET,"true");

        AndroidDriver driver = new AndroidDriver(
                new URL("http://0.0.0.0:4723/wd/hub"),
                desiredCapabilities);
        // //
        logger.info("启动 安卓手机端 --设置");
        sleep(1000);
        driver.quit();
    }

}
