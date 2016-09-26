package log4juse;

import java.io.File;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class SimpleUse {
	public static void main(String[] args) {
		File configFile = new File(System.getProperty("user.dir"),
				"files0/log4j.properties");
		PropertyConfigurator.configure(configFile.getPath());
		Logger logger = Logger.getLogger(SimpleUse.class);
		// System.out.println("This is println message.");
		// 记录debug级别的信息
		logger.debug("This is debug message.");
		// 记录info级别的信息
		logger.info("This is info message.");
		// 记录error级别的信息
		logger.error("This is error message.");
	}
}
