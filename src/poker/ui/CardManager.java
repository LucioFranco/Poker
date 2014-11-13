/**
 * 
 */
package poker.ui;

import java.awt.Image;
import java.awt.Toolkit;

import poker.base.Card;
import poker.base.Face;
import poker.base.Suit;

public class CardManager {
	public static Image getCardImage(Card card) {
		if(card == null || card.isNull()) {
			return Toolkit.getDefaultToolkit().getImage("res/cards/b1fv.png");
		}
		return Toolkit.getDefaultToolkit().getImage("res/cards/" + getCardImageID(card.getFace(), card.getSuit()) + ".png");
	}
	
	private static int getCardImageID(Face face, Suit suit) {
		int suitnum = 0;
		int facenum = 0;
		
		switch(suit) {
		case CLUBS:
			suitnum = 0;
			break;
		case SPADES:
			suitnum = 1;
			break;
		case HEARTS:
			suitnum = 2;
			break;
		case DIAMONDS:
			suitnum = 3;
			break;
		}
		
		switch(face) {
		case ACE:
			facenum = 1;
			break;
		case KING:
			facenum = 5;
			break;
		case QUEEN:
			facenum = 9;
			break;
		case JACK:
			facenum = 13;
			break;
		case TEN:
			facenum = 17;
			break;
		case NINE:
			facenum = 21;
			break;
		case EIGHT:
			facenum = 25;
			break;
		case SEVEN:
			facenum = 29;
			break;
		case SIX:
			facenum = 33;
			break;
		case FIVE:
			facenum = 37;
			break;
		case FOUR:
			facenum = 41;
			break;
		case THREE:
			facenum = 45;
			break;
		case TWO:
			facenum = 49;
			break;
		}
		
		return facenum + suitnum;
	}
}
