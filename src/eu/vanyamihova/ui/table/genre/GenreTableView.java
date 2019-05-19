package eu.vanyamihova.ui.table.genre;

import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import eu.vanyamihova.launch.WindowConfiguration;
import eu.vanyamihova.model.Genre;
import eu.vanyamihova.model.Relation;

import java.util.Arrays;

/**
 * @author Vanya Mihova <vanya.mihova89@gmail.com>
 * @since 2019-05-18
 */
public class GenreTableView extends TableView<Relation> {

    public GenreTableView(GenreTableEventHandler eventHandler) {
        setPrefSize(WindowConfiguration.WIDTH, WindowConfiguration.HEIGHT - 80);
        getColumns().addAll(Arrays.asList(
                new GenreLabelColumn(WindowConfiguration.WIDTH)
        ));

        setRowFactory(tv -> {
            TableRow<Relation> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    Relation relation = row.getItem();
                    eventHandler.onRowClick(relation.getGenre());
                }
            });
            return row;
        });
    }

    public interface GenreTableEventHandler {
        void onRowClick(Genre genre);
    }
}
