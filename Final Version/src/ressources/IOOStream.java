package ressources;

import java.io.IOException;

/**
 * Interface d'un bloc entr�es/sorties pour un socket
 * 
 * @author Octave M., Erwan P. Geoffrey A., Tristan H., John B., Brieuc P.
 * @version 0.0.1
 */
public interface IOOStream {

	/**
	 * Proc�dure envoyant un paquet
	 * 
	 * @param paquet
	 *            Information
	 * @throws IOException e
	 */
	public abstract void writeObject(Object paquet) throws IOException;

	/**
	 * Proc�dure r�ceptionnant un paquet
	 * 
	 * @return paquet Information
	 * @throws ClassNotFoundException e1
	 * @throws IOException e2
	 */
	public abstract Object readObject() throws ClassNotFoundException, IOException;

	/**
	 * Proc�dure terminant la connexion
	 */
	public abstract void close();
}
