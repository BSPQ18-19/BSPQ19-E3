package es.deusto.client;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Scanner;

import org.apache.log4j.Logger;

import es.deusto.server.IServer;
import es.deusto.server.Server;
import es.deusto.server.jdo.Admin;
import es.deusto.server.jdo.Article;
import es.deusto.server.jdo.User;

/**
 * Hello world!
 *
 */
public class Client {
	static Logger logger = Logger.getLogger(Client.class.getName());
	IServer server;

	public static void main(String[] args) {
		if (args.length != 3) {
			System.out.println("Use: java [policy] [codebase] Client.Client [host] [port] [server]");
			System.exit(0);
		}

		if (System.getSecurityManager() == null) {
			System.setSecurityManager(new SecurityManager());
		}
		try {
			String name = "//" + args[0] + ":" + args[1] + "/" + args[2];
			Client client = new Client();
			client.connection(name);
			client.menu();
		} catch (Exception e) {
			logger.error(e);
		}
	}

	public void connection(String name) {
		try {
			this.server = (IServer) java.rmi.Naming.lookup(name);
		} catch (Exception e) {
			logger.error(e);
		}
	}

	private void menu() {
		try {
			int option = 0;
			do {
				Scanner scan = new Scanner(System.in);
				logger.info("Select an option: ");
				logger.info("1) LogIn User");
				logger.info("2) Register User");
				logger.info("3) LogIn Admin");
				logger.info("4) Exit");
				option = scan.nextInt();
				scan.nextLine();
				if (option == 1 || option == 2 || option == 3) {
					String username;
					String pass;
					switch (option) {
					case 1:
						// LogIn User
						logger.info("Username: ");
						username = scan.nextLine();
						logger.info("Password: ");
						pass = scan.nextLine();
						logger.info("LogIn: " + username + " : " + pass);
						User user = server.logIn(username, pass);
						if (user != null) {
							logger.info("LogIn successful");
							ArrayList<Article> articles = (ArrayList<Article>) server.getFirstArticles();
							if (articles.size() > 0 && articles != null) {
								logger.debug("Size of the articles: " + articles.size());
								for (int m = 0; m < 10 && m < articles.size(); m++) {
									logger.info(articles.get(m).getTitle());
								}
							} else {
								logger.info("There is no first articles");
							}

						} else {
							logger.info("LogIn error");

						}
						break;
					case 2:
						// Register user
						logger.info("User: ");
						username = scan.nextLine();
						logger.info("Password: ");
						pass = scan.nextLine();
						logger.info("Email: ");
						String email = scan.nextLine();
						if (server.registerUser(username, pass, email)) {
							logger.info("Register successful");
						} else {
							logger.info("Register error");
						}
						break;
					case 3:
						// LogIn admin
						logger.info("Admin username: ");
						username = scan.nextLine();
						logger.info("Password: ");
						pass = scan.nextLine();
						Admin adm = server.logInAdmin(username, pass);
						if (adm != null) {
							logger.info("LogIn Admin successful");
							int option2 = 0;
							do {
								logger.info("Options: ");
								logger.info("1) Create article");
								logger.info("2) Eliminate article");
								logger.info("3) Edit article");
								logger.info("3) LogOut");
								option2 = scan.nextInt();
								scan.nextLine();
								if (option2 == 1 || option2 == 2 || option2 == 3) {
									switch (option2) {
									case 1:
										// Create article
										logger.info("Creating article...");
										logger.info("Title: ");
										String title = scan.nextLine();
										logger.info("Body of the article: ");
										String body = scan.nextLine();
										logger.info("Category: ");
										String category = scan.nextLine();
										Article art = new Article(title, body, category, adm);
										server.createArticle(art, adm);
									case 2:
										// Eliminate article
									case 3:
										// Edit article
									}

								} else if (option2 == 4) {
									logger.info("Logging out...");
								} else {
									logger.info("Option error");
								}

							} while (option2 != 4);

						} else {
							logger.info("LogIn Admin error");
						}
						break;
					}

				} else if (option == 4) {
					logger.info("Exiting from application");
					break;
				} else {
					logger.info("Error in the option");
				}

			} while (option != 4);

		} catch (Exception e) {
			System.err.println("RMI Example exception: " + e.getMessage());
			e.printStackTrace();
		}
	}
}
