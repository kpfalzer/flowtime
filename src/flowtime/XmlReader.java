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
package flowtime;
import  java.io.FileReader;
import static flowtime.Util.error;
import  java.io.IOException;
import  org.xml.sax.Attributes;
import  org.xml.sax.ContentHandler;
import  org.xml.sax.Locator;
import  org.xml.sax.SAXException;
import  org.xml.sax.XMLReader;
import  org.xml.sax.InputSource;
import  org.xml.sax.helpers.XMLReaderFactory;
/**
 *
 * @author kpfalzer
 */
public class XmlReader {
    public XmlReader(String fname) throws SAXException {
        m_fname = fname;
		m_rdr = XMLReaderFactory.createXMLReader();
    }

	public void parse(MyContentHandler handler) throws SAXException, IOException {
		m_rdr.setContentHandler(handler);
		FileReader frdr = new FileReader(m_fname);
		m_rdr.parse(new InputSource(frdr));
	}

    public static void main(String argv[]) {
		try {
			XmlReader rdr = new XmlReader(argv[0]);
			rdr.parse(new XmlReader.MyContentHandler());
		} catch (Exception ex) {
			error(ex);
		}
	}

	protected XMLReader getReader() {
		return m_rdr;
	}

    private final String    m_fname;
    private XMLReader       m_rdr = null;

    public static class MyContentHandler implements ContentHandler {

        public void startElement(String uri, String localName, String qName, Attributes atts) throws SAXException {
            //TODO
        }

        public void endElement(String uri, String localName, String qName) throws SAXException {
            //TODO
        }

        public void endPrefixMapping(String prefix) throws SAXException {
            //TODO
        }

        public void endDocument() throws SAXException {
            //TODO
        }

        public void setDocumentLocator(Locator locator) {
            //TODO
        }

        public void skippedEntity(String name) throws SAXException {
            //TODO
        }

        public void startDocument() throws SAXException {
            //TODO
        }

        public void startPrefixMapping(String prefix, String uri) throws SAXException {
            //TODO
        }

        public void processingInstruction(String target, String data) throws SAXException {
            //TODO
        }

        public void ignorableWhitespace(char[] ch, int start, int length) throws SAXException {
            //TODO
        }

        public void characters(char[] ch, int start, int length) throws SAXException {
            //TODO
        }

    }
}
