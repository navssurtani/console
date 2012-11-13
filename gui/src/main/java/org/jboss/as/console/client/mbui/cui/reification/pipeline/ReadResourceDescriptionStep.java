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
package org.jboss.as.console.client.mbui.cui.reification.pipeline;

import com.allen_sauer.gwt.log.client.Log;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;
import org.jboss.as.console.client.shared.dispatch.DispatchAsync;

import static java.lang.Boolean.TRUE;

/**
 * @author Harald Pehl
 * @date 11/12/2012
 */
public class ReadResourceDescriptionStep extends ReificationStep
{
    final DispatchAsync dispatcher;

    @Inject
    public ReadResourceDescriptionStep(final DispatchAsync dispatcher)
    {
        super("read resource descriptions");
        this.dispatcher = dispatcher;
    }


    @Override
    public void execute(final AsyncCallback<Boolean> callback)
    {
        Log.warn("Not yet implemented");
        // TODO Read resource metadata for *all* interaction units and set them by calling
        // ResourceMapping.setResourceDescription(ModelNode);
        callback.onSuccess(TRUE);
    }
}