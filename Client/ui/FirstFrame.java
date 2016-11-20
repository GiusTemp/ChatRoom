package ui;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.UnknownHostException;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * @author: Felicetti Davide
 * @version: 0.1
 * 
 * Created: 20/11/2016
 *
 * **/



public class FirstFrame extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	private Controller controller;
	private JButton reg;	
	private JTextField utenteField;	
	private ClientGui cg;
	
	
	public FirstFrame(Controller controller) {
		this.controller=controller;		
		initGUI();
	}
	
	
	
	private void initGUI() {
		JPanel frame=new JPanel();
		add(frame);
		
		frame.setLayout(new BoxLayout(frame, BoxLayout.PAGE_AXIS));
		JPanel title=new JPanel();
		JLabel titolo=new JLabel("Benvenuto!");		
		titolo.setForeground(Color.blue);
		titolo.setFont(new Font("Lucida Fax",Font.BOLD,18));
		title.add(titolo);
		frame.add(title);
		
		//---UP
		JPanel up=new JPanel();
		
		//Modifica il layout di default da centrale a quello che ti pare!(FlowLayout.<Posizione arbitraria tra le proposte>)
		up.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		frame.add(up);	
		
		JLabel nomeUt=new JLabel("Nome Utente");
		up.add(nomeUt);
		utenteField=new JTextField(10);
		up.add(utenteField);
			
		//------------------------------------------
		
		//---BOTTONE
		JPanel butOne=new JPanel();		
		frame.add(butOne);
		reg=new JButton("REGISTRAMI");
		reg.addActionListener(this);
		butOne.add(reg);		
				
		//------------------------------------------
		
		JPanel sot=new JPanel();
		
		JTextField diritti=new JTextField(25);
		diritti.setBackground(Color.lightGray);
		diritti.setHorizontalAlignment(JTextField.CENTER);
		diritti.setBorder(BorderFactory.createEmptyBorder());
		
		diritti.setText("All Rights Reserved ©2016, (BO)  by D&g");
		sot.add(diritti);
		frame.add(sot);
		
		
	}
	
	
	
	

	@Override
	public void actionPerformed(ActionEvent e) {
		String utente=utenteField.getText();
		if(e.getSource()==reg){
			controller.registra(utente);
			
				try {
					cg=new ClientGui(controller,utenteField.getText());
				} catch (UnknownHostException e1) {					
					e1.printStackTrace();
				}			
				cg.setVisible(true);			
			//FirstFrame sparisce e uso soltanto la ClientGUI
			this.setVisible(false);			
		}
		
		
		/*else{
			controller.deregistra();
			//il controller invia la richiesta al server che risponde con l'esito della deregistrazione
			user.showMessage("== ESITO DEREGISTRAZIONE ==\n"
					+ "* esito comunicatomi dal Server! * ");
		}*/
			
		
		
		
	}

}
