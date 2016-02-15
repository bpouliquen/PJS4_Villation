package Reseau.serveur;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.LinkedList;

import Reseau.socketPerso.IOOStreamLocal;
import Reseau.socketPerso.IOOStreamReseau;
import Reseau.socketPerso.Information;

public class Client implements Runnable{

	private IOOStreamLocal ioos;
	public Client(IOOStreamLocal temp){
		ioos=new IOOStreamLocal(temp);
	}
	@Override
	public void run() {
		System.out.println(ioos.readObject());
		ioos.writeObject(new Information("Je suis un client local"));
		try {
			Thread.sleep(5000);

			ioos.writeObject(new Information("mais tg"));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
