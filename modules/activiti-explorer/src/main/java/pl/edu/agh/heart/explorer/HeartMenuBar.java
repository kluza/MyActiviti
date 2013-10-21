/* @(#) $Id$
 *
 * Copyright (c) 2000-2013 ComArch S.A. All Rights Reserved.
 * Any usage, duplication or redistribution of this software
 * is allowed only according to separate agreement prepared
 * in written between ComArch and authorized party.
 */
package pl.edu.agh.heart.explorer;

import org.activiti.explorer.ui.custom.ToolBar;
import org.activiti.explorer.ui.custom.ToolbarEntry.ToolbarCommand;

/** @author Comarch */
public class HeartMenuBar extends ToolBar {
    private HeartPage heartPage;
    
    public HeartMenuBar(HeartPage p) {
        heartPage = p;
        addToolbarEntry("heart", "HeaRT", new ToolbarCommand() {
            
            public void toolBarItemSelected() {
                heartPage.showHeaRT();
            }
        });
    }
}
