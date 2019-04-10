package es.deusto.server.jdo;

import java.io.Serializable;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable
public class User implements Serializable{
	
	@PrimaryKey
	public String username;
	public String password;
	public String email;
	
	/**
	 * 
	 * @param The name of the User
	 * @param The password that will use for login
	 * @param The email of that person
	 */
	public User(String username, String password, String email) {
		this.username = username;
		this.password = password;
		this.email = email;
	}
	
	protected User() {}
	
	/**
	 * 
	 * @return Gets you the username of this user
	 */
	public String getLogin() {
		return this.username;
	}
	
	/**
	 * 
	 * @return Gets you the password of this user
	 */
	public String getPassword() {
		return this.password;
	}
	
	/**
	 * 
	 * @param Set a new password of the user
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	/**
	 * 
	 * @return Set a new email for the user
	 */
	public String getEmail() {
		return this.email;
	}
	
    /**
     * Compare two users if they are the same
     * @param A user to compare
     * @return Return if the two user are equals
     */
    public boolean equals(User obj)
    {
        if (obj.username.equals(this.username))
        {
            return true;
        }
        return false;
    }

   /**
    * Hashcode for the user
    */
    public int hashCode ()
    {
        return this.username.hashCode() * this.password.hashCode();
    }
    
    /**
     * Method to print the user
     */
    public String toString ()
    {
        return this.username + "::" + this.password;
    }
}

