import org.junit.Before;
import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class CSArrayListLabTests {
    private CSArrayList<String> stringList;
    private CSArrayList<Object> intList;
    private CSArrayList<Object> list;

    @Before
    public void setup(){
        list = new CSArrayList<>();
        stringList = new CSArrayList<>();
        intList = new CSArrayList<>();
    }

    @Test
    public void testEdgeIndexOperations(){
        stringList.add("A");
        stringList.add("C");
        stringList.add(1, "B");

        assertEquals("A", stringList.get(0));
        assertEquals("B", stringList.get(1));
        assertEquals("C", stringList.get(2));

        assertEquals("A", stringList.remove(0));
        assertEquals("C", stringList.remove(stringList.size()-1));
    }
    @Test
    public void testMultipleResizes(){
        for (int i = 0; i < 10000; i++){
            intList.add(i);
        }
        assertEquals(10000, intList.size());
        assertEquals(Integer.valueOf(0), intList.get(0));
        assertEquals(Integer.valueOf(9999), intList.get(9999));
    }
    @Test
    public void testSearchWithDuplicatesAndNull(){

        list.add("A");
        list.add(null);
        list.add("B");
        list.add(null);
        list.add("A");

        assertEquals(0, list.indexOf("A"));
        assertEquals(1, list.indexOf(null));
        assertEquals(2, list.indexOf("B"));
        assertEquals(-1, list.indexOf("Z"));
    }
    @Test
    public void testRemoveObjectBehavior(){
        stringList.add("A");
        stringList.add("B");

        assertTrue(stringList.remove("B"));
        assertFalse(stringList.remove("Z"));
        assertEquals(1, stringList.size());
    }
    @Test
    public void testFailFastIterator(){
        stringList.add("A");
        stringList.add("B");

        Iterator<String> it = stringList.iterator();
        assertEquals("A", it.next());

        stringList.add("C");
        assertThrows(ConcurrentModificationException.class, it::next);
    }
}
