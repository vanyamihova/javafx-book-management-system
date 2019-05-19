package eu.vanyamihova.ui.textfield;

import eu.vanyamihova.launch.WindowConfiguration;

/**
 * @author Vanya Mihova <vanya.mihova89@gmail.com>
 * @since 2019-05-18
 */
public final class LabelTextField  extends BaseTextField {

    public LabelTextField() {
        setPromptText("Label");
        minWidth(WindowConfiguration.COLUMN);
        maxWidth(WindowConfiguration.COLUMN);
        prefWidth(WindowConfiguration.COLUMN);
    }

}