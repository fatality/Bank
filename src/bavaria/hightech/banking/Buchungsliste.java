package bavaria.hightech.banking;

import java.util.ArrayList;
import java.util.List;

/**
 * @author mrochow
 */
public class Buchungsliste {
	
	List<Buchung> postingList;
	private int counter;
	
	/**
	 * Default constructor
	 */
	public Buchungsliste() {
		postingList = new ArrayList<Buchung>();
	}
	
	/**
	 * Adds a new </code>Buchung</code>
	 * 
	 * @param b: <code>Buchung</code>
	 */
	public void add(Buchung b) {
		postingList.add(b);
	}
	
	/**
	 * Clears the posting list
	 */
	public void clear() {
		postingList.clear();
	}
	
	/**
	 * Returns the next element
	 * 
	 * @return the next <code>Buchung</code>
	 */
	public Buchung next() {
		if (counter >= postingList.size()) return null;
		Buchung b = postingList.get(counter);
		counter++;
		return b;
	}
	
	/**
	 * Resets the iteration counter
	 */
	public void resetIteration() {
		counter = 0;
	}
	
	/**
	 * Prints the desired informations
	 * 
	 * @return <code>String</code>
	 */
	@Override
	public String toString() {
		String s = "\n";
		s += "Bu-Ta     Erläuterungen     Betrag\n";
		for (Buchung element : postingList) {
			s += element.toString() + "\n";
		}
		s += "-----------------------------------\n";
		return s;
	}
	
}
