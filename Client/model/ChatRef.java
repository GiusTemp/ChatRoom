package model;

import javax.swing.JTextArea;

import ui.Controller;

public class ChatRef extends Thread{
	
	private Controller controller;
	private JTextArea chatCom;
	
	public ChatRef(Controller controller, JTextArea chatCom) {
		this.controller = controller;
		this.chatCom = chatCom;
	
	}
	
	@Override
	public void run(){
		while(true){
			String s=this.controller.recive();
			System.out.println(s);
			chatCom.append(s+"\n");
		}//while
	
		
	}
	
}
