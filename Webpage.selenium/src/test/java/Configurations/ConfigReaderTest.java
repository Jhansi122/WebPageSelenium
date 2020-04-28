package Configurations;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ConfigReaderTest {
	Properties pro;

	public ConfigReaderTest() {
		try {

			File src = new File("C:\\Program Files\\Selenium\\Spring tool suite\\Code\\Webpage.selenium\\src\\main\\resources\\Project Configurations\\Configuration.properties");
			FileInputStream fis = new FileInputStream(src);
			pro=new Properties();
			pro.load(fis);
		} catch (Exception e) {
			System.out.println("Exception = " + e.getMessage());
			e.printStackTrace();
		}

	}

	public String driver() {
		String driverPath = pro.getProperty("ChromeDriver");
		return driverPath;
	}
	
	 /*public String FirefoxDriver()
	{
		String driverPath= pro.getProperty("FirefoxDriver");
		return driverPath;
	}*/

	public String URL() {
		String Webpage = pro.getProperty("URL");
		return Webpage;
		
	}
	
	}
	


