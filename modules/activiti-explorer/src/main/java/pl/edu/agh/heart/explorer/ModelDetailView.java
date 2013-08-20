/* @(#) $Id$
 *
 * Copyright (c) 2000-2013 ComArch S.A. All Rights Reserved.
 * Any usage, duplication or redistribution of this software
 * is allowed only according to separate agreement prepared
 * in written between ComArch and authorized party.
 */
package pl.edu.agh.heart.explorer;

import pl.edu.agh.heart.comm.HeartRepository;
import pl.edu.agh.heart.model.HMRModel;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.TextArea;

/** @author ja */
public class ModelDetailView extends TabSheet {
    private HeartPage masterPage;
    private TextArea typesTA = new TextArea();
    private TextArea attrsTA = new TextArea();
    private TextArea schemesRulesTA = new TextArea();
    private TextArea callbacksTA = new TextArea();
    private String modelName;
    private String userName;
    private HMRModel model;
    
    public ModelDetailView(HeartPage masterPage) {
        addTab(typesTA, "Types");
        addTab(attrsTA, "Attributes");
        addTab(schemesRulesTA, "Schemes & Rules");
        addTab(callbacksTA, "Callbacks");
        typesTA.setWidth("100%");
        attrsTA.setWidth("100%");
        schemesRulesTA.setWidth("100%");
        callbacksTA.setWidth("100%");
        typesTA.setHeight("100%");
        attrsTA.setHeight("100%");
        schemesRulesTA.setHeight("100%");
        callbacksTA.setHeight("100%");
    }
    
    public void setData(HMRModel model, String modelName, String userName) {
        typesTA.setValue(model.getTypes());
        attrsTA.setValue(model.getAttributes());
        schemesRulesTA.setValue(model.getSchemesAndRules());
        callbacksTA.setValue(model.getCallbacks());
        this.modelName = modelName;
        this.userName = userName;
        this.model = model;
    }
    
    public void clear() {
        modelName = userName = null;
        model = null;
        typesTA.setValue(null);
        attrsTA.setValue(null);
        schemesRulesTA.setValue(null);
        callbacksTA.setValue(null);
    }
    
    public void saveContent() {
        if (model != null) {
            model.parseData(typesTA.getValue().toString());
            model.parseData(attrsTA.getValue().toString());
            model.parseData(schemesRulesTA.getValue().toString());
            model.parseData(callbacksTA.getValue().toString());
            HeartRepository hr = masterPage.getHeartRepository();
            try {
                hr.pushModelHMR(modelName, userName, model.toString());
            } catch (Exception exception) {
                // TODO Handle exception using log4j or CLog
            }
        }
    }
}
