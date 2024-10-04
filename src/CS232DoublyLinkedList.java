import org.junit.Test;

/**
 * Doubly linked list implementation of the CS232List interface.
 * 
 * @author Grant Braught
 * @author Dickinson College
 * @version Feb 18, 2016
 */
public class CS232DoublyLinkedList<E> implements CS232List<E> {

	private DLLNode head;
	private DLLNode tail;
	private int size;

	/**
	 * Construct a new empty CS232DoublyLinkedList.
	 */
	public CS232DoublyLinkedList() {
		/*
		 * This implementation uses dummy head and tail nodes to simplify the
		 * implementation of insert/remove/add operations at the start or end of
		 * the list.
		 */
		head = new DLLNode(null, null, null);
		tail = new DLLNode(null, head, null);
		head.next = tail;
		size = 0;
	}

	/**
	 * {@inheritDoc}
	 */
	public int size() {
		return size;
	}

	/**
	 * {@inheritDoc}
	 */
	public void add(E element) {
		DLLNode pred = tail.prev;
		DLLNode node = new DLLNode(element, pred, tail);
		pred.next = node;
		tail.prev = node;

		size++;
	}

	/*
	 * Helper method to get the DLLNode at the specified index.
	 * 
	 * Throws exception if the index is not valid.
	 */
	private DLLNode getNode(int index) {
		checkBounds(index);
		DLLNode cur = head.next;
		for (int i = 0; i < index; i++) {
			cur = cur.next;
		}
		return cur;
	}

	/*
	 * Helper method that throws an exception if the index is not valid.
	 */
	private void checkBounds(int index) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException("Invalid index: " + index
					+ " on List of size " + size + ".");
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public E get(int index) throws IndexOutOfBoundsException {
		DLLNode node = getNode(index);
		return node.element;
	}

	/**
	 * {@inheritDoc}
	 */
	public void set(int index, E element) throws IndexOutOfBoundsException {
		DLLNode node = getNode(index);
		node.element = element;
	}

	/**
	 * {@inheritDoc}
	 */
	public void insert(int index, E element) throws IndexOutOfBoundsException {
		/*
		 * If the index is passed the end of the list, then tail will succeed
		 * (appear immediately after) the new node. Otherwise, the node at index
		 * succeeds the new node.  Need this because, getNode throws an exception
		 * when index is out of range.
		 */
		DLLNode succ = tail;
		if (index != size) {
			succ = getNode(index);
		}

		// Whatever succ points to now comes after new node.
		DLLNode node = new DLLNode(element, succ.prev, succ);
		succ.prev.next = node;
		succ.prev = node;

		size++;
	}

	/**
	 * {@inheritDoc}
	 */
	public E remove(int index) throws IndexOutOfBoundsException {
		
		
		DLLNode node = getNode(index);
		DLLNode prev = node.prev;
		DLLNode next = node.next;

		if(node == tail) {	
			prev = tail;
		}
		if(node == head) {
			next = head;
		}
		else {
		prev.next = next;
		next.prev = prev;

		size--;
		}
		
		return node.element;
	}

	/**
	 * Clear all elements of the list up to and including index. The element, if
	 * any, at index+1 becomes the first element in the list.
	 * 
	 * @param index
	 *            the index up to which to clear the list.
	 * @throws IndexOutOfBoundsException
	 *             if index < 0 or index >= size()
	 */
	public void clearTo(int index) throws IndexOutOfBoundsException {
		if(index < 0 || index >= size()) {
			throw new IndexOutOfBoundsException("Index out of bounds.");
		}
		DLLNode node = getNode(index).next;
	
		if (node == tail) {
			head.next = node;
			node.prev = head;
			size = 0;
		}
		else {
			//node.prev = null;
			head.next = node;
			size = size - index-1;
		}
	}

	/**
	 * Add all of the elements of the provided list into this list. The first
	 * element of the provided list appears at the specified index in this list.
	 * The element at index, if any, in this list appears immediately following
	 * the last element of list. Note that it is possible to addAllAt the end of
	 * the list by providing the list's size as the index.
	 * 
	 * @param index
	 *            the index at which to add the elements.
	 * @param list
	 *            the list containing the elements to be added.
	 * @throws IndexOutOfBoundsException
	 *             if index < 0 or index > size()
	 * @throws IllegalArgumentException
	 *             if list is empty.
	 */
	public void addAllAt(int index, CS232DoublyLinkedList<E> list)
			throws IndexOutOfBoundsException {
				if (list.size == 0) {
					throw new IllegalArgumentException("The list is empty.");
				}
				if (index < 0 || index > size()) {
					throw new IndexOutOfBoundsException("Index out of bounds.");
				}
				
				if(index == 0) {
					DLLNode start = list.head.next;
					DLLNode node = head.next;
					DLLNode end = list.tail.prev;
					
					
					head.next = start;
					start.prev = head;
					
					end.next = node;
					node.prev = end;
					size += list.size;
				}
				else if(index == size) {
					DLLNode last = tail.prev;
					DLLNode start = list.head.next;
					DLLNode end = list.tail.prev;
					
					
					last.next = start;
					start.prev = last;
					end.next = tail;
					tail.prev = end;
					
					size += list.size;
				}
				else {
					DLLNode last = getNode(index-1);
					DLLNode node = getNode(index);
					DLLNode start = list.head.next;
					DLLNode end = list.tail.prev;
					
					last.next = start;
					start.prev = last;
					
					end.next = node;
					node.prev = end;
					
					size += list.size;

				}

	}

	/*
	 * Defines the node object for the doubly linked list. Note: Fields are
	 * public so that they can be accessed directly rather than via accessors
	 * and mutators. This make the implementations of the doubly linked list
	 * methods above easier to implement and read. And because the DLLNode class
	 * is private to this class it is not an egregious violation of
	 * encapsulation.
	 */
	private class DLLNode {
		public E element;
		public DLLNode prev;
		public DLLNode next;

		public DLLNode(E element, DLLNode prev, DLLNode next) {
			this.element = element;
			this.prev = prev;
			this.next = next;
		}
	}

	/**
	 * Helper method for testing that checks that all of the links are
	 * symmetric.
	 * 
	 * @return true if all of the links are correct.
	 */
	public boolean checkListIntegrity() {
		if (size == 0) {
			return head.next == tail && tail.prev == head;
		} else {
			DLLNode cur = head.next;

			while (cur.next != tail) {

				DLLNode prev = cur.prev;
				DLLNode next = cur.next;

				if (prev.next != cur || next.prev != cur) {
					return false;
				}

				cur = cur.next;
			}
		}
		return true;
	}
	
	
	public static void main(String args[]) {
		CS232DoublyLinkedList<String> myList = new CS232DoublyLinkedList<String>();
        myList.add("Test1");
        myList.add("Test2");
        myList.add("Test3");
        myList.add("Test4");
        myList.add("Test5");
        
        CS232DoublyLinkedList<String> addList = new CS232DoublyLinkedList<String>();
    	addList.add("new1");
    	addList.add("new2");
    	addList.add("new3");
    	myList.addAllAt(0, addList);
    	
    	System.out.println(myList.size());
    	System.out.println(myList.checkListIntegrity());
    	System.out.println(myList.get(0));
    	System.out.println(myList.get(1));
    	System.out.println(myList.get(2));
    	System.out.println(myList.get(3));
    	System.out.println(myList.get(4));
    	System.out.println(myList.get(5));
    	System.out.println(myList.get(6));
    	System.out.println(myList.get(7));
    	
	}
}
