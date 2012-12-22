/*
 * Copyright 2012 (C) Tom Parker <thpr@users.sourceforge.net>
 * Derived from Skill.java
 * Copyright 2001 (C) Bryan McRoberts <merton_monk@yahoo.com>
 * 
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 * 
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with this library; if not, write to the Free Software Foundation, Inc.,
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA
 */
package pcgen.core.display;

import java.text.NumberFormat;
import java.util.Collection;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Pattern;

import pcgen.base.lang.StringUtil;
import pcgen.base.util.NamedValue;
import pcgen.cdom.enumeration.ObjectKey;
import pcgen.cdom.enumeration.Type;
import pcgen.core.PCStat;
import pcgen.core.PlayerCharacter;
import pcgen.core.SettingsHandler;
import pcgen.core.Skill;
import pcgen.core.analysis.SkillRankControl;
import pcgen.core.bonus.BonusObj;
import pcgen.core.bonus.BonusPair;
import pcgen.core.utils.CoreUtility;
import pcgen.util.Delta;

public class SkillCostDisplay
{

	/**
	 * Builds up a string describing what makes up the misc modifier for a skill
	 * for a character. This can either be in long form '+2[skill TUMBLE gteq
	 * 5|TYPE=SYNERGY.STACK]' or in short form '+2[TUMBLE]'. Any modifiers that
	 * cannot be determined will be displayed as a single entry of 'OTHER'.
	 * 
	 * @param aPC
	 *            The character associated with this skill.
	 * @param shortForm
	 *            True if the abbreviated form should be used.
	 * @return The explanation of the misc modifier's make-up.
	 */
	public static String getModifierExplanation(Skill sk, PlayerCharacter aPC,
			boolean shortForm)
	{
		double bonusObjTotal = 0.0;
		Set<String> explanation = new TreeSet<String>();
		String keyName = sk.getKeyName();
		String bonusKey = ("SKILL." + keyName).toUpperCase();
		for (BonusObj bonus : aPC.getActiveBonusList())
		{
			// calculate bonus and add to activeBonusMap
			if (aPC.isApplied(bonus) && "SKILL".equals(bonus.getBonusName()))
			{
				boolean include =
						bonus.getBonusInfoList()
							.contains(keyName.toUpperCase());
				if (!include)
				{
					for (BonusPair bp : aPC.getStringListFromBonus(bonus))
					{
						String bpKey = bp.bonusKey.toUpperCase();
						if (bpKey.startsWith(bonusKey))
						{
							include = true;
							break;
						}
					}
				}
	
				if (include)
				{
					double iBonus = 0;
					for (BonusPair bp : aPC.getStringListFromBonus(bonus))
					{
						String bpKey = bp.bonusKey.toUpperCase();
						if (bpKey.startsWith(bonusKey))
						{
							iBonus += bp.resolve(aPC).doubleValue();
						}
					}
					if (!CoreUtility.doublesEqual(iBonus, 0.0))
					{
						explanation.add(Delta.toString((int) iBonus)
								+ aPC.getBonusContext(bonus, shortForm));
						bonusObjTotal += iBonus;
					}
				}
			}
		}
	
		StringBuilder bonusDetails = new StringBuilder();
		bonusDetails.append(StringUtil.joinToStringBuilder(explanation, " "));
		
		// TODO: Need to add other bonuses which are not encoded as bonus
		// objects
		// - familiars, racial, feats - and add them to bonusObjTotal
	
		double bonus;
		PCStat stat = sk.get(ObjectKey.KEY_STAT);
		if (stat != null)
		{
			bonus = aPC.getStatModFor(stat);
			bonus += aPC.getTotalBonusTo("SKILL", "STAT." + stat.getAbb());
			SkillCostDisplay.appendBonusDesc(sk, bonusDetails, bonus, "STAT");
		}
	
		// The catch-all for non-bonusObj modifiers.
		bonus = aPC.getTotalBonusTo("SKILL", keyName) - bonusObjTotal;
		SkillCostDisplay.appendBonusDesc(sk, bonusDetails, bonus, "OTHER");
	
		// loop through all current skill types checking for boni
		for (Type singleType : sk.getTrueTypeList(false))
		{
			bonus = aPC.getTotalBonusTo("SKILL", "TYPE." + singleType);
			SkillCostDisplay.appendBonusDesc(sk, bonusDetails, bonus, "TYPE." + singleType);
		}
	
		// now check for any lists of skills, etc
		bonus = aPC.getTotalBonusTo("SKILL", "LIST");
		SkillCostDisplay.appendBonusDesc(sk, bonusDetails, bonus, "LIST");
	
		// now check for ALL
		bonus = aPC.getTotalBonusTo("SKILL", "ALL");
		SkillCostDisplay.appendBonusDesc(sk, bonusDetails, bonus, "ALL");
	
		// these next two if-blocks try to get BONUS:[C]CSKILL|TYPE=xxx|y to
		// function
		if (aPC.isClassSkill(sk))
		{
			bonus = aPC.getTotalBonusTo("CSKILL", keyName);
			SkillCostDisplay.appendBonusDesc(sk, bonusDetails, bonus, "CSKILL");
	
			// loop through all current skill types checking for boni
			for (Type singleType : sk.getTrueTypeList(false))
			{
				bonus = aPC.getTotalBonusTo("CSKILL", "TYPE." + singleType);
				SkillCostDisplay
						.appendBonusDesc(sk, bonusDetails, bonus, "CSKILL");
			}
	
			bonus = aPC.getTotalBonusTo("CSKILL", "LIST");
			SkillCostDisplay.appendBonusDesc(sk, bonusDetails, bonus, "CSKILL");
		}
	
		if (!aPC.isClassSkill(sk)
				&& !sk.getSafe(ObjectKey.EXCLUSIVE))
		{
			bonus = aPC.getTotalBonusTo("CCSKILL", keyName);
			SkillCostDisplay.appendBonusDesc(sk, bonusDetails, bonus, "CCSKILL");
	
			// loop through all current skill types checking for boni
			for (Type singleType : sk.getTrueTypeList(false))
			{
				bonus = aPC.getTotalBonusTo("CCSKILL", "TYPE." + singleType);
				SkillCostDisplay.appendBonusDesc(sk, bonusDetails, bonus, "CCSKILL");
			}
	
			bonus = aPC.getTotalBonusTo("CCSKILL", "LIST");
			SkillCostDisplay.appendBonusDesc(sk, bonusDetails, bonus, "CCSKILL");
		}
	
		// Encumbrance
		int aCheckMod = sk.getSafe(ObjectKey.ARMOR_CHECK).calculateBonus(aPC);
		SkillCostDisplay.appendBonusDesc(sk, bonusDetails, aCheckMod, "ARMOR");
	
		String aString = SettingsHandler.getGame().getRankModFormula();
		if (aString.length() != 0)
		{
			aString = aString.replaceAll(Pattern.quote("$$RANK$$"), SkillRankControl.getTotalRank(aPC, sk).toString());
			bonus = aPC.getVariableValue(aString, "").intValue();
			SkillCostDisplay.appendBonusDesc(sk, bonusDetails, bonus, "RANKS");
		}
	
		return bonusDetails.toString();
	}

