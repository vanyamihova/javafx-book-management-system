package eu.vanyamihova.ui.tabpane.search;

import javafx.scene.control.Tab;
import eu.vanyamihova.ui.tabpane.Refreshable;

/**
 * @author Vanya Mihova <vanya.mihova89@gmail.com>
 * @since 2019-05-17
 */
public final class SearchTab extends Tab implements Refreshable {

    private SearchTabContent searchTabContent = new SearchTabContent();

    public SearchTab() {
        super("Search");
        setContent(searchTabContent);
        setClosable(false);
    }

    @Override
    public void refresh() {
        searchTabContent.refresh();
    }
}
