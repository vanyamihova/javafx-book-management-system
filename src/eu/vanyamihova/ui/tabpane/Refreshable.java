package eu.vanyamihova.ui.tabpane;

/**
 * Indicates that a class can refresh it's content data.
 * <p>
 * It's implemented in {@link BaseTabContent}, so every {@code TabContent}
 * will have this feature. To make it work properly, every child should
 * create it's own implementation.
 *
 * @author Vanya Mihova <vanya.mihova89@gmail.com>
 * @since 2019-05-18
 */
public interface Refreshable {
    void refresh();
}
