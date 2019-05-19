package eu.vanyamihova.ui.textarea;

import javafx.scene.control.TextArea;
import eu.vanyamihova.launch.WindowConfiguration;
import eu.vanyamihova.ui.Resetable;
import eu.vanyamihova.ui.Validable;

/**
 * @author Vanya Mihova <vanya.mihova89@gmail.com>
 * @since 2019-05-17
 */
public final class SummaryTextArea extends TextArea implements Validable, Resetable {

    public SummaryTextArea() {
        setPromptText("Summary");
        minWidth(WindowConfiguration.WIDTH);
        maxWidth(WindowConfiguration.WIDTH);
        prefWidth(WindowConfiguration.WIDTH);
        setWrapText(true);
    }

    @Override
    public boolean isValid() {
        String value = getText();
        return value != null && !value.isEmpty();
    }

    @Override
    public void reset() {
        setText(null);
    }

}

