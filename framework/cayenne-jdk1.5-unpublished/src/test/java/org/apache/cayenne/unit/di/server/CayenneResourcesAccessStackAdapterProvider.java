/*****************************************************************
 *   Licensed to the Apache Software Foundation (ASF) under one
 *  or more contributor license agreements.  See the NOTICE file
 *  distributed with this work for additional information
 *  regarding copyright ownership.  The ASF licenses this file
 *  to you under the Apache License, Version 2.0 (the
 *  "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 ****************************************************************/
package org.apache.cayenne.unit.di.server;

import org.apache.cayenne.ConfigurationException;
import org.apache.cayenne.conn.DataSourceInfo;
import org.apache.cayenne.di.Provider;
import org.apache.cayenne.unit.AccessStackAdapter;
import org.apache.cayenne.unit.CayenneResources;

public class CayenneResourcesAccessStackAdapterProvider implements Provider<AccessStackAdapter> {

    protected CayenneResources resources;

    public CayenneResourcesAccessStackAdapterProvider(CayenneResources resources) {
        this.resources = resources;
    }

    public AccessStackAdapter get() throws ConfigurationException {
        DataSourceInfo connectionInfo = resources.getConnectionInfo();
        return resources.getAccessStackAdapter(connectionInfo.getAdapterClassName());
    }
}