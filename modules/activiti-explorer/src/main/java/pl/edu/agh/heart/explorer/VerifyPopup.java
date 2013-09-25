/* @(#) $Id$
 *
 * Copyright (c) 2000-2013 ComArch S.A. All Rights Reserved.
 * Any usage, duplication or redistribution of this software
 * is allowed only according to separate agreement prepared
 * in written between ComArch and authorized party.
 */
package pl.edu.agh.heart.explorer;

import org.activiti.explorer.ui.custom.PopupWindow;
import pl.edu.agh.heart.comm.HeartRepository;
import pl.edu.agh.heart.constants.VerifyMode;
import pl.edu.agh.heart.model.HMRModel;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.Reindeer;

/** @author ja */
public class VerifyPopup extends PopupWindow {
    private VerticalLayout windowLayout;
    private ComboBox vModeCombo;
    private ComboBox schemeCombo;
    private HeartRepository hr;
    private String modelName;
    private String userName;
    private HMRModel model;
    private TextField responseText = new TextField("Result");
    private Button verButton;
    
    public VerifyPopup(HeartRepository hr, String modelName, String userName, HMRModel model) {
        this.hr = hr;
        this.modelName = modelName;
        this.userName = userName;
        this.model = model;
        windowLayout = (VerticalLayout) getContent();
        initWindow();
        initVModeCombo();
        initSchemeCombo();
        initVerButton();
        initLayout();
    }
    
    private void initWindow() {
        windowLayout.setSpacing(true);
        addStyleName(Reindeer.WINDOW_LIGHT);
        setModal(true);
        setWidth("460px");
        setHeight("470px");
        center();
        setCaption("Choose verification method and schema");
    }
    
    private void initVModeCombo() {
        vModeCombo = new ComboBox("Verification mode");
        vModeCombo.addItem(VerifyMode.COMPLETE);
        vModeCombo.addItem(VerifyMode.CONTRADICT);
        vModeCombo.addItem(VerifyMode.REDUCE);
        vModeCombo.addItem(VerifyMode.SUBSUME);
    }
    
    private void initSchemeCombo() {
        schemeCombo = new ComboBox("Scheme");
        for (String sName: model.getSchemeNames()) {
            schemeCombo.addItem(sName);
        }
    }
    
    private void initVerButton() {
        verButton = new Button("Verify");
        verButton.addListener(new Button.ClickListener() {
            public void buttonClick(ClickEvent pEvent) {
                String vMode = (String) vModeCombo.getValue();
                String scheme = (String) schemeCombo.getValue();
                try {
                    boolean result = hr.verifyModel(modelName, userName, vMode, scheme);
                    responseText.setValue(String.valueOf(result));
                } catch (Exception e) {
                    responseText.setValue("Error");
                }
            }
        });
    }
    
    private void initLayout() {
        addComponent(vModeCombo);
        addComponent(schemeCombo);
        addComponent(verButton);
        addComponent(responseText);
    }
}
