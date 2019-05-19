//package es.deusto.server;
//
//import java.rmi.Naming;
//import java.rmi.RemoteException;
//
//import org.apache.log4j.Logger;
//import org.junit.After;
//import org.junit.AfterClass;
//import org.junit.Before;
//import org.junit.BeforeClass;
//
//import es.deusto.client.Client;
//
//public class rmiTest {
//	
//	private static Thread rmiRegistryThread = null;
//    private static Thread rmiServerThread = null;
//	static Logger logger = Logger.getLogger(rmiTest.class.getName());
//	private static Client client;
//    
//    @BeforeClass
//    static public void setUpClass() {
//        // Launch the RMI registry
//        class RMIRegistryRunnable implements Runnable {
//
//            public void run() {
//                try {
//                    java.rmi.registry.LocateRegistry.createRegistry(1099);
//                    System.out.println("RMI registry ready.");
//                } catch (Exception e) {
//                    System.out.println("RMI registry already started");
//                }
//            }
//        }
//
//        rmiRegistryThread = new Thread(new RMIRegistryRunnable());
//        rmiRegistryThread.start();
//        try {
//            Thread.sleep(2000);
//        } catch (InterruptedException ie) {
//            ie.printStackTrace();
//        }
//
//        String ip = "127.0.0.1", port = "1099", name = "newspaperServer";
//        String serverName = "//"+ip+":"+port+"/"+ name;
//        class RMIServerRunnable implements Runnable {
//
//            public void run() {
//
//                try {
//                    IServer objServer = new Server();
//                    Naming.rebind(serverName, objServer);
//                    logger.info("Running the server");
//                } catch (Exception e) {
//                    logger.error(e);
//                }
//            }
//        }
//        rmiServerThread = new Thread(new RMIServerRunnable());
//        rmiServerThread.start();
//        try {
//            Thread.sleep(2000);
//        } catch (InterruptedException ie) {
//            ie.printStackTrace();
//        }
//
//    }
//
//    @Before
//    public void setUp() throws RemoteException {
//    	client = new Client();
//		client.connection("//127.0.0.1:1099/newspaperServer");
//		client.server.getFirstArticles();
//    }
//
//    @After
//    public void tearDown(){
//       
//    }
//
//    @AfterClass
//    static public void tearDownClass(){
//        try	{
//            rmiServerThread.join();
//            rmiRegistryThread.join();
//        } catch (InterruptedException ie) {
//            ie.printStackTrace();
//        }
//}
//	
//	
//
//}
