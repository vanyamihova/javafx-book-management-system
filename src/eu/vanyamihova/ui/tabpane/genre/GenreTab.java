package eu.vanyamihova.ui.tabpane.genre;

import javafx.scene.control.Tab;
import eu.vanyamihova.ui.tabpane.Refreshable;

/**
 * @author Vanya Mihova <vanya.mihova89@gmail.com>
 * @since 2019-05-18
 */
public final class GenreTab extends Tab implements Refreshable {

    private GenreTabContent genreTabContent = new GenreTabContent();

    public GenreTab() {
        super("Genres");
        setContent(genreTabContent);
        setClosable(false);
    }

    @Override
    public void refresh() {
        genreTabContent.refresh();
    }
}
