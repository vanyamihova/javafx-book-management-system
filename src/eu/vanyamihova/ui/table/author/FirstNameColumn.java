package eu.vanyamihova.ui.table.author;

import javafx.scene.control.TableColumn;
import eu.vanyamihova.model.Relation;
import eu.vanyamihova.ui.table.ObservableValueWrapper;

/**
 * @author Vanya Mihova <vanya.mihova89@gmail.com>
 * @since 2019-05-16
 */
public final class FirstNameColumn extends TableColumn<Relation, String> {

    public FirstNameColumn(double width) {
        super("First name");
        setPrefWidth(width);
        setCellValueFactory(p -> new ObservableValueWrapper<String>() {
            @Override
            public String getValue() {
                return p.getValue().getAuthor().getFirstName();
            }
        });
    }
}
