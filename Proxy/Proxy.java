package proxy;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.StringTokenizer;


public class Proxy {
	
	public static void main(String[] args) {
		int porta_proxy = 8000;
		byte bufR[] = new byte[512];
		InetAddress address = null;
		DatagramSocket socket = null;
		ArrayList<Utente> utenti = new ArrayList<>();
		
		start();
		try {
			address = InetAddress.getByName("192.168.1.111");
			socket = new DatagramSocket(porta_proxy, address);
		} catch (SocketException  | UnknownHostException e2) {
			e2.printStackTrace();
		}
		try {
			
			while(true) {
				DatagramPacket pack = new DatagramPacket(bufR, bufR.length);
				socket.receive(pack);
				inoltra(pack, utenti,socket);
			}
				
		} catch (IOException e) {
			e.printStackTrace();
		}
		socket.close();

	}
	
	public static void start() {
		System.out.println("______________________________________");
		System.out.println("|                                     |");
		System.out.println("|  Proxy server correttamente avviato |");
		System.out.println("|             PORTA: 8000             |");
		System.out.println("|       INDIRIZZO:  192.168.1.111     |");
		System.out.println("|_____________________________________|\n");
		
	}
	
	public static void inoltra(DatagramPacket pack, ArrayList<Utente>  utenti, DatagramSocket socket) {
		int i,pos = 0, flag=0;
		ByteArrayInputStream inputStream = new ByteArrayInputStream(pack.getData());
		DataInputStream in = new DataInputStream(inputStream);
		ByteArrayOutputStream outbyte = new ByteArrayOutputStream();
		DataOutputStream out = new DataOutputStream(outbyte);
		String contenuto = null;
		
		try {
			contenuto = in.readUTF();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if( contenuto.startsWith("REGISTRAZIONE")) {
			StringTokenizer tok = new StringTokenizer(contenuto);
			tok.nextToken(":");
			String nick = tok.nextToken();
			
			
			InetAddress address = pack.getAddress();
			int porta = pack.getPort();
			
			utenti.add(new Utente(nick, address, porta));
		} 
		
		if( contenuto.startsWith("DEREGISTRAZIONE")) { 
			for(i=0; i<utenti.size(); i++)
				if( utenti.get(i).getAddress().equals(pack.getAddress()) 
						&& utenti.get(i).getPort()==pack.getPort())
						pos = i;
			utenti.remove(pos);
		}
		
		if( !contenuto.startsWith("REGISTRAZIONE") && !contenuto.startsWith("DEREGISTRAZIONE")) {
			Utente tmp = null;
			
			for(Utente x: utenti)
				if( x.getAddress().equals(pack.getAddress()) && x.getPort() == pack.getPort())
					tmp = x;
			
			String inoltro = "[ "+tmp.getNick()+" ]:  "+contenuto;
			byte bufDim[] = new byte[512];
			DatagramPacket packRisposta = null;
			try {
				out.writeUTF(inoltro);
			} catch (IOException e) {
				e.printStackTrace();
			}
			bufDim = outbyte.toByteArray();
			
			//fase di inoltro
			for(Utente x: utenti){
				if( !x.getAddress().equals(pack.getAddress()) ){
					packRisposta= new DatagramPacket(bufDim, bufDim.length, x.getAddress(), x.getPort());
					try {
						socket.send(packRisposta);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}	
			}
		
	
		try {
			in.close();
			inputStream.close();
			out.close();
			outbyte.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		flag++;
	}
		
	if( flag == 0) {
		System.out.println("Lista iscritti (" + LocalDateTime.now()+")");
		if( utenti.size() == 0)
			System.out.println("--vuoto--");
		else 
			for(Utente x: utenti)
				System.out.println(x.toString());
		System.out.println("--------------------------------");
		}
	}
	
	
}
