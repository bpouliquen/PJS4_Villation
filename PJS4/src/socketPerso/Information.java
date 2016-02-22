package socketPerso;

/**
 * Classe instanciant un paquet réseau
 * 
 * @author Octave M., Erwan P. Geoffrey A., Tristan H., John B., Brieuc P.
 * @version 0.0.1
 */
public class Information {
	
	private String message;
	
	/**
	 * Constructeur
	 */
	public Information(String mesg){
		this.message=mesg;
	}
	
	@Override
	public String toString(){
		return message; 
	}
}
