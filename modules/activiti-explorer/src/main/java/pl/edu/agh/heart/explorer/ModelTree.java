/* @(#) $Id$
 *
 * Copyright (c) 2000-2013 ComArch S.A. All Rights Reserved.
 * Any usage, duplication or redistribution of this software
 * is allowed only according to separate agreement prepared
 * in written between ComArch and authorized party.
 */
package pl.edu.agh.heart.explorer;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import pl.edu.agh.heart.comm.HeartRepository;
import pl.edu.agh.heart.model.HMRModel;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.ui.Tree;

/** @author ja */
public class ModelTree {
    public static Tree get(HeartPanel panel) {
        Tree tree = new Tree();
        tree.setImmediate(true);
        try {
            HeartRepository hr = panel.getMasterPage().getHeartRepository();
            Map<String, List<String>> modelMap = hr.getModelNames();
            for (String user: modelMap.keySet()) {
                tree.addItem(user);
                List<String> l = modelMap.get(user);
                if ((l == null) || l.isEmpty()) {
                    tree.setChildrenAllowed(user, false);
                } else {
                    for (String model: l) {
                        ModelTreeItem m = new ModelTreeItem(user, model);
                        tree.addItem(m);
                        tree.setChildrenAllowed(m, false);
                        tree.setParent(m, user);
                    }
                }
                tree.expandItemsRecursively(user);
            }
            tree.addListener(new ModelTreeListener(tree, panel));
        } catch (IOException exception) {
            String errMsg = "Communication exception";
            tree.addItem(errMsg);
            tree.setChildrenAllowed(errMsg, false);
        } catch (Exception exception) {
            String errMsg = "HeaRT connection failed!";
            tree.addItem(errMsg);
            tree.setChildrenAllowed(errMsg, false);
        } finally {
            return tree;
        }
    }
    
    static class ModelTreeItem {
        public String user, name;
        
        public ModelTreeItem(String user, String name) {
            this.user = user;
            this.name = name;
        }
        
        public String toString() {
            return name;
        }
    }
}


class ModelTreeListener implements ValueChangeListener {
    private Tree tree;
    private HeartPanel panel;
    
    public ModelTreeListener(Tree tree, HeartPanel panel) {
        this.tree = tree;
        this.panel = panel;
    }
    
    public void valueChange(ValueChangeEvent pEvent) {
        Object value = tree.getValue();
        if (value instanceof ModelTree.ModelTreeItem) {
            try {
                ModelTree.ModelTreeItem model = (ModelTree.ModelTreeItem) value;
                HeartRepository hr = panel.getMasterPage().getHeartRepository();
                String modelDef = hr.getModelHMR(model.name, model.user);
//                panel.getText().setValue(modelDef);
                if (modelDef != null) {
                    panel.displayDetails(new HMRModel(modelDef), model.name, model.user);
                } else {
                    panel.disableDetails();
                }
            } catch (IOException exception) {
                panel.getText().setValue("Couldn't connect to HeaRT");
            } catch (Exception exception) {
                panel.getText().setValue("HeaRT communication error");
            }
        }
    }
}
