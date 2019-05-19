package eu.vanyamihova.ui.textfield;

import eu.vanyamihova.launch.WindowConfiguration;

/**
 * @author Vanya Mihova <vanya.mihova89@gmail.com>
 * @since 2019-05-17
 */
public final class PublishedTextField extends BaseTextField {

    public PublishedTextField() {
        setPromptText("Published");
        minWidth(WindowConfiguration.COLUMN);
        maxWidth(WindowConfiguration.COLUMN);
        prefWidth(WindowConfiguration.COLUMN);
    }

    // TODO: override isValid() with pattern to be date

}

