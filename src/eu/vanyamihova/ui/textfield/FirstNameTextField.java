package eu.vanyamihova.ui.textfield;

import eu.vanyamihova.launch.WindowConfiguration;

/**
 * @author Vanya Mihova <vanya.mihova89@gmail.com>
 * @since 2019-05-16
 */
public final class FirstNameTextField extends BaseTextField {

    public FirstNameTextField() {
        setPromptText("First name");
        minWidth(WindowConfiguration.COLUMN);
        maxWidth(WindowConfiguration.COLUMN);
        prefWidth(WindowConfiguration.COLUMN);
    }

}
