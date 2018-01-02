package Users;

import java.io.Serializable;

public class User implements Serializable
{
	//variables
	private int UserID;
	private String UserName;
	private String Password;
	private String Permition;
	private boolean Status;

	//Constructor
	public User(int id, String UserName, String password, String Permition, int Status) 
	{
		this.UserID = id;
		this.UserName = UserName;
		this.Password = password;
		this.Permition = Permition;
		if(Status==1) this.Status = true;
		else  this.Status =false;
	}

	//getters and setters:
	public int getId() {
		return this.UserID;
	}

	public void setId(int id) {
		this.UserID = id;
		System.out.println("ID set to "+id);
	}

	public String getUserName() {
		return this.UserName;
	}

	public void setUserName(String UserName) {
		this.UserName = UserName;
		System.out.println("UserName set to "+UserName);
	}

	
	public String getPassword() {
		return this.Password;
	}

	
	public void setPassword(String Password) {
		this.Password = Password;
		System.out.println("Password set to "+Password);
	}
	
	public String getPermition() {
		return this.Permition;
	}

	
	public void setPermition(String Permition) {
		this.Permition = Permition;
		System.out.println("Permition set to "+Permition);
	}
	
	public Boolean getStatus() {
		return this.Status;
	}

	
	public void setPermition(Boolean Status) {
		this.Status = Status;
		if(Status==true)
		System.out.println("Status set to not block");
		else
			System.out.println("Status set to block");
	}
	
	
	//tostring method:
	public String toString(){
		String AllDetails= ""+UserID+ " "+UserName+ " "+ Password + " " + Permition+ " "+Status;
		return AllDetails;
	}

}
