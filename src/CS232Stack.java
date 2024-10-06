

/**
 * Generic interface specifying a Stack ADT.
 * 
 * @author Grant Braught
 * @author Dickinson College
 * @version Feb 18, 2016
 */
public interface CS232Stack<E> {

    /**
     * Add the provided element to the top of the stack.
     * 
     * @param obj the element to push onto the stack.
     */
    public void push(E element);

    /**
     * Remove the element from of the top of the stack and return it.
     * 
     * @return the element from the top of the stack or null if the stack is
     *         empty.
     */
    public E pop();

    /**
     * Return a reference to the element on the top of the stack without
     * removing it.
     * 
     * @return a reference to the element on the top of the stack or null if the
     *         stack is empty.
     */
    public E peek();
    
    /**
     * Return the number of elements contained in the stack.
     * 
     * @return the size of the stack.
     */
    public int size();
}
