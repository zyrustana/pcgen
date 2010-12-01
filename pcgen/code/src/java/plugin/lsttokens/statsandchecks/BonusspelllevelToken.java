/*
 * StatnameToken.java
 * Copyright 2006 (C) Devon Jones <soulcatcher@evilsoft.org>
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
 *
 * Created on September 2, 2002, 8:02 AM
 *
 * Current Ver: $Revision$
 * Last Editor: $Author$
 * Last Edited: $Date$
 *
 */
package plugin.lsttokens.statsandchecks;

import java.net.URI;

import pcgen.cdom.content.BonusSpellInfo;
import pcgen.persistence.lst.SimpleLoader;
import pcgen.persistence.lst.StatsAndChecksLstToken;
import pcgen.rules.context.LoadContext;
import pcgen.util.Logging;

/**
 * Class deals with BONUSSPELLLEVEL Token
 */
public class BonusspelllevelToken implements StatsAndChecksLstToken
{
	private static final Class<BonusSpellInfo> BONUS_SPELL_INFO_CLASS = BonusSpellInfo.class;

	public String getTokenName()
	{
		return "BONUSSPELLLEVEL";
	}

	public boolean parse(LoadContext context, String lstLine, URI sourceURI)
	{
		try
		{
			SimpleLoader<BonusSpellInfo> bonusSpellLoader = new SimpleLoader<BonusSpellInfo>(
					BONUS_SPELL_INFO_CLASS);
			bonusSpellLoader.parseLine(context, lstLine.substring(16), sourceURI);
			return true;
		}
		catch (Exception e)
		{
			Logging.errorPrint(e.getMessage());
			return false;
		}
	}
}
