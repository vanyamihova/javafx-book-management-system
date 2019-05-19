package eu.vanyamihova.ui.tabpane.author;

import javafx.scene.control.Tab;
import eu.vanyamihova.ui.tabpane.Refreshable;

/**
 * @author Vanya Mihova <vanya.mihova89@gmail.com>
 * @since 2019-05-16
 */
public final class AuthorTab extends Tab implements Refreshable {

    private AuthorTabContent authorTabContent = new AuthorTabContent();

    public AuthorTab() {
        super("Authors");
        setContent(authorTabContent);
        setClosable(false);
    }

    @Override
    public void refresh() {
        authorTabContent.refresh();
    }
}
