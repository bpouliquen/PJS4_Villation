package Reseau.serveur;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServeurGeneral implements Runnable{

	private ServerSocket serveur;
	private AppliServeur app;
	public ServeurGeneral(int port, AppliServeur app){
		this.app=app;
		try {
			serveur=new ServerSocket(port);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void run(){
		while(true){
			try {
				Socket temp=serveur.accept();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
