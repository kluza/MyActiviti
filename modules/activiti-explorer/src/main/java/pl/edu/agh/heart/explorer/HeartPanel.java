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

/** @author ja */
public class HeartPanel extends DetailPanel {
    private Tree modelTree;
    private TextArea text = new TextArea();
    private HeartPage masterPage;
    private HorizontalLayout layout;
    private ModelDetailView modelDetails;
    
    public HeartPanel(HeartPage p) {
        masterPage = p;
    }
    
    public void attach() {
        super.attach();
        setImmediate(true);
        layout = new HorizontalLayout();
        layout.setImmediate(true);
        addDetailComponent(layout);
        setDetailExpandRatio(layout, 1.0F);
        modelTree = ModelTree.get(this);
        layout.addComponent(modelTree);
//        layout.addComponent(text);
        modelDetails = new ModelDetailView();
        layout.addComponent(modelDetails);
        layout.setExpandRatio(modelTree, 1);
        layout.setExpandRatio(modelDetails, 8);
        layout.setSizeFull();
        layout.setHeight("100%");
        modelDetails.setHeight("100%");
    }
    
    public void displayDetails(HMRModel model) {
        modelDetails.setData(model);
    }
    
    TextArea getText() {
        return text;
    }
    
    public HeartPage getMasterPage() {
        return masterPage;
    }
}
