package eu.vanyamihova.ui.table.search;

import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import eu.vanyamihova.launch.WindowConfiguration;
import eu.vanyamihova.model.Relation;
import eu.vanyamihova.ui.table.author.FullNameColumn;
import eu.vanyamihova.ui.table.book.*;
import eu.vanyamihova.ui.table.genre.GenreLabelColumn;

import java.util.Arrays;

/**
 * @author Vanya Mihova <vanya.mihova89@gmail.com>
 * @since 2019-05-16
 */
public final class ResultTableView extends TableView<Relation> {

    public ResultTableView(ObservableList<Relation> data) {
        setPrefSize(WindowConfiguration.WIDTH, WindowConfiguration.HEIGHT - 259);
        setItems(data);
        getColumns().addAll(Arrays.asList(
                new IsbnColumn(WindowConfiguration.COLUMN),
                new TitleColumn(WindowConfiguration.COLUMN * 2),
                new PagesColumn(WindowConfiguration.COLUMN),
                new PublishedColumn(WindowConfiguration.COLUMN),
                new FullNameColumn(WindowConfiguration.COLUMN),
                new GenreLabelColumn(WindowConfiguration.COLUMN)
        ));
    }

}
