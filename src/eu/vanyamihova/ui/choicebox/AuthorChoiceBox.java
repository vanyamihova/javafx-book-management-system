package eu.vanyamihova.ui.choicebox;

import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ChoiceBox;
import eu.vanyamihova.launch.WindowConfiguration;
import eu.vanyamihova.model.Author;
import eu.vanyamihova.model.Relation;

import java.util.List;

/**
 * @author Vanya Mihova <vanya.mihova89@gmail.com>
 * @since 2019-05-17
 */
public class AuthorChoiceBox extends ChoiceBox {

    private ChoiceBoxListener<Author> choiceBoxListener;

    public AuthorChoiceBox() {
        minWidth(WindowConfiguration.COLUMN);
        maxWidth(WindowConfiguration.COLUMN);
        prefWidth(WindowConfiguration.COLUMN);
        getSelectionModel().selectedItemProperty()
                .addListener((ChangeListener<Choice>) (observableValue, oldChoice, newChoice) -> {
                    if (choiceBoxListener == null || newChoice == null) {
                        return;
                    }
                    choiceBoxListener.onSelectItem((Author) newChoice.getModel());
                });
    }

    private ObservableList<Choice> generateChoices(List<Relation> relations) {
        ObservableList<Choice> choices = FXCollections.observableArrayList();
        for (Relation relation : relations) {
            Author author = relation.getAuthor();
            choices.add(new Choice(author, author.getFullName()));
        }
        return choices;
    }

    public void setChoiceBoxListener(ChoiceBoxListener<Author> choiceBoxListener) {
        this.choiceBoxListener = choiceBoxListener;
    }

    public void setItems(List<Relation> relations) {
        ObservableList<Choice> choices = generateChoices(relations);
        setItems(choices);
    }

    public void reset() {
        getSelectionModel().clearSelection();
    }

    public void select(Author author) {
        getSelectionModel().select(new Choice(author, author.getFullName()));
    }

}
