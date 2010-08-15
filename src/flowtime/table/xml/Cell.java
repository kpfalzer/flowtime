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
import	java.util.Map;
import	org.xml.sax.Attributes;

/**
 *
 * @author karl
 */
public class Cell {
	public Cell(Attributes atts) {
		m_style = new StyleName(atts.getValue(STYLE));
		assert(null != m_style.asName());
		m_href = atts.getValue(HREF);
	}

	public void addData(Data data) {
		m_data = data;
	}

	public void linkStyle(Map<String,Style> stylesByName) {
		String key = m_style.asName();
		m_style = (null == key) ? null : new AsStyle(stylesByName.get(key));
	}

	public Style getStyle() {
		assert(m_style instanceof AsStyle);
		return m_style.asStyle();
	}

	private IStyle			m_style;
	private final String	m_href;
	private Data			m_data;

	private static final String STYLE = "ss:StyleID";
	private static final String HREF = "ss:HRef";

	/**
	 * Interface allows initial reference to Style name using StyleName.
	 * Then, we can link to actual Style using AsStyle.
	 */
	private static interface IStyle {
		public String asName();
		public Style asStyle();
	}
	private static class StyleName implements IStyle {
		public StyleName(String nm) {
			m_name = nm;
		}
		public String asName() {
			return m_name;
		}
		public Style asStyle() {
			return null;
		}
		private final String	m_name;
	}
	private static class AsStyle implements IStyle {
		public AsStyle(Style style) {
			m_style = style;
		}
		public String asName() {
			return null;
		}
		public Style asStyle() {
			return m_style;
		}
		private final Style	m_style;
	}
}
