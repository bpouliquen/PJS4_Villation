package ressources;

import java.io.IOException;
import java.io.Serializable;

/**
 * Interface d'un bloc entrées/sorties pour un socket
 * 
 * @author Octave M., Erwan P. Geoffrey A., Tristan H., John B., Brieuc P.
 * @version 0.0.1
 */
public interface IOOStream {
	
	/**
	 * Procédure envoyant un paquet
	 * 
	 * @param paquet
	 *            Information
	 * @throws IOException e
	 */
	public abstract void writeObject(Object paquet) throws IOException;

	/**
	 * Procédure réceptionnant un paquet
	 * 
	 * @return paquet Information
	 * @throws ClassNotFoundException e1
	 * @throws IOException e2
	 */
	public abstract Object readObject() throws ClassNotFoundException, IOException;

	/**
	 * Procédure terminant la connexion
	 */
	public abstract void close();
}
