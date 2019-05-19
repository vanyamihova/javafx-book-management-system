package eu.vanyamihova.ui.tabpane;

import javafx.scene.control.TabPane;
import eu.vanyamihova.ui.tabpane.author.AuthorTab;
import eu.vanyamihova.ui.tabpane.book.BookTab;
import eu.vanyamihova.ui.tabpane.genre.GenreTab;
import eu.vanyamihova.ui.tabpane.search.SearchTab;

/**
 * @author Vanya Mihova <vanya.mihova89@gmail.com>
 * @since 2019-05-16
 */
public final class DashboardTabPane extends TabPane {

    public DashboardTabPane() {
        super(new AuthorTab(), new GenreTab(), new BookTab(), new SearchTab());

        getSelectionModel().selectedItemProperty().addListener(
                (ov, t, t1) -> {
                    if (t1 instanceof Refreshable) {
                        ((Refreshable) t1).refresh();
                    }
                }
        );
    }

}
