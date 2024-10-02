package engine.logger;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.Configurator;

public class CustomLogger {
    static String filePath = "src/main/resources/properties/log4j2.xml";

    static {
        Configurator.initialize(null, filePath);
    }

    public static Logger logger = LogManager.getLogger("logger");
}
