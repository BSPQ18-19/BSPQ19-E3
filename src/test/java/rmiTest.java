//import java.rmi.Naming;
//import java.rmi.RemoteException;
//import java.rmi.registry.LocateRegistry;
//import java.rmi.registry.Registry;
//
//import es.deusto.client.Client;
//import es.deusto.server.IServer;
//import es.deusto.server.Server;
//import es.deusto.server.ServerTest;
//import es.deusto.server.jdo.User;
//import junit.framework.Test;
//import junit.framework.TestCase;
//import junit.framework.TestSuite;
//
//public class rmiTest extends TestCase{
//	private static Registry locateRegistry;
//	private static String serverUrl;
//	private static String clientUrl;
//	private static String cwd = rmiTest.class.getProtectionDomain().getCodeSource().getLocation().getFile();
//	/**
//     * Create the test case
//     *
//     * @param testName name of the test case
//     */
//    public rmiTest( String testName ){
//        super( testName );
//        try {
//			locateRegistry = LocateRegistry.createRegistry(1098);
//		} catch (RemoteException e) {
//			e.printStackTrace();
//		}
//
//		serverUrl = "//127.0.0.1:1099/newspaperServer";
//		clientUrl = "//127.0.0.1:1099/newspaperServer";
//
////System.setProperty("java.rmi.server.codebase", "file:" + cwd);
//        
//    }
//
//    /**
//     * @return the suite of tests being tested
//     */
//    public static Test suite()
//    {
//        return new TestSuite( rmiTest.class );
//    }
//
//    /**
//     * Rigourous Test :-)
//     * @throws RemoteException 
//     */
//    public void testApp() throws RemoteException
//    {
//    	//Server
//    	if (System.getSecurityManager() == null) {
//			System.setSecurityManager(new SecurityManager());
//		}
//
//		try {
//			IServer objServer = new Server();
//			Naming.rebind(serverUrl, objServer);
//			// loadDB();
//			System.out.println("Server '" + serverUrl + "' active and waiting...");
//			java.io.InputStreamReader inputStreamReader = new java.io.InputStreamReader(System.in);
//			java.io.BufferedReader stdin = new java.io.BufferedReader(inputStreamReader);
//			@SuppressWarnings("unused")
//			String line = stdin.readLine();
//		} catch (Exception e) {
//			System.err.println("Hello exception: " + e.getMessage());
//			e.printStackTrace();
//		}
//    	
//		//Client
//		
//		if (System.getSecurityManager() == null) {
//			System.setSecurityManager(new SecurityManager());
//		}
//		try {
//			Client client = new Client();
//			client.connection(clientUrl);
//		} catch (Exception e) {
//			System.err.println(e);
//		}
//    
//    }
//	
//	
//}
