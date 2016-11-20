package model;

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

public class Client {
	private int porta_locale = 5000;
	private int porta_proxy = 8000;
	private InetAddress address_proxy = null;
	private InetAddress address = null;
	private DatagramSocket socket = null;
		
	public Client(){
		System.out.println("Client correttamente avviato");
		try {
			address = InetAddress.getByName("192.168.1.105");
			address_proxy = InetAddress.getByName("192.168.1.111");
			socket = new DatagramSocket(porta_locale, address);
		} catch (SocketException  | UnknownHostException e2) {
			e2.printStackTrace();
		}
	
}
	
	public void registra(String nick) {
		byte bufR[] = new byte[512];
		
		String registrazione = "REGISTRAZIONE:"+nick;
		ByteArrayOutputStream outbyte = new ByteArrayOutputStream();
		DataOutputStream out = new DataOutputStream(outbyte);
		try {
			out.writeUTF(registrazione);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		bufR = outbyte.toByteArray();
		
		DatagramPacket pack = new DatagramPacket(bufR, bufR.length, address_proxy, porta_proxy);
		try {
			socket.send(pack);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void deregistra() {
		byte bufR[] = new byte[512];
		
		String registrazione = "DEREGISTRAZIONE";
		ByteArrayOutputStream outbyte = new ByteArrayOutputStream();
		DataOutputStream out = new DataOutputStream(outbyte);
		try {
			out.writeUTF(registrazione);
		} catch (IOException e) {
			e.printStackTrace();
		}
		bufR = outbyte.toByteArray();
		
		DatagramPacket pack = new DatagramPacket(bufR, bufR.length, address_proxy, porta_proxy);
		try {
			socket.send(pack);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String getMessage() {
		byte bufR[] = new byte[512];
		String contenuto = null;
		DatagramPacket pack = new DatagramPacket(bufR, bufR.length);
		try {
			socket.receive(pack);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		ByteArrayInputStream inputStream = new ByteArrayInputStream(pack.getData());
		DataInputStream in = new DataInputStream(inputStream);
		
		try {
			contenuto = in.readUTF();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return contenuto;
	}
	
	public void sendMessage(String mess) {
		ByteArrayOutputStream outbyte = new ByteArrayOutputStream();
		DataOutputStream out = new DataOutputStream(outbyte);
		
		byte bufDim[] = new byte[512];
		DatagramPacket packRisposta = null;
		try {
			out.writeUTF(mess);
		} catch (IOException e) {
			e.printStackTrace();
		}
		bufDim = outbyte.toByteArray();
		packRisposta= new DatagramPacket(bufDim, bufDim.length, address_proxy,porta_proxy);
		try {
			socket.send(packRisposta);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}















