/* Glazed Lists                                                 (c) 2003-2007 */
/* http://publicobject.com/glazedlists/                      publicobject.com,*/
/*                                                     O'Dell Engineering Ltd.*/
package ca.odell.glazedlists.calculation;

import ca.odell.glazedlists.BasicEventList;
import ca.odell.glazedlists.EventList;

import org.junit.Test;

import static org.junit.Assert.*;

public class OneElementTest {

    @Test
    public void testPropertyChanges() {
        final EventList<String> source = new BasicEventList<>();

        final PropertyChangeCounter counter = new PropertyChangeCounter();
        final Calculation<Boolean> oneCount = Calculations.oneElement(source);
        oneCount.addPropertyChangeListener(counter);

        // check the initial Count state
        assertEquals(Boolean.FALSE, oneCount.getValue());
        assertEquals(0, counter.getCountAndReset());

        // test add
        source.add("a");
        assertEquals(Boolean.TRUE, oneCount.getValue());
        assertEquals(1, counter.getCountAndReset());

        // test add 2nd element
        source.add("b");
        assertEquals(Boolean.FALSE, oneCount.getValue());
        assertEquals(1, counter.getCountAndReset());

        // test update
        source.set(0, "b");
        assertEquals(Boolean.FALSE, oneCount.getValue());
        assertEquals(0, counter.getCountAndReset());

        // test remove
        source.remove(0);
        assertEquals(Boolean.TRUE, oneCount.getValue());
        assertEquals(1, counter.getCountAndReset());

        // test remove last element
        source.remove(0);
        assertEquals(Boolean.FALSE, oneCount.getValue());
        assertEquals(1, counter.getCountAndReset());
    }
}
