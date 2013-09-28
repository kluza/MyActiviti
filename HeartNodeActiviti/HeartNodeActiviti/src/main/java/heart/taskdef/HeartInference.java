/* @(#) $Id$
 *
 * Copyright (c) 2000-2013 ComArch S.A. All Rights Reserved.
 * Any usage, duplication or redistribution of this software
 * is allowed only according to separate agreement prepared
 * in written between ComArch and authorized party.
 */
package heart.taskdef;

import org.activiti.designer.integration.servicetask.AbstractCustomServiceTask;
import org.activiti.designer.integration.servicetask.PropertyType;
import org.activiti.designer.integration.servicetask.annotation.Help;
import org.activiti.designer.integration.servicetask.annotation.Property;
import org.activiti.designer.integration.servicetask.annotation.Runtime;

/** @author ja */
@Runtime(javaDelegateClass = "pl.edu.agh.heart.taskdelegate.HeartDelegate")
@Help(displayHelpShort = "Run HeaRT table")
public class HeartInference extends AbstractCustomServiceTask {
    
    @Property(type = PropertyType.TEXT, displayName = "User name", required = true)
    private String userName;
    
    @Property(type = PropertyType.TEXT, displayName = "Model name", required = true)
    private String modelName;
    
    @Property(type = PropertyType.TEXT, displayName = "Table", required = true)
    private String table;
    
    @Override
    public String getName() {
        return "HeaRT table";
    }
    
    @Override
    public String getSmallIconPath() {
        return "icons/hekate_s.png";
    }
    
    @Override
    public String contributeToPaletteDrawer() {
        return "HeaRT";
    }
    
}
