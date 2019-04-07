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
	
	
	public User(String username, String password, String email) {
		this.username = username;
		this.password = password;
		this.email = email;
	}
	
	protected User() {}
	public String getLogin() {
		return this.username;
	}
	
	public String getPassword() {
		return this.password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getEmail() {
		return this.email;
	}
	
    /**
     * Implementation of equals method (JDO requirement).
     **/
    public boolean equals(User obj)
    {
        if (obj.username.equals(this.username))
        {
            return true;
        }
        return false;
    }

    /**
     * Implementation of hashCode (JDO requirement)
     */
    public int hashCode ()
    {
        return this.username.hashCode() * this.password.hashCode();
    }

    /**
     * Implementation of toString that outputs this object id's PK values.
     * (JDO requirement).
     **/
    public String toString ()
    {
        return this.username + "::" + this.password;
    }
}

