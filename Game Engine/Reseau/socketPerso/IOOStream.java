package Reseau.socketPerso;

import java.io.IOException;

public interface IOOStream {

	public abstract void writeObject(Information paquet) throws IOException;
	public abstract Information readObject() throws ClassNotFoundException, IOException;
	public abstract void close();

}
