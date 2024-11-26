package utils;

import helpers.PropertiesHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

public class LogUtils {

    private static final Logger LOGGER = (Logger) LogManager.getLogger(LogUtils.class);



    public static void info(String message) {
        if (PropertiesHelper.getValue("LOGGER").equals("all")) {
        LOGGER.info(message);
        }
    }

    public static void warn(String message) {
        if (PropertiesHelper.getValue("LOGGER").equals("all")) {
            LOGGER.warn(message);

        }
    }

    public static void debug(String message) {
        if (PropertiesHelper.getValue("LOGGER").equals("all")) {
            LOGGER.debug(message);
        }
    }

    public static void error(String message) {
        if (PropertiesHelper.getValue("LOGGER").equals("error") || (PropertiesHelper.getValue("LOGGER").equals("all"))) {
            LOGGER.info(message);
        }
    }


}
