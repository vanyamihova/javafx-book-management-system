package eu.vanyamihova.ui.tabpane.book;

import javafx.scene.control.Tab;
import eu.vanyamihova.ui.tabpane.Refreshable;

/**
 * @author Vanya Mihova <vanya.mihova89@gmail.com>
 * @since 2019-05-16
 */
public final class BookTab extends Tab implements Refreshable {

    private BookTabContent bookTabContent = new BookTabContent();

    public BookTab() {
        super("Books");
        setContent(bookTabContent);
        setClosable(false);
    }

    @Override
    public void refresh() {
        bookTabContent.refresh();
    }
}
