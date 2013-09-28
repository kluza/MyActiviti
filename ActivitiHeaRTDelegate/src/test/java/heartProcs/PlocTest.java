/* @(#) $Id$
 *
 * Copyright (c) 2000-2013 ComArch S.A. All Rights Reserved.
 * Any usage, duplication or redistribution of this software
 * is allowed only according to separate agreement prepared
 * in written between ComArch and authorized party.
 */
package heartProcs;

import static org.junit.Assert.assertNotNull;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.junit.Test;

/** @author ja */
public class PlocTest {
    
    @Test
    public void test() {
        org.apache.log4j.BasicConfigurator.configure();
        ProcessEngine pEngine =
                ProcessEngineConfiguration.createStandaloneInMemProcessEngineConfiguration()
                        .buildProcessEngine();
        RepositoryService repService = pEngine.getRepositoryService();
        RuntimeService runService = pEngine.getRuntimeService();
        Deployment d =
                repService.createDeployment().addClasspathResource("procDefs/Ploc.bpmn20.xml").deploy();
        assertNotNull(d);
        ProcessInstance pInstance = runService.startProcessInstanceByKey("ploc");
        assertNotNull(pInstance);
    }
    
}
