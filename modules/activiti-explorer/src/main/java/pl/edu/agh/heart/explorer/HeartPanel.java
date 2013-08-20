/* @(#) $Id$
 *
 * Copyright (c) 2000-2013 ComArch S.A. All Rights Reserved.
 * Any usage, duplication or redistribution of this software
 * is allowed only according to separate agreement prepared
 * in written between ComArch and authorized party.
 */
package pl.edu.agh.heart.explorer;

import org.activiti.explorer.ui.custom.DetailPanel;
import pl.edu.agh.heart.model.HMRModel;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.Tree;
import com.vaadin.ui.VerticalLayout;

/** @author ja */
public class HeartPanel extends DetailPanel {
    private Tree modelTree;
    private TextArea text = new TextArea();
    private HeartPage masterPage;
    private HorizontalLayout mainLayout;
    private VerticalLayout sideLayout;
    private ModelDetailView modelDetails;
    
    public HeartPanel(HeartPage p) {
        masterPage = p;
    }
    
    public void attach() {
        super.attach();
        setImmediate(true);
        mainLayout = new HorizontalLayout();
        mainLayout.setImmediate(true);
        sideLayout = new VerticalLayout();
        sideLayout.setImmediate(true);
        addDetailComponent(mainLayout);
        setDetailExpandRatio(mainLayout, 1.0F);
        modelTree = ModelTree.get(this);
        mainLayout.addComponent(modelTree);
//        layout.addComponent(text);
        modelDetails = new ModelDetailView(masterPage);
        sideLayout.addComponent(modelDetails);
        mainLayout.addComponent(sideLayout);
        mainLayout.setExpandRatio(modelTree, 1);
        mainLayout.setExpandRatio(sideLayout, 8);
        mainLayout.setSizeFull();
        mainLayout.setHeight("100%");
        modelDetails.setHeight(300F, UNITS_PIXELS);
    }
    
    public void displayDetails(HMRModel model, String modelName, String userName) {
        modelDetails.setData(model, modelName, userName);
    }
    
    public void disableDetails() {
        modelDetails.clear();
    }
    
    TextArea getText() {
        return text;
    }
    
    public HeartPage getMasterPage() {
        return masterPage;
    }
    
    public ModelDetailView getModelDetails() {
        return modelDetails;
    }
}
