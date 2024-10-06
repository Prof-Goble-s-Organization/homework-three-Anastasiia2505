
import static org.junit.Assert.assertTrue;

import java.util.NoSuchElementException;

/**
 * Doubly linked list implementation of the CS232List interface.
 * 
 * @author Grant Braught
 * @author Dickinson College
 * @version Feb 18, 2016
 */
public class CS232IterableDoublyLinkedList<E> implements CS232List<E>,
		CS232Iterable<E> {

	private DLLNode head;
	private DLLNode tail;
	private int size;

	/**
	 * Construct a new empty CS232DoublyLinkedList.
	 */
	public CS232IterableDoublyLinkedList() {
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
		if (node != null) {
			return node.element;
		} else {
			return null;
		}
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
		 * If the list is empty then tail will succeed (appear immediately
		 * after) the new node. Otherwise, the node at index succeeds the new
		 * node.
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
		// Intentionally not implemented... see HW assignment!
		return null;
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
	 * {@inheritDoc}
	 */
	public CS232Iterator<E> getIterator() {
		return new DLLIterator();
	}

	/*
	 * Iterator implementation for the doubly linked list.
	 */
	private class DLLIterator implements CS232Iterator<E> {

		private DLLNode cursor;
		private boolean canRemove;

		public DLLIterator() {
			cursor = head;
			canRemove = false; 
		}

		public boolean hasNext() {
			if(cursor == tail) {
				return false;
			}
			return cursor.next != tail;
		}

		public E next() {
			if (!hasNext()) {
				throw new NoSuchElementException("There is no next element.");
			} else {
				cursor = cursor.next;
				canRemove = true;
				return cursor.element;
			}
		}

		public boolean hasPrevious() {
			if(cursor == head) {
				return false;
			}
			else {
				return cursor.prev != head;
			}
		}

		public E previous() {
			// Intentionally not implemented, see HW assignment!
			//throw new UnsupportedOperationException("Not implemented");
			if(!hasPrevious()) {
				throw new NoSuchElementException("There is no previous element.");
			}

			else {
				cursor = cursor.prev;
				canRemove = true;
				return cursor.element;
			}
		}

		public void insert(E element) {
			DLLNode node = new DLLNode(element, cursor, cursor.next);
			cursor.next.prev = node;
			cursor.next = node;
			cursor = node;
			size++;
		} 

		public E remove() {
			// Intentionally not implemented, see HW assignment!
			//throw new UnsupportedOperationException("Not implemented");
			if (!canRemove) {
		        throw new IllegalStateException("Cannot remove element before next() or previous() is called.");
		    }
			
			DLLNode temp = cursor;
			DLLNode next = cursor.next;
			DLLNode prev = cursor.prev;
			
			
			if (prev == head) {
				head.next = next;
				next.prev = head;
				cursor = next;
			}
			else if (next == tail) {
				prev.next = tail;
				tail.prev = prev;
				cursor = tail;
			}
			
			else {
				next.prev = prev;
				prev.next = next;
				cursor = next;
			}
			size--;
			canRemove = false;
			return temp.element;
			
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
	
	public static void main(String[] args) {
		CS232IterableDoublyLinkedList myList = new CS232IterableDoublyLinkedList<String>();
		myList.add("one");
		myList.add("two");
		myList.add("three");
		myList.add("four");
		myList.add("five");
		CS232Iterator<String> it = ((CS232Iterable<String>) myList).getIterator();

		it.next(); //one
		it.next(); //two
		
		while (it.hasNext()) {
			it.next(); //five
		}
		for (int i = 0; i < myList.size()-1; i++) {
			it.previous();
		}
		
		System.out.println(it.next());
		System.out.println(myList.checkListIntegrity());
	
}
}
