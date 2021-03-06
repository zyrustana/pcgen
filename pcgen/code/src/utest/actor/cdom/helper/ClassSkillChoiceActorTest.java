/*
 * 
 * Copyright (c) 2010 Tom Parker <thpr@users.sourceforge.net>
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
package actor.cdom.helper;

import org.junit.Test;

import pcgen.cdom.base.PersistentChoiceActor;
import pcgen.cdom.helper.ClassSkillChoiceActor;
import pcgen.core.Skill;
import actor.testsupport.AbstractPersistentCDOMChoiceActorTestCase;

public class ClassSkillChoiceActorTest extends
		AbstractPersistentCDOMChoiceActorTestCase<Skill>
{

	static ClassSkillChoiceActor pca = new ClassSkillChoiceActor(null, null);

	@Test
	public void testEmpty()
	{
		// Just to get Eclipse to recognize this as a JUnit 4.0 Test Case
	}

	@Override
	public PersistentChoiceActor<Skill> getActor()
	{
		return pca;
	}

	@Override
	public Class<Skill> getCDOMClass()
	{
		return Skill.class;
	}
}
