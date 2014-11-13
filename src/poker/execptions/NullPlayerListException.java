/**
 * 
 */
package poker.execptions;

/**
 * @author luciofranco
 *
 */
public class NullPlayerListException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @param message
	 */
	public NullPlayerListException(String message) {
		super(message);
	}
}
