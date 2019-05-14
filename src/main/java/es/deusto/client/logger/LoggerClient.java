package es.deusto.client.logger;

import org.apache.log4j.Logger;

/**
 * The logger object is for logging activity in Client
 * @author Petr
 */
public class LoggerClient {
    private static final Logger LOGGER = Logger.getLogger("ClientLog");

    public static Logger getLogger() {
        return LOGGER;
    }
}
