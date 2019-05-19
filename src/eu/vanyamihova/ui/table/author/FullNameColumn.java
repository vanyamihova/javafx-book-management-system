package eu.vanyamihova.ui.table.author;

import javafx.scene.control.TableColumn;
import eu.vanyamihova.model.Relation;
import eu.vanyamihova.ui.table.ObservableValueWrapper;

/**
 * @author Vanya Mihova <vanya.mihova89@gmail.com>
 * @since 2019-05-18
 */
public class FullNameColumn extends TableColumn<Relation, String> {

    public FullNameColumn(double width) {
        super("Author");
        setPrefWidth(width);
        setCellValueFactory(p -> new ObservableValueWrapper<String>() {
            @Override
            public String getValue() {
                return p.getValue().getAuthor().getFullName();
            }
        });
    }
}
