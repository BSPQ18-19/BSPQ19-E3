package es.deusto.client.service;

import es.deusto.server.IServer;

public class RMIService {
    private static RMIService service = null;
    private IServer server;

    public void setService (String IP, Integer PORT, String SERVER_NAME) {
        String name = "//" + IP + ":" + PORT + "/" + SERVER_NAME;
        try {
            server = (IServer) java.rmi.Naming.lookup(name);
        } catch (Exception e) {
            System.out.println("Nejde to");
        }
    }

    public IServer getService() {
        return server;
    }
}
