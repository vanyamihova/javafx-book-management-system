package eu.vanyamihova.ui.tabpane;

import javafx.scene.layout.Pane;

/**
 * @author Vanya Mihova <vanya.mihova89@gmail.com>
 * @since 2019-05-18
 */
public abstract class BaseTabContent extends Pane implements Refreshable {

    /**
     * Override this method if the {@code TabContent} needs to refresh some data
     */
    @Override
    public void refresh() {
    }
}
