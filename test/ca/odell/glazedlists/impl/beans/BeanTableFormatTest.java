/**
 * Glazed Lists
 * http;//glazedlists.dev.java.net/
 *
 * COPYRIGHT 2003 O'DELL ENGINEERING LTD.
 */
package ca.odell.glazedlists.impl.beans;

// for being a JUnit test case
import junit.framework.*;
// standard collections
import java.util.*;
// test objects
import java.awt.Color;
// table forms
import ca.odell.glazedlists.*;
import ca.odell.glazedlists.gui.*;

/**
 * This test verifies that the BeanTableFormat works as expected.
 *
 * @author <a href="mailto:jesse@odel.on.ca">Jesse Wilson</a>
 */
public class BeanTableFormatTest extends TestCase {

    /** the format to test */
    private TableFormat footballFormat;

    /** the objects to test */
    private FootballTeam riders = new FootballTeam("Roughriders", "Saskatchewan", Color.green, Color.white);
    private FootballTeam ticats = new FootballTeam("Tiger-Cats", "Hamilton", Color.yellow, Color.black);
    
    /**
     * Prepare for the test.
     */
    public void setUp() {
        String[] propertyNames = new String[] { "name", "home", "primary", "secondary" };
        String[] columnNames = new String[] { "Name", "Home", "Primary Color", "Secondary Color" };
        boolean[] writable = new boolean[] { false, true, false, false };
        footballFormat = GlazedLists.tableFormat(propertyNames, columnNames, writable);
    }

    /**
     * Clean up after the test.
     */
    public void tearDown() {
        // do nothing
    }
    
    /**
     * Tests that BeanTableFormat works as a TableFormat.
     */
    public void testTableFormat() {
        assertEquals(4,                  footballFormat.getColumnCount());
        assertEquals("Name",             footballFormat.getColumnName(0));
        assertEquals("Home",             footballFormat.getColumnName(1));
        assertEquals("Primary Color",    footballFormat.getColumnName(2));
        assertEquals("Secondary Color",  footballFormat.getColumnName(3));
        
        assertEquals("Roughriders",      footballFormat.getColumnValue(riders, 0));
        assertEquals("Saskatchewan",     footballFormat.getColumnValue(riders, 1));
        assertEquals(Color.green,        footballFormat.getColumnValue(riders, 2));
        assertEquals(Color.white,        footballFormat.getColumnValue(riders, 3));
        
        assertEquals("Tiger-Cats",       footballFormat.getColumnValue(ticats, 0));
        assertEquals("Hamilton",         footballFormat.getColumnValue(ticats, 1));
        assertEquals(Color.yellow,       footballFormat.getColumnValue(ticats, 2));
        assertEquals(Color.black,        footballFormat.getColumnValue(ticats, 3));
    }

    /**
     * Tests that BeanTableFormat works as a WritableTableFormat.
     */
    public void testWritableTableFormat() {
        WritableTableFormat writableFootballFormat = (WritableTableFormat)footballFormat;
        assertEquals(false,              writableFootballFormat.isEditable(riders, 0));
        assertEquals(true,               writableFootballFormat.isEditable(riders, 1));
        assertEquals(false,              writableFootballFormat.isEditable(riders, 2));
        assertEquals(false,              writableFootballFormat.isEditable(riders, 3));
        
        writableFootballFormat.setColumnValue(riders, "Regina", 1);
        assertEquals("Regina",           riders.getHome());
        assertEquals("Regina",           footballFormat.getColumnValue(riders, 1));
        
        writableFootballFormat.setColumnValue(ticats, "Lancaster", 1);
        assertEquals("Lancaster",        ticats.getHome());
        assertEquals("Lancaster",        footballFormat.getColumnValue(ticats, 1));
    }

    /**
     * Tests that BeanTableFormat works as an AdvancedTableFormat.
     */
    public void testAdvancedTableFormat() {
        AdvancedTableFormat advancedFootballFormat = (AdvancedTableFormat)footballFormat;
        assertEquals(String.class,       advancedFootballFormat.getColumnClass(0));
        assertEquals(String.class,       advancedFootballFormat.getColumnClass(1));
        assertEquals(Color.class,        advancedFootballFormat.getColumnClass(2));
        assertEquals(Color.class,        advancedFootballFormat.getColumnClass(3));
    }
}

/**
 * A test object.
 */
class FootballTeam {
    private String name;
    private String home;
    private Color primary;
    private Color secondary;
    public FootballTeam(String name, String home, Color primary, Color secondary) {
        this.name = name;
        this.home = home;
        this.primary = primary;
        this.secondary = secondary;
    }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; };

    public String getHome() { return home; }
    public void setHome(String home) { this.home = home; };

    public Color getPrimary() { return primary; }
    public void setPrimary(Color primary) { this.primary = primary; };

    public Color getSecondary() { return secondary; }
    public void setSecondary(Color secondary) { this.secondary = secondary; };
}