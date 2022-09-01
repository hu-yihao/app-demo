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

public class DemoTest {
    static final Logger logger =getLogger(lookup().lookupClass());
    @Test
    public void openSettings() throws MalformedURLException, InterruptedException {

        DesiredCapabilities desiredCapabilities=new DesiredCapabilities();
        desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME,"Android");
        desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION,"6.0.1");
        desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME,"127.0.0.1:7555");

        //adb shell pm list package
        //adb shell pm list package -3
        desiredCapabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE,"com.android.settings");
        //adb shell monkey -p com.android.settings -vvv1
        desiredCapabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY,".Settings");

        AndroidDriver driver = new AndroidDriver(
                new URL("http://0.0.0.0:4723/wd/hub"),
                desiredCapabilities);


        logger.info("启动 安卓手机端 --设置");
        sleep(1000);
        driver.quit();


    }
}
