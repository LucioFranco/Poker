
public class _CardTest {
	public static void main(String[] args) {
		_Test test = new _Test();
		Card c1 = new Card(Face.QUEEN, Suit.CLUBS);
		Card c2 = new Card(Face.QUEEN, Suit.CLUBS);
		test.check("CompareTo cards that are equal", c1.compareTo(c2) == 0);
	}
}
