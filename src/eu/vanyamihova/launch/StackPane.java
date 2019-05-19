package eu.vanyamihova.launch;

import eu.vanyamihova.ui.tabpane.DashboardTabPane;

/**
 * {@code StackPane} wrapper to load custom content.
 *
 * @author Vanya Mihova <vanya.mihova89@gmail.com>
 * @since 2019-05-16
 */
final class StackPane extends javafx.scene.layout.StackPane {

    StackPane() {
        getChildren().add(new DashboardTabPane());
    }

}
