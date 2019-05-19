package eu.vanyamihova.ui.tabpane.genre;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import eu.vanyamihova.model.Genre;
import eu.vanyamihova.model.Relation;
import eu.vanyamihova.repository.GenreRepository;
import eu.vanyamihova.ui.DeleteButton;
import eu.vanyamihova.ui.SaveButton;
import eu.vanyamihova.ui.table.genre.GenreTableView;
import eu.vanyamihova.ui.tabpane.BaseTabContent;
import eu.vanyamihova.ui.textfield.LabelTextField;

import java.util.List;

/**
 * @author Vanya Mihova <vanya.mihova89@gmail.com>
 * @since 2019-05-18
 */
final class GenreTabContent extends BaseTabContent {

    private Genre selectedGenre;
    private GenreTableView genreTableView;
    private ObservableList<Relation> data;
    private final SaveButton saveButton = new SaveButton();
    private final DeleteButton deleteButton = new DeleteButton();
    private final LabelTextField labelTextField = new LabelTextField();
    private final GenreRepository genreRepository = new GenreRepository();

    GenreTabContent() {
        initData();
        refresh();
        HBox editorBox = createEditorBox();

        VBox box = new VBox();
        box.setFillWidth(true);
        box.getChildren().add(editorBox);
        box.getChildren().add(genreTableView);
        getChildren().add(box);
    }

    @Override
    public void refresh() {
        List<Relation> genres = genreRepository.findAll();
        data = FXCollections.observableList(genres);
        genreTableView.setItems(data);
    }

    private void initData() {
        genreTableView = new GenreTableView(genre -> enableEditableMode(genre));
    }

    private HBox createEditorBox() {
        saveButton.setOnMouseClicked(event -> addGenre());
        deleteButton.setOnMouseClicked(event -> deleteGenre());

        HBox box = new HBox();
        box.setPadding(new Insets(10, 10, 10, 10));
        box.setSpacing(10);
        box.getChildren().add(labelTextField);
        box.getChildren().add(saveButton);
        box.getChildren().add(deleteButton);
        return box;
    }

    private void addGenre() {
        if (!labelTextField.isValid()) {
            // TODO: show error message!
            return;
        }

        if (selectedGenre == null) {
            selectedGenre = Genre.builder().build();
        }

        selectedGenre.draft()
                .label(labelTextField.getText())
                .commit();
        genreRepository.save(selectedGenre);

        refreshData();
        refreshView();
    }

    private void deleteGenre() {
        if (selectedGenre == null) {
            return;
        }
        genreRepository.delete(selectedGenre);
        refreshData();
        refreshView();
    }

    private void refreshData() {
        List<Relation> genres = genreRepository.findAll();
        data.clear();
        data.addAll(genres);
    }

    private void refreshView() {
        genreTableView.refresh();
        labelTextField.reset();
        selectedGenre = null;
    }

    private void enableEditableMode(Genre genre) {
        selectedGenre = genre;
        labelTextField.setText(genre.getLabel());
    }
}
