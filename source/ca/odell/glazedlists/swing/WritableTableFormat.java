/**
 * Glazed Lists
 * http://glazedlists.dev.java.net/
 *
 * COPYRIGHT 2003 O'DELL ENGINEERING LTD.
 */
package ca.odell.glazedlists.swing;

// the core Glazed Lists packages
import ca.odell.glazedlists.*;
import ca.odell.glazedlists.event.*;
// records fit inside of tables
import javax.swing.JTable;

/**
 * Specifies how a set of editable records are rendered in a table. The
 * Writable table format provides additional methods for changing the value
 * of a row as a response to cell editors. 
 *
 * @author <a href="mailto:jesse@odel.on.ca">Jesse Wilson</a>
 */
public interface WritableTableFormat extends TableFormat {

    /**
     * For editing fields. This returns true if the specified column can
     * be edited by the user.
     */
    public boolean isColumnEditable(int column);
    
    /**
     * Sets the specified field of the base object to the edited value. When
     * a column of a table is edited, this method is called so that the user
     * can specify how to modify the base object for each column.
     */
    public void setColumnValue(Object baseObject, Object editedValue, int column);
}
