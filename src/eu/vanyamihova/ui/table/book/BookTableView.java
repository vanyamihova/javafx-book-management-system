package eu.vanyamihova.ui.table.book;

import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import eu.vanyamihova.launch.WindowConfiguration;
import eu.vanyamihova.model.Book;
import eu.vanyamihova.model.Relation;

import java.util.Arrays;

/**
 * @author Vanya Mihova <vanya.mihova89@gmail.com>
 * @since 2019-05-16
 */
public final class BookTableView extends TableView<Relation> {

    private BookTableEventHandler eventHandler;

    public BookTableView() {
        setPrefSize(WindowConfiguration.WIDTH, WindowConfiguration.HEIGHT - 373);
        getColumns().addAll(Arrays.asList(
                new IsbnColumn(WindowConfiguration.COLUMN),
                new TitleColumn(WindowConfiguration.COLUMN),
                new PagesColumn(WindowConfiguration.COLUMN),
                new PublishedColumn(WindowConfiguration.COLUMN),
                new SummaryColumn(WindowConfiguration.COLUMN * 3)
        ));

        setRowFactory(tv -> {
            TableRow<Relation> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    Relation relation = row.getItem();
                    if (eventHandler == null) {
                        return;
                    }
                    eventHandler.onRowClick(relation.getBook());
                }
            });
            return row;
        });
    }

    public void setBookTableEventHandler(BookTableEventHandler eventHandler) {
        this.eventHandler = eventHandler;
    }

    public interface BookTableEventHandler {
        void onRowClick(Book book);
    }
}
