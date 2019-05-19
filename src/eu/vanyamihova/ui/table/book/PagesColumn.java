package eu.vanyamihova.ui.table.book;

import javafx.scene.control.TableColumn;
import eu.vanyamihova.model.Relation;
import eu.vanyamihova.ui.table.ObservableValueWrapper;

/**
 * @author Vanya Mihova <vanya.mihova89@gmail.com>
 * @since 2019-05-17
 */
public final class PagesColumn extends TableColumn<Relation, Integer> {

    public PagesColumn(double width) {
        super("Pages");
        setPrefWidth(width);
        setCellValueFactory(p -> new ObservableValueWrapper<Integer>() {
            @Override
            public Integer getValue() {
                return p.getValue().getBook().getPages();
            }
        });
    }
}
