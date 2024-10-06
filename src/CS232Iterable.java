

/**
 * Interface used by an ADT to indicate that a CS232Iterator can be obtained for
 * the data structure.
 * 
 * @author Grant Braught
 * @author Dickinson College
 * @version Feb 18, 2016
 */
public interface CS232Iterable<E> {

	/**
	 * Get a CS232Iterator for the elements of this data structure.
	 * 
	 * @return a CS232Iterator for the data structure.
	 */
	public CS232Iterator<E> getIterator();
}
