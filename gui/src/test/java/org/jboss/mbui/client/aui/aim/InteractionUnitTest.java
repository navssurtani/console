/*
 * JBoss, Home of Professional Open Source
 * Copyright 2011 Red Hat Inc. and/or its affiliates and other contributors
 * as indicated by the @author tags. All rights reserved.
 * See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 *
 * This copyrighted material is made available to anyone wishing to use,
 * modify, copy, or redistribute it subject to the terms and conditions
 * of the GNU Lesser General Public License, v. 2.1.
 * This program is distributed in the hope that it will be useful, but WITHOUT A
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A
 * PARTICULAR PURPOSE.  See the GNU Lesser General Public License for more details.
 * You should have received a copy of the GNU Lesser General Public License,
 * v.2.1 along with this distribution; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA  02110-1301, USA.
 */
package org.jboss.mbui.client.aui.aim;

import org.junit.Before;
import org.junit.Test;

import static org.jboss.mbui.client.aui.aim.InteractionRole.Overview;
import static org.junit.Assert.*;

/**
 * @author Harald Pehl
 * @date 10/26/2012
 */
public class InteractionUnitTest
{
    InteractionUnit cut;

    @Before
    public void setUp() throws Exception
    {
        this.cut = new InteractionUnit("test");
    }

    @Test
    public void newInstance()
    {
        assertFalse(cut.isComposite());
        assertEquals(Overview, cut.getRole());
        assertNotNull(cut.getEntityContext());
        assertEquals("test" + InteractionUnit.ENTITY_CONTEXT_SUFFIX, cut.getEntityContext().getId());
    }

    @Test
    public void parentChild()
    {
        InteractionUnit foo = new InteractionUnit("foo");
        InteractionUnit bar = new InteractionUnit("bar");

        cut.add(foo);
        assertTrue(cut.isComposite());
        assertEquals(foo, cut.getChildren().get(0));
        assertEquals(cut, foo.getParent());

        cut.add(bar);
        assertTrue(cut.isComposite());
        assertEquals(bar, cut.getChildren().get(1));
        assertEquals(cut, bar.getParent());

        cut.remove(bar);
        assertTrue(cut.isComposite());
        assertFalse(cut.getChildren().contains(bar));
        assertNull(bar.getParent());

        cut.remove(foo);
        assertFalse(cut.isComposite());
        assertFalse(cut.getChildren().contains(foo));
        assertNull(foo.getParent());
    }
}
