/*
 *************************************************************************
 *************************************************************************
 **                                                                     **
 **  FLOWTIME                                                           **
 **  Copyright (C) 2010         Karl W. Pfalzer                         **
 **                                                                     **
 **  This program is free software; you can redistribute it and/or      **
 **  modify it under the terms of the GNU General Public License        **
 **  as published by the Free Software Foundation; either version 2     **
 **  of the License, or (at your option) any later version.             **
 **                                                                     **
 **  This program is distributed in the hope that it will be useful,    **
 **  but WITHOUT ANY WARRANTY; without even the implied warranty of     **
 **  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the      **
 **  GNU General Public License for more details.                       **
 **                                                                     **
 **  You should have received a copy of the GNU General Public License  **
 **  along with this program; if not, write to the                      **
 **  Free Software Foundation, Inc.                                     **
 **  51 Franklin Street, Fifth Floor                                    **
 **  Boston, MA  02110-1301, USA.                                       **
 **                                                                     **
 *************************************************************************
 *************************************************************************
 */
package flowtime.table;
import  javax.swing.JTable;
import  javax.swing.JPanel;
import  javax.swing.JScrollPane;
import  javax.swing.JComponent;
import  java.awt.Dimension;
import  java.awt.Component;
import  java.awt.event.ComponentAdapter;
import  java.awt.event.ComponentEvent;
import  java.awt.GridLayout;

/**
 *
 * @author karl
 */
public class Table extends JPanel {

    public Table() {
        this(false);
    }
    public Table(boolean horizScroll) {
        //1 row.  Need gridlayout to get scrollbar correctly shown.
        super(new GridLayout(1,0));
        createMVC(horizScroll);
    }

    private void createMVC(boolean hzSrcoll) {
        m_model = new Model();
        m_view = new View(m_model, hzSrcoll);
        m_cntlr = new Controller();
        this.add(m_view.m_scrollPane);
        m_view.addResizeListener(this);
    }

    private class Model {
        String[] columnNames = {"First Name",
                        "Last Name",
                        "Sport",
                        "# of Years",
                        "Vegetarian"};
        Object[][] data = {
            {"Kathy", "Smith",
             "Snowboarding", new Integer(5), new Boolean(false)},
            {"John", "Doe",
             "Rowing", new Integer(3), new Boolean(true)},
            {"Sue", "Black",
             "Knitting", new Integer(2), new Boolean(false)},
            {"Jane", "White",
             "Speed reading", new Integer(20), new Boolean(true)},
            {"Joe", "Brown",
             "Pool", new Integer(10), new Boolean(false)},
            {"Kathy", "Smith",
             "Snowboarding", new Integer(5), new Boolean(false)},
            {"John", "Doe",
             "Rowing", new Integer(3), new Boolean(true)},
            {"Sue", "Black",
             "Knitting", new Integer(2), new Boolean(false)},
            {"Jane", "White",
             "Speed reading", new Integer(20), new Boolean(true)},
            {"Joe", "Brown",
             "Pool", new Integer(10), new Boolean(false)},
                         {"Kathy", "Smith",
             "Snowboarding", new Integer(5), new Boolean(false)},
            {"John", "Doe",
             "Rowing", new Integer(3), new Boolean(true)},
            {"Sue", "Black",
             "Knitting", new Integer(2), new Boolean(false)},
            {"Jane", "White",
             "Speed reading", new Integer(20), new Boolean(true)},
            {"Joe", "Brown",
             "Pool", new Integer(10), new Boolean(false)},
            {"Kathy", "Smith",
             "Snowboarding", new Integer(5), new Boolean(false)},
            {"John", "Doe",
             "Rowing", new Integer(3), new Boolean(true)},
            {"Sue", "Black",
             "Knitting", new Integer(2), new Boolean(false)},
            {"Jane", "White",
             "Speed reading", new Integer(20), new Boolean(true)},
            {"Joe", "Brown",
             "Pool", new Integer(10), new Boolean(false)}
        };
    }
    private class View {
        View(Model model, boolean hzScroll) {
            m_hzScroll = hzScroll;
            m_table = new JTable(model.data, model.columnNames);
            m_minSize = m_table.getPreferredSize();
            m_table.setPreferredScrollableViewportSize(m_minSize);
            m_table.setAutoResizeMode(m_hzScroll ? JTable.AUTO_RESIZE_OFF : JTable.AUTO_RESIZE_ALL_COLUMNS);
            m_scrollPane = new JScrollPane(m_table);
        }
        JComponent getView() {
            return m_scrollPane;
        }
        void addResizeListener(Component comp) {
           comp.addComponentListener(new ResizeListener());
        }
        private class ResizeListener extends ComponentAdapter {
            @Override
            public void componentResized(ComponentEvent ce) {
                Dimension pref = ce.getComponent().getPreferredSize();
                Dimension sz = ce.getComponent().getSize();
                if (DEBUG) {
                    System.out.println("pref: "+pref+"\nsz: "+sz);
                }
                int rsz = JTable.AUTO_RESIZE_OFF;
                if (sz.getWidth() > pref.getWidth()) {
                    rsz = JTable.AUTO_RESIZE_ALL_COLUMNS;
                } else if (!m_hzScroll && (sz.getWidth() < m_minSize.getWidth())) {
                    rsz = JTable.AUTO_RESIZE_ALL_COLUMNS;
                }
                m_table.setAutoResizeMode(rsz);
            }
        }
        private JTable  m_table;
        private JScrollPane m_scrollPane;
        private Dimension m_minSize;
        private boolean m_hzScroll;
    }
    private class Controller {

    }

    private Model       m_model;
    private View        m_view;
    private Controller  m_cntlr;

    private static boolean DEBUG = true;
}
