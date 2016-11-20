package proxy;

import java.net.InetAddress;

public class Utente {
	private String nick;
	private InetAddress address;
	private int port;
	
	public Utente(String nick, InetAddress address, int port) {
		this.nick = nick;
		this.address = address;
		this.port = port;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public InetAddress getAddress() {
		return address;
	}

	public void setAddress(InetAddress address) {
		this.address = address;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}
	
	public String toString(){
		return "Nickname: "+this.nick+"[ "+this.address+"/"+this.port+" ]";
		
	}
	
	
	
}
