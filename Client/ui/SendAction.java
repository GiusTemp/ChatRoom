package ui;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JTextArea;

public class SendAction extends AbstractAction
{
	private static final long serialVersionUID = 1L;
	
	private String messaggio;
	private JTextArea message;
	private JTextArea chatComune;
	private Controller controller;
	
	

	public SendAction(String messaggio, JTextArea message, JTextArea chatComune, Controller controller) {
		this.messaggio = messaggio;
		this.message = message;
		this.chatComune = chatComune;
		this.controller = controller;
	}



	@Override
	public void actionPerformed(ActionEvent e) {
		//message.setText("");
		controller.send(messaggio);
		chatComune.append("[Tu]: "+messaggio+"\n");
		message.setText("");
		
	}
}
