package es.deusto.client.model;

import es.deusto.server.jdo.Article;
import org.apache.log4j.Logger;
import es.deusto.client.logger.LoggerClient;
import es.deusto.client.service.RMIService;

public class Read {
    private Logger LOGGER;
    private RMIService service;
    private Article loggedUser = null;

    public Read() {
        LOGGER =  LoggerClient.getLogger();
        service = RMIService.getService();
    }

}
