/*
 * Copyright 2007 (C) Thomas Parker <thpr@users.sourceforge.net>
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA
 */
package plugin.lsttokens.auto;

import pcgen.core.PObject;
import pcgen.persistence.LoadContext;
import pcgen.persistence.lst.AutoLstToken;

public class EquipToken implements AutoLstToken
{

	public String getTokenName()
	{
		return "EQUIP";
	}

	public boolean parse(PObject target, String value)
	{
		target.addAutoArray(getTokenName(), value);
		return true;
	}

	public boolean parse(LoadContext context, PObject obj, String value)
	{
		/*
		 * TODO FIXME For EQUIP:
		 */
		// final Equipment aEq = EquipmentList.getEquipmentFromName(
		// tokText, aPC);
		//
		// if (aEq != null) {
		// final Equipment newEq = (Equipment) aEq.clone();
		// newEq.setQty(1);
		// newEq.setAutomatic(true);
		// newEq.setOutputIndex(aList.size());
		// aList.add(newEq);
		// }
		return true;

		// String shieldProfs;
		// String prereq = null; // Do not initialize, null is significant!
		//
		// // Note: May contain PRExxx
		// if (value.indexOf("[") == -1)
		// {
		// shieldProfs = value;
		// }
		// else
		// {
		// int openBracketLoc = value.indexOf("[");
		// shieldProfs = value.substring(0, openBracketLoc);
		// if (!value.endsWith("]"))
		// {
		// Logging.errorPrint("Unresolved Prerequisite in "
		// + getTokenName() + " " + value + " in " + getTokenName());
		// }
		// prereq =
		// value.substring(openBracketLoc + 1, value.length()
		//  - 2);
		// }
		//
		// StringTokenizer tok = new StringTokenizer(shieldProfs,
		// Constants.PIPE);
		//
		// while (tok.hasMoreTokens())
		// {
		// String aProf = tok.nextToken();
		// if (aProf.startsWith(Constants.LST_TYPE)
		// || aProf.startsWith(Constants.LST_TYPE_OLD))
		// {
		// CDOMGroupRef<Equipment> ref =
		// context.ref.getCDOMTypeReference(Equipment.class, aProf
		// .substring(5).split("."));
		// PCGraphGrantsEdge edge =
		// context.graph.linkObjectIntoGraph(getTokenName(), obj,
		// ref);
		// if (prereq != null)
		// {
		// try
		// {
		// edge.addPreReq(PreParserFactory.getInstance().parse(
		// prereq));
		// }
		// catch (PersistenceLayerException e)
		// {
		// Logging.errorPrint("Error generating Prerequisite "
		// + prereq + " in " + getTokenName());
		// }
		// }
		// }
		// else if ("%LIST".equals(value))
		// {
		// /*
		// * FIXME Need to figure out how to handle this!!!
		// */
		// // for (Iterator<AssociatedChoice<String>> e =
		// // getAssociatedList()
		// // .iterator(); e.hasNext();) {
		// // aList.add(e.next().getDefaultChoice());
		// // }
		// }
		// else
		// {
		// CDOMSimpleSingleRef<Equipment> ref =
		// context.ref.getCDOMReference(Equipment.class, aProf);
		// /*
		// * FIXME There is source consolidation that can be done once
		// * %LIST is figured out
		// */
		// PCGraphGrantsEdge edge =
		// context.graph.linkObjectIntoGraph(getTokenName(), obj,
		// ref);
		// if (prereq != null)
		// {
		// try
		// {
		// edge.addPreReq(PreParserFactory.getInstance().parse(
		// prereq));
		// }
		// catch (PersistenceLayerException e)
		// {
		// Logging.errorPrint("Error generating Prerequisite "
		// + prereq + " in " + getTokenName());
		// }
		// }
		// // Individual prefs
		// }
		// }
		//
		// return true;
	}

	public String[] unparse(LoadContext context, PObject obj)
	{
		// TODO Auto-generated method stub
		return null;
	}
}
