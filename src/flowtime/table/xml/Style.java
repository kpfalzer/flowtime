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
import	org.xml.sax.Attributes;
import  org.xml.sax.XMLReader;
import  org.xml.sax.ContentHandler;
import	org.xml.sax.SAXException;

/**
 *
 * @author karl
 */
public class Style {
	public Style(XMLReader rdr, Attributes attr) {
		this(attr);
		m_rdr = rdr;
		m_wasHandler = m_rdr.getContentHandler();
		m_rdr.setContentHandler(new MyContentHandler());
	}
	public Style(Attributes attr) {
		m_id = attr.getValue(ID);
		assert(null != m_id);
		m_name = attr.getValue(NAME);
	}
	public String getId() {
		return m_id;
	}
	public String getName() {
		return m_name;
	}
	private String			m_id;
	private String			m_name;
	private XMLReader		m_rdr;
	private ContentHandler	m_wasHandler;

	private static final String ID = "ss:ID";
	private static final String NAME = "ss:Name";

	private class MyContentHandler extends XmlReader.MyContentHandler {
		@Override
		public void startElement(String uri, String localName, String qName, Attributes atts)
				throws SAXException {
			if (qName.equals("Font")) {
				Font font = new Font(atts);
			} else if (qName.equals("Interior")) {
				Interior intr = new Interior(atts);
			} else if (qName.equals("Alignment")) {
				Alignment align = new Alignment(atts);
			}
		}

		@Override
		public void endElement(String uri, String localName, String qName)
				throws SAXException {
			if (qName.equals("Style")) {
				m_rdr.setContentHandler(m_wasHandler);
			}
		}
	}

}
