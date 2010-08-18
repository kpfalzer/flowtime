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
import	org.xml.sax.Attributes;

/**
 *
 * @author karl
 */
public class Data {
	public Data(Attributes atts) {
		m_type = atts.getValue(TYPE);
		assert(null != m_type);
	}

	public void addData(String data) {
		if (m_type.equalsIgnoreCase("string")) {
			m_data = data;
		} else if (m_type.equalsIgnoreCase("number")) {
			m_data = data; //TODO: keep semblance of precision?
		}
	}

	public Object getData() {
		return m_data;
	}

	private final String	m_type;
	private Object			m_data;

	private static final String TYPE = "ss:Type";
}
