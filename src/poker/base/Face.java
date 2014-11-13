package poker.base;
/**
 * 
 * @author luciofranco
 * 	represents Faces
 *	
 */

public enum Face {
	TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6), SEVEN(7), EIGHT(8), NINE(9), TEN(10), JACK(11), QUEEN(12), KING(13), ACE(14);
	
	private final int value;
	private Face(int i) {
		value = i;
	}
	
	/**
	 * 
	 * @return the value of the face
	 * 	Face.ACE.getValue() == 14
	 */
	public int getValue() {
		return this.value;
	}
}
