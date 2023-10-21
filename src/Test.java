import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import dev.failsafe.internal.util.Assert;

public class Test {
	
	public static void main(String[] args) throws InterruptedException {
		
		System.setProperty("webdriver.chrome.driver", 
				System.getProperty("user.dir") + "\\src\\chromedriver\\chromedriver.exe");
		
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		
		//Storing data to use at input textbox
		String textInput = "[{\"name\" : \"Bob\", \"age\" : 20, \"gender\": \"male\"}, {\"name\": \"George\", \"age\" : 42, \"gender\": \"male\"}, {\"name\":\r\n"
				+ "\"Sara\", \"age\" : 42, \"gender\": \"female\"}, {\"name\": \"Conor\", \"age\" : 40, \"gender\": \"male\"}, {\"name\":\r\n"
				+ "\"Jennifer\", \"age\" : 42, \"gender\": \"female\"}]";
		

		driver.get("https://testpages.herokuapp.com/styled/tag/dynamic-table.html");
		System.out.println("**********Url Launched************");
		Thread.sleep(5000);
		System.out.println("Url launched: " + driver.getCurrentUrl());
		
		System.out.println("**********Before Adding Table contains names************");
		
		//checking previous table size or before updation table size
		ArrayList<String> beforeTable = new ArrayList<String>();
		List<WebElement> beforeid = driver.findElements(By.xpath("//th[text()='name']/../following-sibling::tr/td[1]"));
		for (WebElement Ele : beforeid) {
			beforeTable.add(Ele.getText());
		}
		
		System.out.println("Before Adding table size with names:: "+beforeTable.size());
		System.out.println("Before adding names:: " + beforeTable);
		
		driver.findElement(By.xpath("//*[text()='Table Data']")).click();
		Thread.sleep(3000);
		driver.findElement(By.id("jsondata")).clear();
		driver.findElement(By.id("jsondata")).sendKeys(textInput);
		Thread.sleep(5000);
		driver.findElement(By.xpath("//*[@id='refreshtable']")).click();
		Thread.sleep(3000);
		
		System.out.println("**********After Adding Table contains names************");
		
		//Checking after updation tgable size.
		ArrayList<String> actualTable = new ArrayList<String>();
		List<WebElement> Elemnets = driver.findElements(By.xpath("//th[text()='name']/../following-sibling::tr/td[1]"));
		for (WebElement Ele : Elemnets) {
			actualTable.add(Ele.getText());
		}
		
		System.out.println("Before Adding table size with names:: "+actualTable.size());
		System.out.println("Actual Names:: " + actualTable);
		
		//To verify tables sizes whether table updated or not
		if(beforeTable.size() < actualTable.size()) {
			for (int i = 0; i <= actualTable.size(); i++) {
				System.out.println("**********Table updated Successfully validated************");
			}
		}
		
		driver.close();
		System.exit(0);
		
	}

}
