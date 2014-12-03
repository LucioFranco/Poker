/**
 * 
 */
package poker.execptions;

/**
 * exception is throw when the list of players has one player that is null
 *
 */
public class NullPlayerListException extends Exception {

	private static final long serialVersionUID = 1L;

	/**
	 * @param message
	 */
	public NullPlayerListException(String message) {
		super(message);
	}
}
