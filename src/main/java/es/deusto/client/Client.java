package es.deusto.client;

import java.rmi.RemoteException;
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
	Scanner scan = new Scanner(System.in);

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

	protected void menu() {
		try {
			int option = 0;
			do {
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
							firstArticles();
							// User actions
							userActions();
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
							adminActions(adm);
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

	protected void firstArticles() throws RemoteException {
		ArrayList<Article> articles = (ArrayList<Article>) server.getFirstArticles();
		if (articles.size() > 0 && articles != null) {
			logger.debug("Size of the articles: " + articles.size());
			logger.info("Deusto newspaper");
			for (int m = 0; m < 10 && m < articles.size(); m++) {
				logger.info("Article " + m + ":" + articles.get(m).getTitle());
			}
		} else {
			logger.info("There is no first articles");
		}
	}

	protected void userActions() throws RemoteException {
		int option = 0;
		do {
			logger.info("Options: ");
			logger.info("1) Read article");
			logger.info("2) View the top articles");
			logger.info("3) LogOut");
			option = scan.nextInt();
			scan.nextLine();
			if (option == 1 || option == 2) {
				switch (option) {
				case 1:
					// Read article
					logger.info("Select the article you want to read (Example: 1,2,3...): ");
					int option2 = scan.nextInt();
					scan.nextLine();
					ArrayList<Article> articles = (ArrayList<Article>) server.getFirstArticles();
					logger.info(articles.get(option2).toString());
					articles.get(option2).setVisits(articles.get(option2).getVisits() + 1);
					break;
				case 2:
					// view top article
					logger.info("View top articles");
					ArrayList<Article> top = server.viewTopArticle();
					for (int i = 0; i < top.size(); i++) {
						logger.info("Article " + i + ": " + top.get(i).getTitle());
						logger.info("Visits: " + top.get(i).getVisits());
					}
					int option3 = 0;
					do {
						logger.info("Options: ");
						logger.info("1) Read article");
						logger.info("2) Get recent articles");
						logger.info("3) LogOut");
						option3 = scan.nextInt();
						scan.nextLine();
						if (option3 == 1) {
							// Read article
							logger.info("Select the article you want to read (Example: 1,2,3...): ");
							int optionRead = scan.nextInt();
							scan.nextLine();
							logger.info(top.get(optionRead).toString());
							top.get(optionRead).setVisits(top.get(optionRead).getVisits() + 1);
						} else if (option3 == 2) {
							firstArticles();
						}
						else if (option3 == 3) {
							option = 3;
							logger.info("Logging out...");
						} else {
							logger.info("Option error");
						}
					} while (option3 != 2 && option3 != 3);
					break;
				}

			} else if (option == 3) {
				logger.info("Logging out...");
			} else {
				logger.info("Option error");
			}

		} while (option != 3);
	}

	protected void adminActions(Admin adm) throws RemoteException {
		int option2 = 0;
		ArrayList<Article> ownArticles;
		do {
			logger.info("Options: ");
			logger.info("1) Create article");
			logger.info("2) Eliminate article");
			logger.info("3) Edit article");
			logger.info("4) See your articles");
			logger.info("5) LogOut");
			option2 = scan.nextInt();
			scan.nextLine();
			if (option2 == 1 || option2 == 2 || option2 == 3 || option2==4) {
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
					break;
				case 2:
					// Eliminate article
					logger.info("Eliminating article...");
					ownArticles = server.searchArticleAuthor(adm.username);
					if (ownArticles.size() > 0 && ownArticles != null) {
						//You have own articles
						logger.debug("Size of the articles: " + ownArticles.size());
						logger.info("Your articles");
						for (int m = 0; m < 10 && m < ownArticles.size(); m++) {
							logger.info("Article " + m + ":" + ownArticles.get(m).getTitle());
						}
						//Options with our own articles, Read or Select Deleting article
						int optionEliminate;
						do {
							optionEliminate = 0;
							logger.info("Options: ");
							logger.info("1) Read article");
							logger.info("2) Select the article you want to delete");
							logger.info("3) Exit the Eliminating article option");
							optionEliminate = scan.nextInt();
							scan.nextLine();
							if (optionEliminate == 1 || optionEliminate == 2 ) {
								if(optionEliminate == 1) {
									logger.info("Select the article you want to read (Example: 1,2,3...): ");
									int optionRead = scan.nextInt();
									scan.nextLine();
									logger.info(ownArticles.get(optionRead).toString());
								}else {
									logger.info("Your articles");
									for (int m = 0; m < 10 && m < ownArticles.size(); m++) {
										logger.info("Article " + m + ":" + ownArticles.get(m).getTitle());
									}
									logger.info("Select the article you want to delete (Example: 1,2,3...): ");
									int optionDelete = scan.nextInt();
									scan.nextLine();
									Boolean okey = server.deleteArticle(ownArticles.get(optionDelete), adm);
									logger.info("The process of deleting the article: " + okey);
								}
							}else {
								logger.info("Exiting the Eliminating article option...");
							}
						}while(optionEliminate != 3);
					} else {
						//You dont have articles
						logger.info("You don't have articles");
					}
					break;	
				case 3:
					// Edit article
					break;
				case 4:
					// See yours articles
					ownArticles = server.searchArticleAuthor(adm.username);
					if (ownArticles.size() > 0 && ownArticles != null) {
						//You have own articles
						logger.debug("Size of the articles: " + ownArticles.size());
						logger.info("Your articles");
						for (int m = 0; m < 10 && m < ownArticles.size(); m++) {
							logger.info("Article " + m + ":" + ownArticles.get(m).getTitle());
						}
					}else {
						//You dont have articles
						logger.info("You don't have articles");
					}
					break;
				}

			} else if (option2 == 5) {
				logger.info("Logging out...");
			} else {
				logger.info("Option error");
			}

		} while (option2 != 5);
	}
}
