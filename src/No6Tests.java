

import static org.junit.Assert.*;

import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class No6Tests {
	private CS232Iterator<String> it;
	private CS232IterableDoublyLinkedList<String> myList;

	@Before
	public void setUp() throws Exception {
		myList = new CS232IterableDoublyLinkedList<String>();
		it = ((CS232Iterable<String>) myList).getIterator();
		buildList();
	}

	private void buildList() {
		String[] list = { "one", "two", "three", "four", "five" };
		for (String s : list) {
			myList.add(s);
		}
	}

	@Test
	public void testInitialHasPrevious() {
		assertFalse("should not have previous", it.hasPrevious());

		assertTrue("Not all links are correct", myList.checkListIntegrity());
	}

	@Test
	public void testPrevious() {
		it.next();
		assertEquals("Incorrect value from previous", "one", it.previous());

		assertTrue("Not all links are correct", myList.checkListIntegrity());
	}

	@Test
	public void testNextPreviousSequence() {
		it.next();
		it.next();
		assertEquals("Incorect value", "two", it.previous());
		assertEquals("Incorect value", "two", it.next());

		assertTrue("Not all links are correct", myList.checkListIntegrity());

		it.next();
		it.previous();
		assertEquals("Incorrect value", "two", it.previous());
		assertEquals("Incorrect value", "one", it.previous());

		assertTrue("Not all links are correct", myList.checkListIntegrity());
	}

	@Test
	public void testPreviousHasPrevious() {
		it.next();
		assertTrue("Should have a previous", it.hasPrevious());

		while (it.hasNext()) {
			it.next();
		}

		for (int i = 0; i < myList.size(); i++) {
			assertTrue("should have previous", it.hasPrevious());
			assertTrue("Not all links are correct", myList.checkListIntegrity());
			it.previous();
		}

		assertFalse("Should not a previous", it.hasPrevious());

		assertTrue("Not all links are correct", myList.checkListIntegrity());
	}

	@Test
	public void testPreviousExceptionInitally() {
		try {
			it.previous();
			fail("Should throw NoSuchElementException.");
		} catch (NoSuchElementException e) {
			// pass.
		} catch (Exception e) {
			fail("Threw incorrect exception type, should be NoSuchElementException.");
		}
		
		assertTrue("Not all links are correct", myList.checkListIntegrity());
	}

	@Test
	public void testPreviousExceptionAfterMoves() {
		it.next();
		it.previous();

		try {
			it.previous();
			fail("Should throw NoSuchElementException.");
		} catch (NoSuchElementException e) {
			// pass.
		} catch (Exception e) {
			fail("Threw incorrect exception type, should be NoSuchElementException.");
		}
		
		assertTrue("Not all links are correct", myList.checkListIntegrity());
	}

	@Test
	public void testRemoveFirst() {
		it.next();
		String s = it.remove();
		
		assertEquals("Incorrect value removed", "one", s);
		assertFalse("should not be a previous", it.hasPrevious());
		assertEquals("incorrect list size after remove", 4, myList.size());
		assertEquals("incorrect value at index 0 after remove", "two", myList.get(0));
		assertEquals("incorrect next item after remove", "two", it.next());
		
		assertTrue("Not all links are correct", myList.checkListIntegrity());
	}
	
	@Test
	public void testRemoveMiddle() {
		it.next();  // one
		it.next();  // two
		it.next();  // three
		String s = it.remove();
		
		assertEquals("Incorrect value removed", "three", s);
		assertTrue("should be a previous", it.hasPrevious());
		assertEquals("incorrect list size after remove", 4, myList.size());
		assertEquals("incorrect value at index 2 after remove", "four", myList.get(2));
		assertEquals("incorrect next item after remove", "four", it.next());
		
		assertTrue("Not all links are correct", myList.checkListIntegrity());
	}
	
	@Test
	public void testRemoveLast() {
		while(it.hasNext()) {
			it.next();
		}
		String s = it.remove();
		
		assertEquals("Incorrect value removed", "five", s);
		assertTrue("should be a previous", it.hasPrevious());
		assertFalse("should not be a next", it.hasNext());
		
		assertEquals("incorrect list size after remove", 4, myList.size());
		assertEquals("incorrect value at index 4 after remove", "four", myList.get(3));
		assertEquals("incorrect previous item after remove", "four", it.previous());
		
		assertTrue("Not all links are correct", myList.checkListIntegrity());
	}
	
	@Test
	public void testRemoveAfterPrevious() {
		it.next(); // one
		it.next(); // two
		it.next(); // three
		it.previous(); // three
		it.remove();

		assertTrue("should be a previous", it.hasPrevious());
		assertEquals("incorrect list size after remove", 4, myList.size());
		assertEquals("incorrect value at index 2 after remove", "four",
				myList.get(2));
		assertEquals("incorrect next item after remove", "four", it.next());

		assertTrue("Not all links are correct", myList.checkListIntegrity());
	}
	
	@Test
	public void testTwoRemovesInARow() {
		it.next(); // one
		it.next(); // two
		it.next(); // three
		it.remove();
		
		try {
			it.remove();
			fail("Should throw exception on second remove.");
		}
		catch(IllegalStateException e) {
			assertTrue("Not all links are correct", myList.checkListIntegrity());
		}
		catch(Exception e) {
			fail("Incorrect exception type thrown");
		}
	}
	
	@Test
	public void testNextResetsRemove() {
		it.next(); // one
		it.next(); // two
		it.next(); // three
		it.remove();
		it.next(); // four
		
		try {
			it.remove();
		}
		catch(Exception e) {
			fail("A call to next should allow another call to next.");
		}
		
		assertEquals("incorrect list size after removes", 3, myList.size());
		assertEquals("incorrect value at index 0 after removes", "one",
				myList.get(0));
		assertEquals("incorrect value at index 1 after removes", "two",
				myList.get(1));
		assertEquals("incorrect value at index 2 after removes", "five",
				myList.get(2));
		
		assertTrue("Not all links are correct", myList.checkListIntegrity());
	}
	
	@Test
	public void testPreviousResetsRemove() {
		it.next(); // one
		it.next(); // two
		it.next(); // three
		it.remove();
		it.previous(); // two
		
		try {
			it.remove();
		}
		catch(Exception e) {
			fail("A call to next should allow another call to next.");
		}
		
		assertEquals("incorrect list size after removes", 3, myList.size());
		assertEquals("incorrect value at index 0 after removes", "one",
				myList.get(0));
		assertEquals("incorrect value at index 1 after removes", "four",
				myList.get(1));
		assertEquals("incorrect value at index 2 after removes", "five",
				myList.get(2));
		
		assertTrue("Not all links are correct", myList.checkListIntegrity());
	}
}
