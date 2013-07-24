/* @(#) $Id$
 *
 * Copyright (c) 2000-2013 ComArch S.A. All Rights Reserved.
 * Any usage, duplication or redistribution of this software
 * is allowed only according to separate agreement prepared
 * in written between ComArch and authorized party.
 */
package org.activiti.editor.language.json.converter;

import java.util.Map;
import org.activiti.bpmn.model.BaseElement;
import org.activiti.bpmn.model.FieldExtension;
import org.activiti.bpmn.model.FlowElement;
import org.activiti.bpmn.model.ImplementationType;
import org.activiti.bpmn.model.ServiceTask;
import org.activiti.editor.constants.StencilConstants;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.node.ObjectNode;

/** @author ja */
public class HeartTaskJsonConverter extends BaseBpmnJsonConverter {
    private static final String USERNAME = "username";
    private static final String MODELNAME = "modelname";
    private static final String TABLE = "table";
    private static final String DELEGATE = "pl.edu.agh.heart.taskdelegate.HeartDelegate";
    
    protected void convertElementToJson(ObjectNode pPropertiesNode, FlowElement pFlowElement) {
        //nuffin, it's just a sevice task
    }
    
    protected FlowElement convertJsonToElement(JsonNode pElementNode, JsonNode pModelNode,
            Map<String, JsonNode> pShapeMap) {
        ServiceTask task = new ServiceTask();
        task.setImplementationType(ImplementationType.IMPLEMENTATION_TYPE_CLASS);
        task.setImplementation(DELEGATE);
        FieldExtension userName = new FieldExtension();
        String userNameValue = getPropertyValueAsString(USERNAME, pElementNode);
        System.out.println("\n\n#######\n\nuser: " + userNameValue + "\n\n########\n\n");
        userName.setFieldName("userName");
        userName.setStringValue(userNameValue);
        task.getFieldExtensions().add(userName);
        FieldExtension modelName = new FieldExtension();
        String modelNameValue = getPropertyValueAsString(MODELNAME, pElementNode);
        System.out.println("\n\n#######\n\nmodel: " + modelNameValue + "\n\n########\n\n");
        modelName.setFieldName("modelName");
        modelName.setStringValue(modelNameValue);
        task.getFieldExtensions().add(modelName);
        FieldExtension table = new FieldExtension();
        String tableValue = getPropertyValueAsString(TABLE, pElementNode);
        System.out.println("\n\n#######\n\ntable: " + tableValue + "\n\n########\n\n");
        table.setFieldName("table");
        table.setStringValue(tableValue);
        task.getFieldExtensions().add(table);
        convertJsonToFormProperties(pElementNode, task);
        return task;
    }
    
    protected String getStencilId(FlowElement pFlowElement) {
        return StencilConstants.STENCIL_TASK_HEART;
    }
    
    public static void fillJsonTypes(Map<String, Class<? extends BaseBpmnJsonConverter>> convertersToBpmnMap) {
        convertersToBpmnMap.put(STENCIL_TASK_HEART, HeartTaskJsonConverter.class);
    }
    
    public static void fillBpmnTypes(
            Map<Class<? extends BaseElement>, Class<? extends BaseBpmnJsonConverter>> convertersToJsonMap) {
        //nuffin, it's just a service task
    }
    
    public static void fillTypes(Map<String, Class<? extends BaseBpmnJsonConverter>> convertersToBpmnMap,
            Map<Class<? extends BaseElement>, Class<? extends BaseBpmnJsonConverter>> convertersToJsonMap) {
        fillJsonTypes(convertersToBpmnMap);
        fillBpmnTypes(convertersToJsonMap);
    }
    
}
