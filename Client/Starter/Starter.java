package Starter;

import java.awt.Dimension;

import ui.Controller;
import ui.FirstFrame;

/**
 * @author: Felicetti Davide, Tempesta Giuseppe
 * @version: 0.1
 * Created: 20/11/2016
 *
 * **/

public class Starter {
	public static void main(String[] args){
		Controller controller=new Controller();
		FirstFrame ff=new FirstFrame(controller);
				
		ff.setVisible(true);
		ff.setSize(new Dimension(350,200));
		ff.setLocationRelativeTo(null);
		ff.setTitle(" ChatGroup S.p.A  ©");	
	}
}
