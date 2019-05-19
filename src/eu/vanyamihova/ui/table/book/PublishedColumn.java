package eu.vanyamihova.ui.table.book;

import javafx.scene.control.TableColumn;
import eu.vanyamihova.model.Relation;
import eu.vanyamihova.ui.table.ObservableValueWrapper;

import java.sql.Date;

/**
 * @author Vanya Mihova <vanya.mihova89@gmail.com>
 * @since 2019-05-17
 */
public final class PublishedColumn extends TableColumn<Relation, Date> {

    public PublishedColumn(double width) {
        super("Published");
        setPrefWidth(width);
        setCellValueFactory(p -> new ObservableValueWrapper<Date>() {
            @Override
            public Date getValue() {
                return p.getValue().getBook().getPublished();
            }
        });
    }
}
