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

	/**
	 * @param face
	 * @return
	 */
	public static Face getFace(int face) {
		switch(face) {
		default:
			break;
		case 2:
			return TWO;
		case 3:
			return THREE;
		case 4:
			return FOUR;
		case 5:
			return FIVE;
		case 6:
			return SIX;
		case 7:
			return SEVEN;
		case 8:
			return EIGHT;
		case 9:
			return NINE;
		case 10:
			return TEN;
		case 11:
			return JACK;
		case 12:
			return QUEEN;
		case 13:
			return KING;
		case 14:
			return ACE;
		}
		return null;
	}
}
