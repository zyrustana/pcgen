/*
 * Copyright (c) 2008 Tom Parker <thpr@users.sourceforge.net>
 * 
 * This program is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with this library; if not, write to the Free Software Foundation, Inc.,
 * 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301, USA
 */
package plugin.lsttokens.equipment;

import pcgen.cdom.enumeration.IntegerKey;
import pcgen.cdom.inst.EquipmentHead;
import pcgen.core.Equipment;
import pcgen.rules.context.LoadContext;
import pcgen.rules.persistence.token.AbstractNonEmptyToken;
import pcgen.rules.persistence.token.CDOMPrimaryToken;
import pcgen.rules.persistence.token.ParseResult;

/**
 * Deals with ALTCRITMULT token
 */
public class AltcritmultToken extends AbstractNonEmptyToken<Equipment>
		implements CDOMPrimaryToken<Equipment>
{

	@Override
	public String getTokenName()
	{
		return "ALTCRITMULT";
	}

	@Override
	protected ParseResult parseNonEmptyToken(LoadContext context,
		Equipment eq, String value)
	{
		Integer cm = null;
		if ((value.length() > 0) && (value.charAt(0) == 'x'))
		{
			try
			{
				cm = Integer.valueOf(value.substring(1));
				if (cm.intValue() <= 0)
				{
					return new ParseResult.Fail(getTokenName() + " cannot be <= 0", context);
				}
			}
			catch (NumberFormatException nfe)
			{
				return new ParseResult.Fail(getTokenName()
						+ " was expecting an Integer: " + value, context);
			}
		}
		else if ("-".equals(value))
		{
			cm = Integer.valueOf(-1);
		}
		if (cm == null)
		{
			return new ParseResult.Fail(getTokenName()
					+ " was expecting x followed by an integer "
					+ "or the special value '-' (representing no value)", context);
		}
		EquipmentHead altHead = eq.getEquipmentHead(2);
		context.getObjectContext().put(altHead, IntegerKey.CRIT_MULT, cm);
		return ParseResult.SUCCESS;
	}

	@Override
	public String[] unparse(LoadContext context, Equipment eq)
	{
		EquipmentHead head = eq.getEquipmentHeadReference(2);
		if (head == null)
		{
			return null;
		}
		Integer mult = context.getObjectContext().getInteger(head,
				IntegerKey.CRIT_MULT);
		if (mult == null)
		{
			return null;
		}
		int multInt = mult.intValue();
		String retString;
		if (multInt == -1)
		{
			retString = "-";
		}
		else if (multInt <= 0)
		{
			context.addWriteMessage(getTokenName() + " cannot be <= 0");
			return null;
		}
		else
		{
			retString = "x" + multInt;
		}
		return new String[] { retString };
	}

	@Override
	public Class<Equipment> getTokenClass()
	{
		return Equipment.class;
	}
}
