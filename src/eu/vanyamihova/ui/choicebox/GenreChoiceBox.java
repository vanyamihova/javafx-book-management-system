package eu.vanyamihova.ui.choicebox;

import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ChoiceBox;
import eu.vanyamihova.launch.WindowConfiguration;
import eu.vanyamihova.model.Genre;
import eu.vanyamihova.model.Relation;

import java.util.List;

/**
 * @author Vanya Mihova <vanya.mihova89@gmail.com>
 * @since 2019-05-18
 */
public class GenreChoiceBox extends ChoiceBox {

    private Choice selectedChoice;
    private ChoiceBoxListener<Genre> choiceBoxListener;

    public GenreChoiceBox() {
        minWidth(WindowConfiguration.COLUMN);
        maxWidth(WindowConfiguration.COLUMN);
        prefWidth(WindowConfiguration.COLUMN);
        getSelectionModel().selectedItemProperty()
                .addListener((ChangeListener<Choice>) (observableValue, oldChoice, newChoice) -> {
                    selectedChoice = newChoice;
                    if (choiceBoxListener == null || newChoice == null) {
                        return;
                    }
                    choiceBoxListener.onSelectItem((Genre) newChoice.getModel());
                });
    }

    private ObservableList<Choice> generateChoices(List<Relation> relations) {
        ObservableList<Choice> choices = FXCollections.observableArrayList();
        for (Relation relation : relations) {
            Genre genre = relation.getGenre();
            choices.add(new Choice(genre, genre.getLabel()));
        }
        return choices;
    }

    public void setChoiceBoxListener(ChoiceBoxListener<Genre> choiceBoxListener) {
        this.choiceBoxListener = choiceBoxListener;
    }

    public void setItems(List<Relation> relations) {
        ObservableList<Choice> choices = generateChoices(relations);
        setItems(choices);
    }

    public void reset() {
        getSelectionModel().clearSelection();
    }

    public void select(Genre genre) {
        selectedChoice = new Choice(genre, genre.getLabel());
        getSelectionModel().select(selectedChoice);
    }

    public Genre getSelectedChoice() {
        if (selectedChoice == null) {
            return null;
        }
        return (Genre) selectedChoice.getModel();
    }

}
