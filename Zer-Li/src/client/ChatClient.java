package client;

import ocsf.client.*;
import common.*;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import java.io.*;
import java.util.ArrayList;

import Users.LoginContol;
import Users.User;

public class ChatClient extends AbstractClient {
	// Instance variables **********************************************

	// Constructors ****************************************************

	public ChatClient(String host, int port) throws IOException {
		super(host, port); // Call the superclass constructor
	}

	// Instance methods ************************************************

	public void handleMessageFromServer(Object msg) 
	{
		Message ServerMsg;
		ServerMsg = (Message) msg;
		if (ServerMsg.getMsgType().equals("User")) 
		{
			ArrayList<User> AllUsersFromServer = (ArrayList<User>) ServerMsg.getMsgObject();
			for (int i = 0; i < AllUsersFromServer.size(); i++) 
			{
				System.out.println(""+AllUsersFromServer.get(i));
				LoginContol.AllUsers.add(AllUsersFromServer.get(i));
			}

			quit();
			return;
			// }
		}
	}

	public void sendRequestToGetAllUsers() {
		try {
			this.openConnection();
		}

		catch (IOException e1) {
			System.out.println("Cannot open connection");
		}

		try {
			sendToServer("Give Me All Users");
		} catch (IOException e) {
			System.out.println("Cannot connect to server");

		}

	}

	public void quit() {
		try {
			closeConnection();
		} catch (IOException e) {
			System.out.println("Cannot close connection");
		}
	}

}