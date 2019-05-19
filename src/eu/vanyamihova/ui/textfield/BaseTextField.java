package eu.vanyamihova.ui.textfield;

import javafx.scene.control.TextField;
import eu.vanyamihova.ui.Resetable;
import eu.vanyamihova.ui.Validable;

/**
 * @author Vanya Mihova <vanya.mihova89@gmail.com>
 * @since 2019-05-16
 */
public abstract class BaseTextField extends TextField implements Validable, Resetable {

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
