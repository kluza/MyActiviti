package pl.edu.agh.heart.explorer;

import org.activiti.explorer.ui.custom.ToolBar;
import org.activiti.explorer.ui.custom.ToolbarEntry.ToolbarCommand;

/** @author ja */
public class HeartMenuBar extends ToolBar {
    private HeartPage heartPage;
    
    public HeartMenuBar(HeartPage p) {
        heartPage = p;
        addToolbarEntry("heart", "HeaRT", new ToolbarCommand() {
            
            public void toolBarItemSelected() {
                heartPage.showHeaRT();
            }
        });
        addToolbarEntry("hqed", "HQEd", new ToolbarCommand() {
            
            public void toolBarItemSelected() {
                heartPage.showHQEd();
            }
            
        });
    }
}
