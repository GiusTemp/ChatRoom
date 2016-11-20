package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.net.UnknownHostException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;


import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import javax.swing.border.Border;


import model.ChatRef;

/**
 * @author: Felicetti Davide
 * @version: 0.1
 * Created: 20/11/2016
 *
 * **/


public class ClientGui extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	private Controller controller;
	private JTextArea chatComune;
	private JTextArea message;	
	private JButton esci;
	private JButton invia;
	private ChatRef ref;
	private JButton clear;
	private LocalDateTime ldt;
	private DateTimeFormatter form;
	
	
	
	public ClientGui(Controller controller,String nomeUtente) throws UnknownHostException{
		this.controller=controller;		
		this.form=DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT,FormatStyle.SHORT);
		setSize(new Dimension(850,500));
		setLocationRelativeTo(null);
		initGUI(nomeUtente);
		ref = new ChatRef(this.controller, this.chatComune);
		ref.start();
		pack();
	}
	
	
	
	
	private void initGUI(String nomeUtente) throws UnknownHostException {
		JPanel frame=new JPanel();
		this.add(frame);
		frame.setLayout(new BoxLayout(frame, BoxLayout.PAGE_AXIS));
		
		//---Parte SOPRA		
		JPanel up=new JPanel();
		up.setLayout(new BorderLayout());
		
		
		//Centrale
		JPanel central=new JPanel();//titolo chat comune+area chat+bottone
		central.setLayout(new BoxLayout(central, BoxLayout.PAGE_AXIS));
		
		JLabel l1=new JLabel();
		l1.setLayout(new FlowLayout(FlowLayout.LEFT));
		ldt=LocalDateTime.now();
		l1.setForeground(Color.red);
		l1.setText(" == Chat Comune ==   Utente Corrente: "+nomeUtente+"                  Inizio: "+ldt.format(form));
		central.add(l1);
		
		JPanel altro=new JPanel();		
		
		chatComune=new JTextArea(20,70);
		chatComune.setEditable(false);
		chatComune.setFont(new Font("Courier",Font.PLAIN,14));
		JScrollPane sp=new JScrollPane(chatComune);	
		
		Border b4=BorderFactory.createLineBorder(Color.blue,2);
		sp.setBorder(b4);
		
		altro.add(sp);		
		central.add(altro);
		
		
				
		//aggiungi bottone "esci da chat"-->deregistrazione + "chiudi chat"-->chiudo finestra chat
		JPanel exitPanel=new JPanel();
		exitPanel.setLayout(new FlowLayout());
		esci=new JButton("Esci/Chiudi chat");
		esci.addActionListener(this);
		exitPanel.add(esci);	
		
		clear=new JButton("CLEAR");
		clear.addActionListener(this);
		
		exitPanel.add(clear);
		
		central.add(exitPanel);		
		
		up.add(central,BorderLayout.CENTER);		
		
		frame.add(up);	
		//----------------------------------------
		
		JPanel down=new JPanel();
		
		JPanel mesPanel=new JPanel();
		mesPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		
		message=new JTextArea(5,60);	
//		Keymap keyMap = message.getKeymap ();
//		
//		keyMap.addActionForKeyStroke (KeyStroke.getKeyStroke (KeyEvent.VK_ENTER, 0), 
//				new SendAction (message.getText(),message,chatComune,controller));
		message.setFont(new Font("Arial",Font.ITALIC,14));
		message.setText("Scrivi qualcosa...");
		
		
		
		JScrollPane sp2=new JScrollPane(message);		
		mesPanel.add(sp2);		
		down.add(mesPanel);
		
		//Bottone invio		
		invia=new JButton("INVIA");
		invia.addActionListener(this);
		down.add(invia);
		
		
		frame.add(down);
		//------------------------------------	
		
		
	}
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String messaggio=message.getText();
		

		
		
		
		if(e.getSource()==invia){
			message.setText("");
			controller.send(messaggio);
			chatComune.append("[Tu]: "+messaggio+"\n");
		}
		
		
		//refresh chat comune
		if(e.getSource()==clear){
			chatComune.setText("");
		}
		
		
		if(e.getSource()==esci){
			this.controller.deregistra();
			System.exit(0);
			
		}
	}




	




	
	
	

}
