

import static org.junit.Assert.*;

import java.util.Random;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class No5Tests {

    private CS232DoublyLinkedList<String> myList;

    @Before
    public void setUp() throws Exception {
        myList = new CS232DoublyLinkedList<String>();
    }
    
    @Test
    public void testRemoveAtStart() {
        myList.add("Test1");
        myList.add("Test2");
        myList.add("Test3");
        
        Object obj = myList.remove(0);
        assertEquals("wrong object removed", "Test1", obj);
        assertEquals("wrong size", 2, myList.size());
        assertEquals("element should have shifted down", "Test2", myList.get(0));
        assertEquals("element should have shifted down", "Test3", myList.get(1));
        
        assertTrue("Not all links are correct", myList.checkListIntegrity());
    }
    
    @Test
    public void testRemoveInMiddle() {
        myList.add("Test1");
        myList.add("Test2");
        myList.add("Test3");
        myList.add("Test4");
        myList.add("Test5");
             
        Object obj = myList.remove(2);
        assertEquals("wrong object removed", "Test3", obj);
        assertEquals("wrong size", 4, myList.size());
        assertEquals("element should not have changed", "Test1", myList.get(0));
        assertEquals("element should not have changed", "Test2", myList.get(1));
        assertEquals("element should have shifted down", "Test4", myList.get(2));
        assertEquals("element should have shifted down", "Test5", myList.get(3));
        
        assertTrue("Not all links are correct", myList.checkListIntegrity());
    }
    
    @Test
    public void testRemoveAtEnd() {
        myList.add("Test1");
        myList.add("Test2");
        myList.add("Test3");
        myList.add("Test4");
        
        Object obj = myList.remove(3);
        assertEquals("wrong object removed", "Test4", obj);
        assertEquals("wrong size", 3, myList.size());
        assertEquals("element should not have changed", "Test1", myList.get(0));
        assertEquals("element should not have changed", "Test2", myList.get(1));
        assertEquals("element should not have changed", "Test3", myList.get(2));
        
        assertTrue("Not all links are correct", myList.checkListIntegrity());
    }
    
    @Test
    public void testRemoveMany() {
    	for (int i=0; i<100; i++) {
    		myList.add("" + i);
    	}
    	Random rnd = new Random();
    	for (int i=0; i<98; i++) {
    		myList.remove(rnd.nextInt(myList.size()));
    	}
    	
    	assertEquals("Incorrect list size", 2, myList.size());
    	
        assertTrue("Not all links are correct", myList.checkListIntegrity());
    }
    
    @Test
    public void testRemoveAtEndThenAdd() {
        myList.add("Test1");
        myList.add("Test2");
        myList.add("Test3");
        myList.add("Test4");
        
        myList.remove(3);
        myList.add("NewValue");
        assertEquals("wrong size", 4, myList.size());
        assertEquals("element should not have changed", "Test1", myList.get(0));
        assertEquals("element should not have changed", "Test2", myList.get(1));
        assertEquals("element should not have changed", "Test3", myList.get(2));
        assertEquals("element should not have changed", "NewValue", myList.get(3));
        
        assertTrue("Not all links are correct", myList.checkListIntegrity());
    }
    
    @Test
    public void testRemoveIllegalIndex() {
        myList.add("Test1");
        myList.add("Test2");
        
        try {
            myList.remove(2);
            fail("should have thrown exception");
        }
        catch (IndexOutOfBoundsException e) {
        }
        catch(Exception e) {
            fail("Incorrect type of exception.");
        }
        
        assertTrue("Not all links are correct", myList.checkListIntegrity());
        
        try {
            myList.remove(-1);
            fail("should have thrown exception");
        }
        catch (IndexOutOfBoundsException e) {
        }
        catch(Exception e) {
            fail("Incorrect type of exception.");
        }
        
        assertTrue("Not all links are correct", myList.checkListIntegrity());
    }

    @Test
    public void testClearToFirst() {
        myList.add("Test1");
        myList.add("Test2");
        myList.add("Test3");
        
    	myList.clearTo(0);
    	
    	assertEquals("Incorrect list length after clearTo", 2, myList.size());
    	assertEquals("Wrong value at index 0.", "Test2", myList.get(0));
    	
        assertTrue("Not all links are correct", myList.checkListIntegrity());
    }
    
    @Test
    public void testClearToMiddle() {
        myList.add("Test1");
        myList.add("Test2");
        myList.add("Test3");
        myList.add("Test4");
        myList.add("Test5");
        
    	myList.clearTo(2);
    	
    	assertEquals("Incorrect list length after clearTo", 2, myList.size());
    	assertEquals("Wrong value at index 0.", "Test4", myList.get(0));
    	
        assertTrue("Not all links are correct", myList.checkListIntegrity());
    }
    
    @Test
    public void testClearToLast() {
        myList.add("Test1");
        myList.add("Test2");
        myList.add("Test3");
        myList.add("Test4");
        myList.add("Test5");
        
    	myList.clearTo(4);
    	
    	assertEquals("Incorrect list length after clearTo", 0, myList.size());
    	
        assertTrue("Not all links are correct", myList.checkListIntegrity());
    }
    
    @Test
    public void testClearToIllegalIndex() {
        myList.add("Test1");
        myList.add("Test2");
        
        try {
            myList.clearTo(2);
            fail("should have thrown exception");
        }
        catch (IndexOutOfBoundsException e) {
        	// pass
        }
        catch(Exception e) {
            fail("Incorrect type of exception.");
        }
        
        assertTrue("Not all links are correct", myList.checkListIntegrity());
        
        try {
            myList.clearTo(-1);
            fail("should have thrown exception");
        }
        catch (IndexOutOfBoundsException e) {
        	// pass
        }
        catch(Exception e) {
            fail("Incorrect type of exception.");
        }
        
        assertTrue("Not all links are correct", myList.checkListIntegrity());
    }
    
    @Test
    public void testAddAllAtFirst() {
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
    	
    	assertEquals("Incorrect list length", 8, myList.size());
    	assertEquals("Incorrect value at 0", "new1", myList.get(0));
    	assertEquals("Incorrect value at 2", "new3", myList.get(2));
    	assertEquals("Incorrect value at 3", "Test1", myList.get(3));
    	
        assertTrue("Not all links are correct", myList.checkListIntegrity());
    }
    
    @Test
    public void testAddAllAtMiddle() {
        myList.add("Test1");
        myList.add("Test2");
        myList.add("Test3");
        myList.add("Test4");
        myList.add("Test5");
        
    	CS232DoublyLinkedList<String> addList = new CS232DoublyLinkedList<String>();
    	addList.add("new1");
    	addList.add("new2");
    	addList.add("new3");
    	
    	myList.addAllAt(2, addList);
    	
    	assertEquals("Incorrect list length", 8, myList.size());
    	assertEquals("Incorrect value at 1", "Test2", myList.get(1));
    	assertEquals("Incorrect value at 2", "new1", myList.get(2));
    	assertEquals("Incorrect value at 4", "new3", myList.get(4));
    	assertEquals("Incorrect value at 5", "Test3", myList.get(5));
    	
        assertTrue("Not all links are correct", myList.checkListIntegrity());
    }
    
    @Test
    public void testAddAllAtEnd() {
        myList.add("Test1");
        myList.add("Test2");
        myList.add("Test3");
        myList.add("Test4");
        myList.add("Test5");
        
    	CS232DoublyLinkedList<String> addList = new CS232DoublyLinkedList<String>();
    	addList.add("new1");
    	addList.add("new2");
    	addList.add("new3");
    	
    	myList.addAllAt(5, addList);
    	
    	assertEquals("Incorrect list length", 8, myList.size());
    	assertEquals("Incorrect value at 4", "Test5", myList.get(4));
    	assertEquals("Incorrect value at 5", "new1", myList.get(5));
    	assertEquals("Incorrect value at 7", "new3", myList.get(7));
    	
        assertTrue("Not all links are correct", myList.checkListIntegrity());
    }
    
    @Test
    public void testAddAllIllegalIndex() {
        myList.add("Test1");
        myList.add("Test2");
        
    	CS232DoublyLinkedList<String> addList = new CS232DoublyLinkedList<String>();
    	addList.add("new1");
    	addList.add("new2");
    	
        try {
            myList.addAllAt(3, addList);
            fail("should have thrown exception");
        }
        catch (IndexOutOfBoundsException e) {
        	// pass
        }
        catch(Exception e) {
            fail("Incorrect type of exception.");
        }
        
        assertTrue("Not all links are correct", myList.checkListIntegrity());
        
        try {
            myList.addAllAt(-1, addList);
            fail("should have thrown exception");
        }
        catch (IndexOutOfBoundsException e) {
        	// pass
        }
        catch(Exception e) {
            fail("Incorrect type of exception.");
        }
        
        assertTrue("Not all links are correct", myList.checkListIntegrity());
    }
    
    @Test
    public void testAddAllEmptyList() {
        myList.add("Test1");
        myList.add("Test2");
        
    	CS232DoublyLinkedList<String> addList = new CS232DoublyLinkedList<String>();
    	
        try {
            myList.addAllAt(1, addList);
            fail("should have thrown exception");
        }
        catch (IllegalArgumentException e) {
        	// pass
        }
        catch(Exception e) {
            fail("Incorrect type of exception.");
        }
        
        assertTrue("Not all links are correct", myList.checkListIntegrity());
    }
}
