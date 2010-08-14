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
import	java.util.Hashtable;
import	java.util.Map;
import	java.util.List;
import	java.util.LinkedList;

/**
 *
 * @author karl
 */
public class Table {
	public Table() {}

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

	private Map<String,Style>	m_stylesById	= new Hashtable<String,Style>();
	private int					m_colCnt		= -1;
	private List<Row>			m_rows			= new LinkedList<Row>();
}
