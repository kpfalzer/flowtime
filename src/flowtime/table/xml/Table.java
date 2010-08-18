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
package flowtime.table.xml;
import	java.util.HashMap;
import	java.util.Map;
import	java.util.ArrayList;
import	javax.swing.table.AbstractTableModel;

/**
 *
 * @author karl
 */
public class Table {
	public Table() {}

	public AbstractTableModel getModel() {
		return new Model();
	}

	/**
	 * Model access subclass compatible with AbstractTableModel.
	 */
	private class Model extends AbstractTableModel {
		public int getColumnCount() {
			return getColCnt();
		}

		public int getRowCount() {
			return m_rows.size() - 1;	//1st row is label
		}

		public Object getValueAt(int rowIndex, int columnIndex) {
			//row is +1 offset since 1st row is labels.
			return getCellAt(rowIndex+1, columnIndex).getData();
		}

		@Override
		public boolean isCellEditable(int rowIndex, int columnIndex) {
			return false;
		}

		@Override
		public String getColumnName(int column) {
			return getCellAt(0, column).getData().toString();
		}
	}

	public void addStyle(Style s) {
		m_stylesById.put(s.getId(), s);
	}

	public Style getStyle(String nm) {
		return m_stylesById.get(nm);
	}

	public void setColCnt(int cnt) {
		m_colCnt = cnt;
	}

	public int getColCnt() {
		return m_colCnt;
	}

	public void addRow(Row row) {
		m_rows.add(row);
	}

	public Row getRowAt(int i) {
		return m_rows.get(i);
	}

	public Cell getCellAt(int row, int col) {
		return getRowAt(row).getCellAt(col);
	}

	/**
	 * Link cell styles and trim row count.
	 */
	public void harden() {
		m_rows.trimToSize();
		for (Row row : m_rows) {
			for (Cell cell : row.getCells()) {
				cell.linkStyle(m_stylesById);
			}
		}
	}

	private Map<String,Style>	m_stylesById	= new HashMap<String,Style>();
	private int					m_colCnt		= -1;
	private ArrayList<Row>		m_rows			= new ArrayList<Row>(100);

}
