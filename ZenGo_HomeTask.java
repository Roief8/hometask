//ZenGo Home Task Made By Roi

package ZenGo_HomeTask;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import graphql.Assert;

public class HomeTask {
	public static WebDriver driver;

	public void launcher() {
		
		//this method initiate the webdriver and launch ZenGo website.
		
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("https:\\www.zengo.com");
		
	}
	
	public void verify (String check, String result) throws InterruptedException {
		
		//this method receive 2 parameters and verify if they are match.
		
		Thread.sleep(1000);
		boolean verify = check.equalsIgnoreCase(result);
		Assert.assertTrue(verify);
		
	}
	
	
	public void menuNav() throws InterruptedException {
		
		//this method navigate site's menu and go to "Ethereum" page.
		
		//locate menu icon.
		driver.findElement(By.className("hamburger-box")).click(); 
		
		//locate "Assets" page.
		Thread.sleep(500);	
		driver.findElement(By.id("menu-item-12609")).click(); 
		
		
		//scroll down and locate "Etherume" page.
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("window.scrollBy(0,650)");
		
		Thread.sleep(500);		
		driver.findElement(By.xpath("//*[@id=\"zengo-coins\"]/tr[2]/td[2]/span[1]/a")).click();
		
		//another way of locating "Etherume" page :
		//WebElement eth = driver.findElement(By.xpath("//*[@id=\"zengo-coins\"]/tr[2]/td[2]/span[1]/a")); 
		//driver.get(eth.getAttribute("href");

	}
	
	public void verifyImg(WebElement image) throws InterruptedException {
		
		//this method verifies the logo source, size, and check if it display successfully.
				
		//verify source.
		String imgSrc = "https://zengo.com/wp-content/themes/zengo/svg/zengologo.svg";
		verify(image.getAttribute("src"), imgSrc);
		
		
		//verify size.
		boolean check;
		Integer height = image.getSize().getHeight();
		Integer width = image.getSize().getWidth();
		
		if (height.equals(40) && width.equals(124)) {
			check=true;
		}else check = false;
		
		Assert.assertTrue(check);
		
		//finally, check if the image displayed successfully.
		Assert.assertTrue(image.isDisplayed());
			
	}
	
	

	public static void main(String[] args) throws InterruptedException {
		
		HomeTask task = new HomeTask();
		
		//1.Launch the ZenGo website - www.zengo.com.
		task.launcher();   
		
		//2.Verify that the site is displayed successfully.
		String title = "ZenGo - Simple & Secure Crypto Wallet App"; 
		task.verify(driver.getTitle(), title);  
		
		//3.Get to the menu item “Assets” and press “Ethereum”.
		task.menuNav();  
		
		//4.Verify that you were redirected to https://zengo.com/assets/ethereum-wallet/
		String address = "https://zengo.com/assets/ethereum-wallet/"; 	
		task.verify(driver.getCurrentUrl(), address);
		
		//5.Verify that ZenGo logo is displayed successfully.
		WebElement logo = driver.findElement(By.className("no-lazy-load"));
		task.verifyImg(logo);
		
		//6.Get back to Home page.
		logo.click();
		
		//7.close browser.
		System.out.println("Test Finished Succesfully.");
		driver.quit();
	
		
	}
	

}
