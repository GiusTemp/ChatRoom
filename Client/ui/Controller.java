package ui;

import model.Client;

/**
 * @author: Tempesta Giuseppe
 * @version: 0.1
 * Created: 20/11/2016
 *
 * **/

public class Controller {
	
	private Client client;
	//private UserInteractor us;

	public Controller() {
		this.client = new Client();
		//this.us=new UserInteractor();
	}
	
	/*public UserInteractor getUs() {
		return us;
	}*/

	public void registra(String nick) {
		this.client.registra(nick);
	}
	
	public void deregistra() {
		this.client.deregistra();
	}
	
	public void send(String msg) {
		this.client.sendMessage(msg);
	}
	
	public String recive() {
		return this.client.getMessage();
		
	}
}