	/**
	 * Append a description of the bonus to the supplied buffer if the bonus
	 * value is not 0.
	 * 
	 * @param bonusDetails
	 *            The StringBuilder being built up. NB: May be modified.
	 * @param bonus
	 *            The value of the bonus.
	 * @param description
	 *            The description of the bonus.
	 */
	public static void appendBonusDesc(Skill sk, StringBuilder bonusDetails,
			double bonus, String description)
	{
		if (CoreUtility.doublesEqual(bonus, 0.0))
		{
			return;
		}
	
		if (bonusDetails.length() > 0)
		{
			bonusDetails.append(' ');
		}
		String value = Delta.toString((float) bonus);
		if (value.endsWith(".0"))
		{
			value = value.substring(0, value.length() - 2);
		}
		bonusDetails.append(value);
		bonusDetails.append('[').append(description).append(']');
	}

	public static String getRanksExplanation(PlayerCharacter pc, Skill sk)
	{
		Collection<NamedValue> assocList = pc.getSkillRankValues(sk);
		String result = StringUtil.join(assocList, ", ");
		double bonus = SkillRankControl.getSkillRankBonusTo(pc, sk);
		if (bonus != 0d)
		{
			if (result.length() > 0)
			{
				result += "; ";
			}
			
			result += "Skillrank bonus " + NumberFormat.getNumberInstance().format(bonus);
		}
		return result;
	}

}
