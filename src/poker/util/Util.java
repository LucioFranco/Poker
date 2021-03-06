/**
 * 
 */
package poker.util;

import java.util.Random;

/**
 * @author luciofranco
 *
 */
public abstract class Util {
	public static int randInt(int min, int max) {

	    // Usually this can be a field rather than a method variable
	    Random rand = new Random();

	    // nextInt is normally exclusive of the top value,
	    // so add 1 to make it inclusive
	    int randomNum = rand.nextInt((max - min) + 1) + min;

	    return randomNum;
	}
	
	public static String[] trimTopOfMessageArray(String[] tempstr1) {
		String[] tempstr = new String[tempstr1.length - 1];
		for(int i = 0; i < tempstr.length; i++) {
			tempstr[i] = tempstr1[i + 1];
		}
		return tempstr;
	}
}
