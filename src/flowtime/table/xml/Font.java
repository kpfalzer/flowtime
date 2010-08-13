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
import static flowtime.Util.toBool;
import	org.xml.sax.Attributes;
/**
 *
 * @author karl
 */
public class Font {
	public Font(Attributes attrs) {
		m_underline = toBool(attrs.getValue(UNDERLINE));
		m_bold = toBool(attrs.getValue(BOLD));
		m_fontName = attrs.getValue(FONTNAME);
		m_color = attrs.getValue(COLOR);
	}

	private final boolean	m_underline;
	private final boolean	m_bold;
	private final String	m_fontName;
	private final String	m_color;

	private static final String COLOR = "ss:Color";
	private static final String UNDERLINE = "ss:Underline";
	private static final String FONTNAME = "ss:FontName";
	private static final String BOLD = "ss:Bold";
}
