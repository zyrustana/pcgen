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
package plugin.lsttokens.campaign;

import java.net.URI;

import pcgen.cdom.enumeration.StringKey;
import pcgen.core.Campaign;
import pcgen.persistence.lst.InstallLstToken;
import pcgen.rules.context.LoadContext;
import pcgen.rules.persistence.token.CDOMPrimaryParserToken;
import pcgen.rules.persistence.token.ErrorParsingWrapper;
import pcgen.rules.persistence.token.ParseResult;

/**
 * Class deals with INFOTEXT Token
 */
public class InfotextToken extends ErrorParsingWrapper<Campaign> implements CDOMPrimaryParserToken<Campaign>, InstallLstToken
{

	public String getTokenName()
	{
		return "INFOTEXT";
	}

	public boolean parse(Campaign campaign, String value, URI sourceUri)
	{
		campaign.put(StringKey.INFO_TEXT, value);
		return true;
	}

	public ParseResult parseToken(LoadContext context, Campaign campaign,
		String value)
	{
		if (value == null || value.length() == 0)
		{
			return new ParseResult.Fail(getTokenName() + " arguments may not be empty");
		}
		context.getObjectContext().put(campaign, StringKey.INFO_TEXT, value);
		return ParseResult.SUCCESS;
	}

	public String[] unparse(LoadContext context, Campaign camp)
	{
		String infotext =
				context.getObjectContext().getString(camp, StringKey.INFO_TEXT);
		if (infotext == null)
		{
			return null;
		}
		return new String[]{infotext};
	}

	public Class<Campaign> getTokenClass()
	{
		return Campaign.class;
	}
}
