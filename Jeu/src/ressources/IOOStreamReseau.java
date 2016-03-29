package ressources;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Classe instanciant un bloc entrées/sorties pour un joueur distant
 * 
 * @author Octave M., Erwan P. Geoffrey A., Tristan H., John B., Brieuc P.
 * @version 0.0.1
 */
public class IOOStreamReseau implements IOOStream {
	private static final long serialVersionUID = 1L;
	private ObjectInputStream ois;
	private ObjectOutputStream oos;

	/**
	 * Constructeur
	 * 
	 * @param s Socket
	 */
	public IOOStreamReseau(Socket s) {
		try {
			oos = new ObjectOutputStream(s.getOutputStream());
			oos.flush();
			ois = new ObjectInputStream(s.getInputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void writeObject(Object paquet) throws IOException {
		oos.writeObject(paquet);
	}

	@Override
	public Object readObject() throws ClassNotFoundException, IOException {
		Object temp = ois.readObject();
		return temp;
	}

	@Override
	public void close() {
		try {
			oos.close();
			ois.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
