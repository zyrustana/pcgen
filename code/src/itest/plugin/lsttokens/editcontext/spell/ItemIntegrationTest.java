/*
 * Copyright (c) 2007 Tom Parker <thpr@users.sourceforge.net>
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
package plugin.lsttokens.editcontext.spell;

import org.junit.Test;

import pcgen.core.spell.Spell;
import pcgen.persistence.PersistenceLayerException;
import pcgen.persistence.lst.CDOMToken;
import pcgen.persistence.lst.LstObjectFileLoader;
import pcgen.persistence.lst.SpellLoader;
import plugin.lsttokens.editcontext.testsupport.AbstractStringIntegrationTestCase;
import plugin.lsttokens.editcontext.testsupport.TestContext;
import plugin.lsttokens.spell.TargetareaToken;

public class ItemIntegrationTest extends
		AbstractStringIntegrationTestCase<Spell>
{

	static TargetareaToken token = new TargetareaToken();
	static SpellLoader loader = new SpellLoader();

	@Override
	public Class<Spell> getCDOMClass()
	{
		return Spell.class;
	}

	@Override
	public LstObjectFileLoader<Spell> getLoader()
	{
		return loader;
	}

	@Override
	public CDOMToken<Spell> getToken()
	{
		return token;
	}

	@Override
	public boolean isClearLegal()
	{
		return false;
	}

	@Test
	public void testRoundRobinProhibitedSimple()
		throws PersistenceLayerException
	{
		verifyCleanStart();
		TestContext tc = new TestContext();
		commit(testCampaign, tc, "[Languedoc-Roussillon]");
		commit(modCampaign, tc, "[Niederösterreich]");
		completeRoundRobin(tc);
	}

	@Test
	public void testRoundRobinProhibitedSame() throws PersistenceLayerException
	{
		verifyCleanStart();
		TestContext tc = new TestContext();
		commit(testCampaign, tc, "[Finger Lakes]");
		commit(modCampaign, tc, "[Finger Lakes[");
		completeRoundRobin(tc);
	}

	@Test
	public void testRoundRobinRemoveAdd() throws PersistenceLayerException
	{
		verifyCleanStart();
		TestContext tc = new TestContext();
		commit(testCampaign, tc, "[Finger Lakes]");
		commit(modCampaign, tc, "Finger Lakes");
		completeRoundRobin(tc);
	}

	@Test
	public void testRoundRobinAddRemove() throws PersistenceLayerException
	{
		verifyCleanStart();
		TestContext tc = new TestContext();
		commit(testCampaign, tc, "Finger Lakes");
		commit(modCampaign, tc, "[Finger Lakes]");
		completeRoundRobin(tc);
	}

	@Test
	public void testRoundRobinProhibitedNoSet()
		throws PersistenceLayerException
	{
		verifyCleanStart();
		TestContext tc = new TestContext();
		emptyCommit(testCampaign, tc);
		commit(modCampaign, tc, "[Niederösterreich]");
		completeRoundRobin(tc);
	}

	@Test
	public void testRoundRobinProhibitedNoReset()
		throws PersistenceLayerException
	{
		verifyCleanStart();
		TestContext tc = new TestContext();
		commit(testCampaign, tc, "[Yarra Valley]");
		emptyCommit(modCampaign, tc);
		completeRoundRobin(tc);
	}
}
