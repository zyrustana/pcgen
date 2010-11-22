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
package plugin.lsttokens.testsupport;

import org.junit.Test;

import pcgen.cdom.base.CDOMObject;
import pcgen.cdom.enumeration.ObjectKey;
import pcgen.cdom.reference.CDOMSingleRef;
import pcgen.persistence.PersistenceLayerException;
import pcgen.rules.context.LoadContext;

public abstract class AbstractItemTokenTestCase<T extends CDOMObject, TC extends CDOMObject>
		extends AbstractTokenTestCase<T>
{

	public abstract Class<TC> getTargetClass();

	public abstract boolean isClearLegal();

	public abstract ObjectKey<CDOMSingleRef<TC>> getObjectKey();

	@Test
	public void testInvalidInputEmpty() throws PersistenceLayerException
	{
		assertFalse(parse(""));
		assertNull(primaryProf.get(getObjectKey()));
		assertNoSideEffects();
	}

	@Test
	public void testInvalidInputString() throws PersistenceLayerException
	{
		assertTrue(parse("String"));
		assertFalse(primaryContext.ref.validate(null));
	}

	@Test
	public void testInvalidInputType() throws PersistenceLayerException
	{
		assertTrue(parse("TestType"));
		assertFalse(primaryContext.ref.validate(null));
	}

	@Test
	public void testInvalidInputJoinedComma() throws PersistenceLayerException
	{
		construct(primaryContext, "TestWP1");
		construct(primaryContext, "TestWP2");
		boolean ret = parse("TestWP1,TestWP2");
		if (ret)
		{
			assertFalse(primaryContext.ref.validate(null));
		}
		else
		{
			assertNull(primaryProf.get(getObjectKey()));
			assertNoSideEffects();
		}
	}

	@Test
	public void testInvalidInputJoinedPipe() throws PersistenceLayerException
	{
		construct(primaryContext, "TestWP1");
		construct(primaryContext, "TestWP2");
		boolean ret = parse("TestWP1|TestWP2");
		if (ret)
		{
			assertFalse(primaryContext.ref.validate(null));
		}
		else
		{
			assertNull(primaryProf.get(getObjectKey()));
			assertNoSideEffects();
		}
	}

	@Test
	public void testInvalidInputJoinedDot() throws PersistenceLayerException
	{
		construct(primaryContext, "TestWP1");
		construct(primaryContext, "TestWP2");
		boolean ret = parse("TestWP1.TestWP2");
		if (ret)
		{
			assertFalse(primaryContext.ref.validate(null));
		}
		else
		{
			assertNull(primaryProf.get(getObjectKey()));
			assertNoSideEffects();
		}
	}

	@Test
	public void testInvalidInputAny() throws PersistenceLayerException
	{
		try
		{
			boolean result = parse("ANY");
			if (result)
			{
				assertFalse(primaryContext.ref.validate(null));
			}
		}
		catch (IllegalArgumentException e)
		{
			// This is okay too
		}
	}

	@Test
	public void testInvalidInputCheckType() throws PersistenceLayerException
	{
		try
		{
			boolean result = getToken().parseToken(primaryContext, primaryProf,
					"TYPE=TestType").passed();
			if (result)
			{
				assertFalse(primaryContext.ref.validate(null));
			}
		}
		catch (IllegalArgumentException e)
		{
			// This is okay too
		}
	}

	@Test
	public void testReplacementInputs() throws PersistenceLayerException
	{
		String[] unparsed;
		construct(primaryContext, "TestWP1");
		construct(primaryContext, "TestWP2");
		if (isClearLegal())
		{
			assertTrue(parse(".CLEAR"));
			unparsed = getToken().unparse(primaryContext, primaryProf);
			assertNull("Expected item to be equal", unparsed);
		}
		assertTrue(parse("TestWP1"));
		assertTrue(parse("TestWP2"));
		unparsed = getToken().unparse(primaryContext, primaryProf);
		assertEquals("Expected item to be equal", "TestWP2", unparsed[0]);
		if (isClearLegal())
		{
			assertTrue(parse(".CLEAR"));
			unparsed = getToken().unparse(primaryContext, primaryProf);
			assertNull("Expected item to be equal", unparsed);
		}
	}

	@Test
	public void testValidInputs() throws PersistenceLayerException
	{
		construct(primaryContext, "TestWP1");
		assertTrue(parse("TestWP1"));
		assertTrue(primaryContext.ref.validate(null));
	}

	@Test
	public void testRoundRobinOne() throws PersistenceLayerException
	{
		construct(primaryContext, "TestWP1");
		construct(secondaryContext, "TestWP1");
		runRoundRobin("TestWP1");
	}

	protected void construct(LoadContext loadContext, String one)
	{
		loadContext.ref.constructCDOMObject(getTargetClass(), one);
	}

	@Override
	protected String getAlternateLegalValue()
	{
		return "TestWP2";
	}

	@Override
	protected String getLegalValue()
	{
		return "TestWP1";
	}


	@Test
	public void testUnparseNull() throws PersistenceLayerException
	{
		primaryProf.put(getObjectKey(), null);
		assertNull(getToken().unparse(primaryContext, primaryProf));
	}

	@Test
	public void testUnparseLegal() throws PersistenceLayerException
	{
		CDOMSingleRef<TC> o = primaryContext.ref.getCDOMReference(getTargetClass(), getLegalValue());
		primaryProf.put(getObjectKey(), o);
		expectSingle(getToken().unparse(primaryContext, primaryProf), o.getLSTformat(false));
	}

	@Test
	public void testUnparseGenericsFail() throws PersistenceLayerException
	{
		ObjectKey objectKey = getObjectKey();
		primaryProf.put(objectKey, new Object());
		try
		{
			getToken().unparse(primaryContext, primaryProf);
			fail();
		}
		catch (ClassCastException e)
		{
			//Yep!
		}
	}

	@Override
	protected ConsolidationRule getConsolidationRule()
	{
		return ConsolidationRule.OVERWRITE;
	}
}
