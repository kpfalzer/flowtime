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
import	java.util.ArrayList;
import	org.xml.sax.Attributes;
import  org.xml.sax.XMLReader;
import	org.xml.sax.SAXException;

/**
 *
 * @author karl
 */
public class Row extends XmlReader.MyContentHandler {
	public Row(XMLReader rdr, Attributes attr, int colCnt) {
		super(rdr);
		assert(0 == attr.getLength());	//dont expect any
		m_cells = new ArrayList<Cell>(colCnt);
		rdr.setContentHandler(this);
	}

	public Cell getCellAt(int ix) {
		return m_cells.get(ix);
	}

	public Iterable<Cell> getCells() {
		return m_cells;
	}

	private void addCell(Cell c) {
		m_cells.add(c);
	}

	private ArrayList<Cell>	m_cells;
	private	Cell			m_currentCell;
	private Data			m_currentData;

	@Override
	public void startElement(String uri, String localName, String qName, Attributes atts)
			throws SAXException {
		if (qName.equals("Cell")) {
			m_currentCell = new Cell(atts);
		} else if (qName.equals("Data")) {
			m_currentData = new Data(atts);
		} else {
			assert(false);
		}
	}

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		if (qName.equals("Row")) {
			restoreHandler();
		} else if (qName.equals("Data")) {
			m_currentCell.addData(m_currentData);
			m_currentData = null;
		} else if (qName.equals("Cell")) {
			addCell(m_currentCell);
		}
	}

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		if (null != m_currentData) {
			assert(0 < length);
			m_currentData.addData(new String(ch, start, length));
		}
	}
}
