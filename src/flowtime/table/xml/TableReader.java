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
import	flowtime.XmlReader;
import static flowtime.Util.error;
import  java.io.IOException;
import	org.xml.sax.Attributes;
import	org.xml.sax.SAXException;
import  org.xml.sax.XMLReader;
import	javax.swing.table.AbstractTableModel;

/**
 *
 * @author karl
 */
public class TableReader extends XmlReader {
	public TableReader(String fname) throws SAXException, IOException {
		super(fname);
		m_rdr = getReader();
		super.parse(new TableReader.MyContentHandler());
		m_table.harden();
	}

	public static void main(String argv[]) {
		try {
			new TableReader(argv[0]);
		} catch (Exception ex) {
			error(ex);
		}
	}

	public Table getTable() {
		return m_table;
	}

	private XMLReader	m_rdr;
	private Table		m_table = new Table();

	private class MyContentHandler extends XmlReader.MyContentHandler {
		@Override
		public void startElement(String uri, String localName, String qName, Attributes atts) 
				throws SAXException {
			if (qName.equals("Style")) {
				Style style = new Style(m_rdr, atts);
				m_table.addStyle(style);
			} else if (qName.equals("Table")) {
				m_table.setColCnt(Integer.parseInt(atts.getValue("ss:ExpandedColumnCount")));
			} else if (qName.equals("Row")) {
				Row row = new Row(m_rdr, atts, m_table.getColCnt());
				m_table.addRow(row);
			}
		}

		@Override
		public void endElement(String uri, String localName, String qName) 
				throws SAXException {
		}
	}
}
