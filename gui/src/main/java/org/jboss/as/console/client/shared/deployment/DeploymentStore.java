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

package org.jboss.as.console.client.shared.deployment;

import com.google.gwt.user.client.rpc.AsyncCallback;
import org.jboss.as.console.client.shared.deployment.model.DeployedEjb;
import org.jboss.as.console.client.shared.deployment.model.DeployedEndpoint;
import org.jboss.as.console.client.shared.deployment.model.DeployedPersistenceUnit;
import org.jboss.as.console.client.shared.deployment.model.DeployedServlet;
import org.jboss.as.console.client.shared.deployment.model.DeploymentRecord;
import org.jboss.as.console.client.shared.deployment.model.DeploymentSubsystem;
import org.jboss.as.console.client.shared.dispatch.impl.DMRResponse;

import java.util.List;
import java.util.Set;

/**
 * Responsible for loading deployment data
 * and turning it a usable representation.
 *
 * @author Heiko Braun
 * @author Stan Silvert
 * @date 1/31/11
 */
public interface DeploymentStore {
    void loadServerGroupDeployments(AsyncCallback<List<DeploymentRecord>> callback);
    void loadDeployments(AsyncCallback<List<DeploymentRecord>> callback);
    void loadSubdeployments(DeploymentRecord deployment, AsyncCallback<List<DeploymentRecord>> callback);
    void loadSubsystems(DeploymentRecord deployment, AsyncCallback<List<DeploymentSubsystem>> callback);
    void loadEjbs(DeploymentSubsystem subsystem, AsyncCallback<List<DeployedEjb>> callback);
    void loadPersistenceUnits(DeploymentSubsystem subsystem, AsyncCallback<List<DeployedPersistenceUnit>> callback);
    void loadServlets(DeploymentSubsystem subsystemn, AsyncCallback<List<DeployedServlet>> callback);
    void loadEndpoints(DeploymentSubsystem subsystem, AsyncCallback<List<DeployedEndpoint>> callback);
    void addToServerGroups( Set<String> serverGroups, boolean enable, DeploymentRecord deploymentRecord, AsyncCallback<DMRResponse> callback);
    void removeContent(DeploymentRecord deploymentRecord, AsyncCallback<DMRResponse> callback);
    void enableDisableDeployment(DeploymentRecord deploymentRecord, AsyncCallback<DMRResponse> callback);
    void removeDeploymentFromGroup(DeploymentRecord deploymentRecord, AsyncCallback<DMRResponse> callback);
    void deleteDeployment(DeploymentRecord deploymentRecord, AsyncCallback<Boolean> callback);
}
