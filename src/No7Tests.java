
import static org.junit.Assert.*;

import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class No7Tests {

	private CS232Iterator<String> it;
	private CS232ArrayList<String> myList;
	
	@Before
    public void setUp() throws Exception {
        myList = new CS232ArrayList<String>();
        it = myList.getIterator();
    }
    
    private void buildList() {
    	String[] list = {"one", "two", "three", "four", "five"};
    	for (String s : list) {
    		myList.add(s);
    	}
    }
    
    @Test
    public void testInitialHasNext() {
		buildList();
		assertTrue("should have next", it.hasNext());
    }
    
    @Test
    public void testInitialHasPrevious() {
		buildList();
		assertFalse("should not have previous", it.hasPrevious());
    }
    
	@Test
	public void testNext() {
		buildList();
		assertEquals("Incorrect value from next", "two", it.next()); //changed to two
	}
	
	@Test
	public void testPrevious() {
		buildList();
		it.next();
		assertEquals("Incorrect value from previous", "one", it.previous());
	}
    
	@Test
	public void testNextPreviousSequence() {
		buildList();
		it.next(); //two
		it.next(); //three
		assertEquals("Incorect value", "two", it.previous());
		assertEquals("Incorect value", "three", it.next()); //changed two to three
		
		it.next();
		it.previous();
		assertEquals("Incorrect value", "two", it.previous());
		assertEquals("Incorrect value", "one", it.previous());
	}
	
	@Test
	public void testNextHasNext() {
		buildList();
		for (int i=0; i<myList.size()-1; i++) { //changed to -1
			assertTrue("should have next", it.hasNext());
			it.next();
		}
		assertFalse("should not have next", it.hasNext());
	}
	
	@Test 
	public void testPreviousHasPrevious() {
		buildList();
		it.next();
		assertTrue("Should have a previous", it.hasPrevious());
		
		while (it.hasNext()) {
			it.next();
		}
		
		for (int i=0; i<myList.size()-1; i++) {
			assertTrue("should have previous", it.hasPrevious());
			it.previous();
		}
		
		assertFalse("Should not a previous", it.hasPrevious());
	}
	
	@Test
	public void testNextException() {
		buildList();
		for (int i=0; i<myList.size()-1; i++) { //changed to -1
			it.next();
		}

		try {
			it.next();
			fail("Should throw NoSuchElementException.");
		}
		catch(NoSuchElementException e) {
			// pass.
		}
		catch(Exception e) {
			fail("Threw incorrect exception type, should be NoSuchElementException.");
		}
	}
	
	@Test 
	public void testPreviousExceptionInitally() {
		buildList();
		
		try {
			it.previous();
			fail("Should throw NoSuchElementException.");
		}
		catch(NoSuchElementException e) {
			// pass.
		}
		catch(Exception e) {
			fail("Threw incorrect exception type, should be NoSuchElementException.");
		}
	}
	
	@Test 
	public void testPreviousExceptionAfterMoves() {
		buildList();
		it.next();
		it.previous();
		
		try {
			it.previous();
			fail("Should throw NoSuchElementException.");
		}
		catch(NoSuchElementException e) {
			// pass.
		}
		catch(Exception e) {
			fail("Threw incorrect exception type, should be NoSuchElementException.");
		}
	}
}
