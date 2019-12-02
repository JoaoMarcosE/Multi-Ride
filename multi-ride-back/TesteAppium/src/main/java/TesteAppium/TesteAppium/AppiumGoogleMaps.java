package TesteAppium.TesteAppium;

import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.html5.Location;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

public class AppiumGoogleMaps {

	private AppiumDriver driver;

	public void OpenGoogleMaps() throws IOException {
		DesiredCapabilities caps = new DesiredCapabilities();

		caps.setCapability("platformName", "Android");
		caps.setCapability("deviceName", "Android Emulator");
		caps.setCapability("automationName", "UiAutomator2");
		caps.setCapability("appPackage", "com.google.android.apps.maps");
		caps.setCapability("appActivity", "com.google.android.maps.MapsActivity");
		caps.setCapability("intentAction", "android.intent.action.VIEW");

		driver = new AppiumDriver(new URL("http://localhost:4723/wd/hub"), caps);
	}
	
	public void SetRoute(double initialLat, double initialLon, double finalLat, double finalLon) {

		String initialLatitude = Double.toString(initialLat);
		String initialLongitude = Double.toString(initialLon);
		
		String initialLocation = initialLatitude + ", " + initialLongitude;
		
        driver.findElementById("com.google.android.apps.maps:id/search_omnibox_text_box").click();
        driver.findElementById("com.google.android.apps.maps:id/search_omnibox_edit_text").sendKeys(initialLocation);
        
        driver.getKeyboard().sendKeys("\n");
        
        try {
			Thread.currentThread().sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
        
        MobileElement element = (MobileElement) driver.findElement(MobileBy.AndroidUIAutomator("new UiSelector().text(\"Rotas\");"));
        element.click();
        
        List<MobileElement> elements = (List<MobileElement>) driver.findElements(MobileBy.AndroidUIAutomator("new UiSelector().text(\"Escolher o ponto de partida\");"));
        
        if (elements.size() >= 0)
        	elements = (List<MobileElement>) driver.findElements(MobileBy.AndroidUIAutomator("new UiSelector().text(\"Seu local\");"));
        	
        element = elements.get(0);      
        element.click();
        
        element = (MobileElement) driver.findElement(MobileBy.AndroidUIAutomator("new UiSelector().text(\"Escolher o ponto de partida\");"));
        element.click();
        
		String finalLatitude = Double.toString(finalLat);
		String finalLongitude = Double.toString(finalLon);
		
		String finalLocation = finalLatitude + ", " + finalLongitude;
		
		element.sendKeys(finalLocation);
		driver.getKeyboard().sendKeys("\n");
	}
	
	public List<Estimates> GetEstimates(){
		List<Estimates> estimates = new ArrayList<>();
		
		SelectTransport();
		
        try {
			Thread.currentThread().sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		estimates.add(FindEstimate("99"));
		estimates.add(FindEstimate("Uber"));
		
		return estimates;
	}
	
	public Estimates FindEstimate(String app){
		MobileElement element = (MobileElement) driver.findElement(MobileBy.AndroidUIAutomator("new UiSelector().textContains(\"" + app + "\");"));
		element.click();
		
		element = (MobileElement) driver.findElement(MobileBy.AndroidUIAutomator("new UiSelector().textContains(\"R$\");"));
		String price = element.getText();
			
		Estimates newEstimate = new Estimates();
		newEstimate.setTime("");
		newEstimate.setPrice(price);		

		return newEstimate;
	}
	
	private void SelectTransport(){
		
		Dimension size = driver.manage().window().getSize();

	    //Starting x location set to 5% of the width (near left)
	    int startx = (int) (size.width * 0.80);
	    //Ending x location set to 95% of the width (near right)
	    int endx = (int) (size.width * 0.20);
	    //y position set to mid-screen vertically
	    int starty = (int) (size.height / 4.3);
		
	    TouchAction touchAction = new TouchAction(driver);

	    touchAction.longPress(PointOption.point(startx, starty))
	               .moveTo(PointOption.point(endx, starty))
	               .release()
	               .perform();
	    
	    endx = (int) (size.width * 0.40);
	    touchAction.longPress(PointOption.point(startx, starty))
        .moveTo(PointOption.point(endx, starty))
        .release()
        .perform();
	       
        try {
			Thread.currentThread().sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	    
	    startx = (int) (size.width * 0.70);
	    touchAction.press(PointOption.point(startx, starty))
	    		   .release()
	    		   .perform();
	}
}