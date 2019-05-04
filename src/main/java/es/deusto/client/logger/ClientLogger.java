package es.deusto.client.logger;

import java.util.logging.Logger;

/**
 * It can be retrieved to log messages.
 */
public class ClientLogger {

    /**
     * The logger to be used in the client.
     */
    private static final Logger logger;

    static {
        logger = Logger.getLogger("ClientLog");
    }

    /**
     * Retrieves the logger to be used in the client.
     * @return a Logger object.
     */
    public static Logger getLogger() {
        return logger;
    }

}

