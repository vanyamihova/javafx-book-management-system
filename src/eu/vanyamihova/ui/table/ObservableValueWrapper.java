package eu.vanyamihova.ui.table;

import javafx.beans.InvalidationListener;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

/**
 * @author Vanya Mihova <vanya.mihova89@gmail.com>
 * @since 2019-05-17
 */
public abstract class ObservableValueWrapper<T> implements ObservableValue<T> {
    @Override
    public void addListener(ChangeListener listener) {

    }

    @Override
    public void removeListener(ChangeListener listener) {

    }

    @Override
    public void addListener(InvalidationListener listener) {

    }

    @Override
    public void removeListener(InvalidationListener listener) {

    }
}
