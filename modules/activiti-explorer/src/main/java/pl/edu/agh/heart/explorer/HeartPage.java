/* @(#) $Id$
 *
 * Copyright (c) 2000-2013 ComArch S.A. All Rights Reserved.
 * Any usage, duplication or redistribution of this software
 * is allowed only according to separate agreement prepared
 * in written between ComArch and authorized party.
 */
package pl.edu.agh.heart.explorer;

import java.io.IOException;
import org.activiti.explorer.ui.AbstractPage;
import org.activiti.explorer.ui.custom.ToolBar;
import pl.edu.agh.heart.comm.HeartRepository;
import com.vaadin.ui.AbstractSelect;

/** @author ja */
public class HeartPage extends AbstractPage {
    private HeartRepository heartRepository;
    private HeartPanel detailPanel;
    
    protected ToolBar createMenuBar() {
        return new HeartMenuBar(this);
    }
    
    protected AbstractSelect createSelectComponent() {
        return null;
    }
    
    public void refreshSelectNext() {
    }
    
    public void selectElement(int pIndex) {
    }
    
    @Override
    public void initUi() {
        super.initUi();
        try {
            heartRepository = new HeartRepository();
        } catch (IOException exception) {
            // TODO Handle exception using log4j or CLog
        }
        detailPanel = new HeartPanel(this);
        setDetailComponent(detailPanel);
    }
    
    public HeartRepository getHeartRepository() {
        return heartRepository;
    }
    
    public void showHeaRT() {
        setDetailComponent(new HeartPanel(this));
    }
}
