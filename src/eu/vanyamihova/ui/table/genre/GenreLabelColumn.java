package eu.vanyamihova.ui.table.genre;

import javafx.scene.control.TableColumn;
import eu.vanyamihova.model.Relation;
import eu.vanyamihova.ui.table.ObservableValueWrapper;

/**
 * @author Vanya Mihova <vanya.mihova89@gmail.com>
 * @since 2019-05-18
 */
public class GenreLabelColumn extends TableColumn<Relation, String> {

    public GenreLabelColumn(double width) {
        super("Genre");
        setPrefWidth(width);
        setCellValueFactory(p -> new ObservableValueWrapper<String>() {
            @Override
            public String getValue() {
                return p.getValue().getGenre().getLabel();
            }
        });
    }
}

