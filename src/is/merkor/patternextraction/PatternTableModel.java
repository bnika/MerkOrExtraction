package is.merkor.patternextraction;

/**
 * A table model for the pattern verification gui.
 * 
 * @author Anna B. Nikulasdottir
 */

import java.io.*;
import java.util.*;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.table.*;
import java.sql.*;


public class PatternTableModel extends AbstractTableModel {
    Vector cache; //Will hold String[] objects
    int colCount;
    String[] headers;
   

    public PatternTableModel() {
        cache = new Vector();
    }

    public String getColumnName (int i) {
        return headers[i];
    }
    public int getColumnCount() {
        return colCount;
    }
    public int getRowCount() {
        return cache.size();
    }

    public Object getValueAt(int row, int col) {
        return ((String[])cache.elementAt(row))[col];
    }


    public void setData(List<PatternInfo> l) {
        cache = new Vector<String[]>();
        colCount = 2;
        headers = new String[colCount];
        headers[0] = "pattern";
        headers[1] = "relation";

        for (int i = 0; i < l.size(); i++) {
            String[] record = new String[colCount];
            record[0] = l.get(i).getPattern();
            record[1] = l.get(i).getRelation();

            cache.addElement(record);
        }
        fireTableChanged(null); // we have a new table.
    }

    public List<PatternInfo> sortByTableOrder(List<PatternInfo> ls) {
        List<PatternInfo> orderedList = new ArrayList<PatternInfo>();
        for (int i = 0; i < cache.size(); i++) {
            String pat = (String)getValueAt(i, 0);
            for (int j = 0; j < ls.size(); j++) {
                if(pat.equals(ls.get(j).getPattern()))
                    orderedList.add(ls.get(j));
            }
        }
        return orderedList;
    }
}
