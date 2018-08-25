package util;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class LogThis {      
    public static void log(Logger logger, Level level, String text) {
        PropertyConfigurator.configure("log4j.properties"); //Located in the Apache folder/bin
        switch(level) {
            case TRACE: logger.trace(text); break;
            case DEBUG: logger.debug(text); break;
            case INFO:  logger.info(text);  break;
            case WARN:  logger.warn(text);  break;
            case ERROR: logger.error(text); break;
            case FATAL: logger.fatal(text); break;
        }
    }
}
