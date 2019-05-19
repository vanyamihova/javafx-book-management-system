package eu.vanyamihova.ui.table.author;

import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import eu.vanyamihova.launch.WindowConfiguration;
import eu.vanyamihova.model.Author;
import eu.vanyamihova.model.Relation;

import java.util.Arrays;

/**
 * @author Vanya Mihova <vanya.mihova89@gmail.com>
 * @since 2019-05-16
 */
public final class AuthorTableView extends TableView<Relation> {

    public AuthorTableView(AuthorTableEventHandler eventHandler) {
        setPrefSize(WindowConfiguration.WIDTH, WindowConfiguration.HEIGHT - 80);
        getColumns().addAll(Arrays.asList(
                new FirstNameColumn((int) (WindowConfiguration.WIDTH / 2)),
                new LastNameColumn((int) (WindowConfiguration.WIDTH / 2))
        ));

        setRowFactory(tv -> {
            TableRow<Relation> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    Relation relation = row.getItem();
                    eventHandler.onRowClick(relation.getAuthor());
                }
            });
            return row;
        });
    }

    public interface AuthorTableEventHandler {
        void onRowClick(Author author);
    }
}
