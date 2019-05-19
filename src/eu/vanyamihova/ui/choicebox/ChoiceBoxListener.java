package eu.vanyamihova.ui.choicebox;

/**
 * @author Vanya Mihova <vanya.mihova89@gmail.com>
 * @since 2019-05-18
 */
public interface ChoiceBoxListener<T> {
    void onSelectItem(T model);
}
