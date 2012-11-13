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
package org.jboss.as.console.client.mbui.cui.reification;

import org.jboss.as.console.client.mbui.aui.aim.Container;
import org.jboss.as.console.client.mbui.aui.aim.InteractionUnit;
import org.jboss.as.console.client.mbui.cui.Context;
import org.jboss.as.console.client.mbui.cui.ReificationStrategy;
import org.jboss.as.console.client.mbui.cui.reification.pipeline.ReificationPipeline;

import javax.inject.Inject;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Harald Pehl
 * @date 10/25/2012
 */
public class Reificator
{
    final ReificationPipeline pipeline;
    final Set<ReificationStrategy<ReificationWidget>> strategies;

    @Inject
    public Reificator(final ReificationPipeline pipeline)
    {
        this.pipeline = pipeline;
        this.strategies = new HashSet<ReificationStrategy<ReificationWidget>>();
        this.strategies.add(new OrderIndependanceStrategy());
        this.strategies.add(new ChoiceStrategy());
        this.strategies.add(new SelectStrategy());
        this.strategies.add(new FormStrategy());
    }

    public ReificationWidget reify(final InteractionUnit interactionUnit, final Context context)
    {
        ReificationWidget result = null;
        if (interactionUnit != null)
        {
            assert !interactionUnit.hasParent() : "Entry point interaction units are not expected to have parents";

            result = startReification(interactionUnit, context);
        }
        return result;
    }

    private ReificationWidget startReification(final InteractionUnit parentUnit, final Context context)
    {
        ReificationWidget parentWidget = null;
        ReificationStrategy<ReificationWidget> strategy = resolve(parentUnit);
        if (strategy != null)
        {
            parentWidget = strategy.reify(parentUnit, context);
            if (parentWidget != null)
            {
                if (parentUnit instanceof Container)
                {
                    Container container = (Container) parentUnit;
                    for (InteractionUnit childUnit : container.getChildren())
                    {
                        try {
                            context.push();
                            ReificationWidget childWidget = startReification(childUnit, context);
                            if (childWidget != null)
                            {
                                parentWidget.add(childWidget, childUnit, container);
                            }
                        } catch (Exception e) {
                            context.pop();
                        }
                    }
                }
            }
        }
        return parentWidget;
    }

    private ReificationStrategy<ReificationWidget> resolve(InteractionUnit interactionUnit)
    {
        ReificationStrategy<ReificationWidget> match = null;
        for (ReificationStrategy<ReificationWidget> strategy : strategies)
        {
            if(strategy.appliesTo(interactionUnit))
            {
                match = strategy;
                break;
            }
        }
        return match;
    }
}