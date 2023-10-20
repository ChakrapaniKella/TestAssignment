import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Test {
	
	public static void main(String[] args) throws InterruptedException {

		System.setProperty("webdriver.chrome.driver", 
				System.getProperty("user.dir") + "\\src\\chromedriver\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		
		//Storing data
		String textInput = "[{\"name\" : \"Bob\", \"age\" : 20, \"gender\": \"male\"}, {\"name\": \"George\", \"age\" : 42, \"gender\": \"male\"}, {\"name\":\r\n"
				+ "\"Sara\", \"age\" : 42, \"gender\": \"female\"}, {\"name\": \"Conor\", \"age\" : 40, \"gender\": \"male\"}, {\"name\":\r\n"
				+ "\"Jennifer\", \"age\" : 42, \"gender\": \"female\"}]";
		
		ArrayList<String> expectedTable = new ArrayList<String>();
		expectedTable.add("Bob");
		expectedTable.add("George");
		expectedTable.add("Sara");
		expectedTable.add("Conor");
		expectedTable.add("Jennifer");

		driver.get("https://testpages.herokuapp.com/styled/tag/dynamic-table.html");
		System.out.println("**********Url Launched************");
		Thread.sleep(5000);
		System.out.println("Url launched: " + driver.getCurrentUrl());
		
		System.out.println("**********Before Adding Table contains names************");
		
		ArrayList<String> beforeTable = new ArrayList<String>();
		List<WebElement> beforeid = driver.findElements(By.xpath("//th[text()='name']/../following-sibling::tr/td[1]"));
		for (WebElement Ele : beforeid) {
			beforeTable.add(Ele.getText());
		}
		System.out.println("Before adding names:: " + beforeTable);
		
		driver.findElement(By.xpath("//*[text()='Table Data']")).click();
		Thread.sleep(3000);
		driver.findElement(By.id("jsondata")).clear();
		driver.findElement(By.id("jsondata")).sendKeys(textInput);
		Thread.sleep(5000);
		driver.findElement(By.xpath("//*[@id='refreshtable']")).click();
		Thread.sleep(3000);
		
		System.out.println("**********After Adding Table contains names************");
		
		ArrayList<String> actualTable = new ArrayList<String>();
		List<WebElement> Elemnets = driver.findElements(By.xpath("//th[text()='name']/../following-sibling::tr/td[1]"));
		for (WebElement Ele : Elemnets) {
			actualTable.add(Ele.getText());
		}
		
		System.out.println("Actual Names:: " + actualTable);
		
		for (int i = 0; i <= 4; i++) {
			if (expectedTable.get(0).equals(actualTable.get(0))) {
				System.out.println("**********Validation passed Table got updated************");
				System.out.println("**********Expected " + expectedTable.get(i) + " Validated against "
						+ actualTable.get(i) + "************");
			}
		}
		
		driver.close();
		System.exit(0);
	}
}
