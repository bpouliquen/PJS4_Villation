package Reseau.socketPerso;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class IOOStreamReseau implements IOOStream{
	private ObjectInputStream ois;
	private ObjectOutputStream oos;
	
	public IOOStreamReseau(Socket s){
		try {
			oos=new ObjectOutputStream(s.getOutputStream());
			oos.flush();
			ois=new ObjectInputStream(s.getInputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void writeObject(Information paquet) throws IOException {
		oos.writeObject(paquet);		
	}
	public Information readObject() throws ClassNotFoundException, IOException{
		Object temp=ois.readObject();
		return (Information) temp;
	}
	public void close(){
		try {
			oos.close();
			ois.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
