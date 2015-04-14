/*
 * Copyright 2015 JBoss Inc
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jbpm.console.ng.server.impl;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.dashbuilder.dataset.DataSetFactory;
import org.dashbuilder.dataset.def.DataSetDefRegistry;
import org.uberfire.commons.services.cdi.Startup;

@Startup
@ApplicationScoped
public class DashbuilderBootstrap {

    public static final String HUMAN_TASKS_DATASET = "jbpmHumanTasks";
    public static final String JBPM_DATASOURCE = "jdbc/jbpmConsoleDS";
    public static final String HUMAN_TASKS_TABLE = "AuditTaskImpl";
    @Inject
    protected DataSetDefRegistry dataSetDefRegistry;

    @PostConstruct
    protected void init() {
        registerDataSetDefinitions();
    }

    protected void registerDataSetDefinitions() {
        System.out.println("Bootstrapping Dashbuilder stuff.....");
        dataSetDefRegistry.registerDataSetDef(
                DataSetFactory.newSQLDataSetDef()
                .uuid(HUMAN_TASKS_DATASET)
                .dataSource(JBPM_DATASOURCE)
                .dbTable(HUMAN_TASKS_TABLE, false)
                .label("taskId")
                .label("actualOwner")
                .date("createdOn")
                .text("name")
                .text("description")
                .label("status")        
                .buildDef());
    }
}