package cox.interview.demo;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;
import ch.qos.logback.core.joran.spi.JoranException;

/**
 * Main class of the application!
 * @author minhnguyen
 */
@SpringBootApplication
public class App 
{
	/** LOGGER */
	private static final Logger LOGGER = LoggerFactory.getLogger(App.class);

	/**
	 * Entry point of the application.
	 * @param args arguments for the application.
	 */
    public static void main( String[] args )
    {
    	initLogging();
		SpringApplication.run(App.class, args);
	}
    
    /**
     * Inits logger for the project.
     */
    private static void initLogging() {
		File logFile = new File("./logback.xml");
		if (logFile.exists()) {
			System.out.println("Loading log config from: " + logFile.getAbsolutePath());
			LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
			loggerContext.reset();
			JoranConfigurator configurator = new JoranConfigurator();
			try (FileInputStream configStream = new FileInputStream(logFile)) {
				configurator.setContext(loggerContext);
				configurator.doConfigure(configStream); // loads logback file
			} catch (IOException e) {
				LOGGER.error("Could not read log config.");
			} catch (JoranException e) {
				/* Can not load log configuration */
				e.printStackTrace();
				System.exit(0);
			}

		} else {
			System.err.println("Can not find logging configuration");
		}
	}
}
