package Reseau.serveur;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;

import Reseau.socketPerso.IOOStream;
import Reseau.socketPerso.IOOStreamLocal;
import Reseau.socketPerso.IOOStreamReseau;
import Reseau.socketPerso.Information;

//génère le socket es serveurs, contient les socket persos
public class AppliServeur {
	private static int PORT=2000;
	private ServeurGeneral serv;
	private List<IOOStream> sock;
	
	public AppliServeur(){
		sock=new LinkedList<IOOStream>();
		serv=new ServeurGeneral(PORT, this);
	}
	
	public void add(IOOStream ois){
		sock.add(ois);
	}
	public void broadcast(Information paquet){
		for (IOOStream s:sock){
			try {
				s.writeObject(paquet);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException{
		IOOStreamLocal ioos=new IOOStreamLocal();
		Client c=new Client(ioos);
		new Thread(c).start();
		ioos.writeObject(new Information("Suce ma queue lol"));
		System.out.println(ioos.readObject());
		System.out.println(ioos.readObject());
	}
}
