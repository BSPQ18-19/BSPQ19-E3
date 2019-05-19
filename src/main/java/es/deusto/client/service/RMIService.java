package es.deusto.client.service;

import es.deusto.server.IServer;

public class RMIService {
    private static RMIService service = new RMIService();
    private IServer server = null;

    public void setService (String IP, Integer PORT, String SERVER_NAME) {
        String name = "//" + IP + ":" + PORT + "/" + SERVER_NAME;
        try {
            server = (IServer) java.rmi.Naming.lookup(name);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static RMIService getService() {
        return service;
    }

    public IServer getServer() {
        return server;
    }
}
