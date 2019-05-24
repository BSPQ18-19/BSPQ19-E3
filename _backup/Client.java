/** @package es.deusto.client
 @brief This is the brief documentation for the Java package es.deusto.client which is a package for the client logic

 This class is the first client which initialize the GUI
 */
package es.deusto.client;

import java.rmi.RemoteException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Scanner;

import org.apache.log4j.Logger;

import es.deusto.server.IServer;
import es.deusto.server.Server;
import es.deusto.server.jdo.Admin;
import es.deusto.server.jdo.Article;
import es.deusto.server.jdo.User;

/**
 * The client class
 *
 */
public class Client {
	private static ResourceBundle bundle = ResourceBundle.getBundle("lang/translations_ES");
	static Logger logger = Logger.getLogger(Client.class.getName());
	public IServer server;
	Scanner scan = new Scanner(System.in);

	public static void main(String[] args) {
		if (args.length != 3) {
			logger.info("Use: java [policy] [codebase] Client.Client [host] [port] [server]");
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
			int lang = 0;
			do {
				logger.info("Select a language: ");
				logger.info("1) English");
				logger.info("2) Spanish");
				logger.info("3) Czech");
				lang = scan.nextInt();
			}while(lang < 1 || lang > 3);
			if(lang == 1) {
				this.bundle = ResourceBundle.getBundle("lang/translations_US");
			}else if(lang == 2) {
				this.bundle = ResourceBundle.getBundle("lang/translations_ES");
			}else if(lang == 3){
				this.bundle = ResourceBundle.getBundle("lang/translations_CZ");
			}
			int option = 0;
			do {
				logger.info(this.bundle.getString("Select_an_option"));
				logger.info(this.bundle.getString("LogIn_User"));
				logger.info(this.bundle.getString("Register_User"));
				logger.info(this.bundle.getString("LogIn_Admin"));
				logger.info(this.bundle.getString("Exit"));
				option = scan.nextInt();
				scan.nextLine();
				if (option == 1 || option == 2 || option == 3) {
					String username;
					String pass;
					switch (option) {
						case 1:
							// LogIn User
							logger.info(this.bundle.getString("Username"));
							username = scan.nextLine();
							logger.info(this.bundle.getString("Password"));
							pass = scan.nextLine();
							logger.info(this.bundle.getString("LogIn")+ ": " + username + " : " + pass);
							User user = server.logIn(username, pass);
							if (user != null) {
								logger.info(this.bundle.getString("LogIn_successful"));
								firstArticles();
								// User actions
								userActions();
							} else {
								logger.info(this.bundle.getString("LogIn_error"));
							}
							break;
						case 2:
							// Register user
							logger.info(this.bundle.getString("User"));
							username = scan.nextLine();
							logger.info(this.bundle.getString("Password"));
							pass = scan.nextLine();
							logger.info(this.bundle.getString("Email"));
							String email = scan.nextLine();
							if (server.registerUser(username, pass, email)) {
								logger.info(this.bundle.getString("Register_successful"));
							} else {
								logger.info(this.bundle.getString("Register_error"));
							}
							break;
						case 3:
							// LogIn admin
							logger.info(this.bundle.getString("Admin_username"));
							username = scan.nextLine();
							logger.info(this.bundle.getString("Password"));
							pass = scan.nextLine();
							Admin adm = server.logInAdmin(username, pass);
							if (adm != null) {
								logger.info(this.bundle.getString("LogIn_Admin_successful"));
								adminActions(adm);
							} else {
								logger.info(this.bundle.getString("LogIn_Admin_error"));
							}
							break;
					}

				} else if (option == 4) {
					logger.info(this.bundle.getString("Exiting_from_application"));
					break;
				} else {
					logger.info(this.bundle.getString("Error_in_the_option"));
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
			logger.info(this.bundle.getString("Deusto_newspaper"));
			for (int m = 0; m < 10 && m < articles.size(); m++) {
				logger.info(this.bundle.getString("Article") +" " + m + ":" + articles.get(m).getTitle());
			}
		} else {
			logger.info(this.bundle.getString("There_is_no_first_articles"));
		}
	}

	protected void userActions() throws RemoteException {
		int option = 0;
		do {
			logger.info(this.bundle.getString("Options"));
			logger.info(this.bundle.getString("Read_article"));
			logger.info(this.bundle.getString("View_the_top_articles"));
			logger.info(this.bundle.getString("LogOut"));

			option = scan.nextInt();
			scan.nextLine();
			if (option == 1 || option == 2) {
				switch (option) {
					case 1:
						// Read article
						logger.info(this.bundle.getString("Select_the_article_you_want_to_read"));
						int option2 = scan.nextInt();
						scan.nextLine();
						ArrayList<Article> articles = (ArrayList<Article>) server.getFirstArticles();
						logger.info(articles.get(option2).toString());
						articles.get(option2).setVisits(articles.get(option2).getVisits() + 1);
						break;
					case 2:
						// view top article
						logger.info(this.bundle.getString("View_top_articles"));
						ArrayList<Article> top = server.viewTopArticle();
						for (int i = 0; i < top.size(); i++) {
							logger.info(this.bundle.getString("Article") + i + ": " + top.get(i).getTitle());
							logger.info(this.bundle.getString("Visits") + " " + top.get(i).getVisits());
						}
						int option3 = 0;
						do {
							logger.info(this.bundle.getString("Options"));
							logger.info(this.bundle.getString("Read_article"));
							logger.info(this.bundle.getString("Get_recent_articles"));
							logger.info(this.bundle.getString("LogOut"));
							option3 = scan.nextInt();
							scan.nextLine();
							if (option3 == 1) {
								// Read article
								logger.info(this.bundle.getString("Select_the_article_you_want_to_read"));
								int optionRead = scan.nextInt();
								scan.nextLine();
								logger.info(top.get(optionRead).toString());
								top.get(optionRead).setVisits(top.get(optionRead).getVisits() + 1);
							} else if (option3 == 2) {
								firstArticles();
							} else if (option3 == 3) {
								option = 3;
								logger.info(this.bundle.getString("Logging_out..."));
							} else {
								logger.info(this.bundle.getString("Option_error"));
							}
						} while (option3 != 2 && option3 != 3);
						break;
				}

			} else if (option == 3) {
				logger.info(this.bundle.getString("LogOut"));
			} else {
				logger.info(this.bundle.getString("Option_error"));
			}

		} while (option != 3);
	}

	protected void adminActions(Admin adm) throws RemoteException {
		int option2 = 0;
		ArrayList<Article> ownArticles;
		do {
			logger.info(this.bundle.getString("Options"));
			logger.info(this.bundle.getString("Create_article"));
			logger.info(this.bundle.getString("Eliminate_article"));
			logger.info(this.bundle.getString("Edit_article"));
			logger.info(this.bundle.getString("See_your_articles"));
			logger.info(this.bundle.getString("LogOut5"));

			option2 = scan.nextInt();
			scan.nextLine();
			if (option2 == 1 || option2 == 2 || option2 == 3 || option2 == 4) {
				switch (option2) {
					case 1:
						// Create article
						logger.info(this.bundle.getString("Creating_article"));
						logger.info(this.bundle.getString("Title"));

						String title = scan.nextLine();
						logger.info(this.bundle.getString("Body_of_the_article"));
						String body = scan.nextLine();
						logger.info(this.bundle.getString("Category"));
						String category = scan.nextLine();
						Article art = new Article(title, body, category, adm);
						server.createArticle(art, adm);
						break;
					case 2:
						// Eliminate article
						logger.info(this.bundle.getString("Eliminating_article..."));
						ownArticles = server.searchArticleAuthor(adm.username);
						if (ownArticles.size() > 0 && ownArticles != null) {
							// You have own articles
							logger.debug("Size of the articles: " + ownArticles.size());
							logger.info(this.bundle.getString("Your_articles"));
							for (int m = 0; m < 10 && m < ownArticles.size(); m++) {
								logger.info(this.bundle.getString("Article") + m + ":" + ownArticles.get(m).getTitle());
							}
							// Options with our own articles, Read or Select Deleting article
							int optionEliminate;
							do {
								optionEliminate = 0;
								logger.info(this.bundle.getString("Options"));
								logger.info(this.bundle.getString("Read_article"));
								logger.info(this.bundle.getString("Select_the_article_you_want_to_delete"));
								logger.info(this.bundle.getString("Exit_the_Eliminating_article_option"));

								optionEliminate = scan.nextInt();
								scan.nextLine();
								if (optionEliminate == 1 || optionEliminate == 2) {
									if (optionEliminate == 1) {
										logger.info(this.bundle.getString("Select_the_article_you_want_to_read"));
										int optionRead = scan.nextInt();
										scan.nextLine();
										logger.info(ownArticles.get(optionRead).toString());
									} else {
										logger.info(this.bundle.getString("Your_articles"));
										for (int m = 0; m < 10 && m < ownArticles.size(); m++) {
											logger.info(this.bundle.getString("Article") + m + ":" + ownArticles.get(m).getTitle());
										}
										logger.info(this.bundle.getString("Select_the_article_you_want_to_delete"));
										int optionDelete = scan.nextInt();
										scan.nextLine();
										Boolean okey = server.deleteArticle(ownArticles.get(optionDelete), adm);
										logger.info(this.bundle.getString("The_process_of_deleting_the_article") + okey);
									}
								} else {
									logger.info(this.bundle.getString("Exiting_the_Eliminating_article_option..."));
								}
							} while (optionEliminate != 3);
						} else {
							// You dont have articles
							logger.info(this.bundle.getString("You_don't_have_articles"));
						}
						break;
					case 3:
						// Edit article
						break;
					case 4:
						// See yours articles
						ownArticles = server.searchArticleAuthor(adm.username);
						if (ownArticles.size() > 0 && ownArticles != null) {
							// You have own articles
							logger.debug("Size of the articles: " + ownArticles.size());
							logger.info(this.bundle.getString("Your_articles"));
							for (int m = 0; m < 10 && m < ownArticles.size(); m++) {
								logger.info(this.bundle.getString("Article") + m + ":" + ownArticles.get(m).getTitle());
							}
						} else {
							// You dont have articles
							logger.info(this.bundle.getString("You_don't_have_articles"));
						}
						break;
				}

			} else if (option2 == 5) {
				logger.info(this.bundle.getString("Logging_out..."));
			} else {
				logger.info(this.bundle.getString("Option_error"));
			}

		} while (option2 != 5);
	}
}
