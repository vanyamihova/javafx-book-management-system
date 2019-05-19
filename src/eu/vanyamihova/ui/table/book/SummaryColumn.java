package eu.vanyamihova.ui.table.book;

import javafx.scene.control.TableColumn;
import eu.vanyamihova.model.Relation;
import eu.vanyamihova.ui.table.ObservableValueWrapper;

/**
 * @author Vanya Mihova <vanya.mihova89@gmail.com>
 * @since 2019-05-17
 */
public final class SummaryColumn extends TableColumn<Relation, String> {

    public SummaryColumn(double width) {
        super("Summary");
        setPrefWidth(width);
        setCellValueFactory(p -> new ObservableValueWrapper<String>() {
            @Override
            public String getValue() {
                return p.getValue().getBook().getSummary();
            }
        });
    }
}
