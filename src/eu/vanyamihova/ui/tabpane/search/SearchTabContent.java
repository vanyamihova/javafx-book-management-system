package eu.vanyamihova.ui.tabpane.search;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import eu.vanyamihova.model.Genre;
import eu.vanyamihova.model.Relation;
import eu.vanyamihova.repository.GenreRepository;
import eu.vanyamihova.repository.SearchRepository;
import eu.vanyamihova.ui.SearchButton;
import eu.vanyamihova.ui.choicebox.GenreChoiceBox;
import eu.vanyamihova.ui.table.search.ResultTableView;
import eu.vanyamihova.ui.tabpane.BaseTabContent;
import eu.vanyamihova.ui.textfield.FirstNameTextField;
import eu.vanyamihova.ui.textfield.IsbnTextField;
import eu.vanyamihova.ui.textfield.LastNameTextField;
import eu.vanyamihova.ui.textfield.TitleTextField;

import java.util.List;

/**
 * @author Vanya Mihova <vanya.mihova89@gmail.com>
 * @since 2019-05-17
 */
final class SearchTabContent extends BaseTabContent {

    private ResultTableView resultTableView;
    private ObservableList<Relation> data;
    private final IsbnTextField isbnTextField = new IsbnTextField();
    private final TitleTextField titleTextField = new TitleTextField();
    private final FirstNameTextField firstNameTextField = new FirstNameTextField();
    private final LastNameTextField lastNameTextField = new LastNameTextField();
    private final SearchButton searchButton = new SearchButton();
    private final GenreChoiceBox genreChoiceBox = new GenreChoiceBox();
    private final SearchRepository searchRepository = new SearchRepository();
    private final GenreRepository genreRepository = new GenreRepository();

    SearchTabContent() {
        initData();
        refresh();

        HBox bookLine = createBookLine();
        HBox authorLine = createAuthorLine();
        HBox genreLine = createGenreLine();
        HBox searchLine = createSearchLine();
        HBox resultsLabelLine = createResultsLabelLine();

        VBox box = new VBox();
        box.setFillWidth(true);
        box.getChildren().add(bookLine);
        box.getChildren().add(authorLine);
        box.getChildren().add(genreLine);
        box.getChildren().add(searchLine);
        box.getChildren().add(resultsLabelLine);
        box.getChildren().add(resultTableView);
        getChildren().add(box);
    }

    @Override
    public void refresh() {
        List<Relation> genres = genreRepository.findAll();
        genreChoiceBox.setItems(genres);
    }

    private void initData() {
        data = FXCollections.observableArrayList();
        resultTableView = new ResultTableView(data);
    }

    private HBox createHbox() {
        HBox box = new HBox();
        box.setPadding(new Insets(10, 10, 10, 10));
        box.setSpacing(10);
        return box;
    }

    private HBox createBookLine() {
        HBox box = createHbox();
        box.getChildren().add(new Label("Book:"));
        box.getChildren().add(isbnTextField);
        box.getChildren().add(titleTextField);
        return box;
    }

    private HBox createAuthorLine() {
        HBox box = createHbox();
        box.getChildren().add(new Label("Author:"));
        box.getChildren().add(firstNameTextField);
        box.getChildren().add(lastNameTextField);
        return box;
    }

    private HBox createGenreLine() {
        HBox box = createHbox();
        box.getChildren().add(new Label("Genre:"));
        box.getChildren().add(genreChoiceBox);
        return box;
    }

    private HBox createSearchLine() {
        searchButton.setOnMouseClicked(event -> searchResults());

        HBox box = createHbox();
        box.getChildren().add(searchButton);
        return box;
    }

    private HBox createResultsLabelLine() {
        HBox box = createHbox();
        box.getChildren().add(new Label("Results:"));
        return box;
    }

    private void searchResults() {
        Genre genre = genreChoiceBox.getSelectedChoice();
        String genreLabel = (genre == null) ? null : genre.getLabel();

        List<Relation> results = searchRepository.findBy(
                isbnTextField.getText(), titleTextField.getText(), firstNameTextField.getText(),
                lastNameTextField.getText(), genreLabel);
        data.clear();
        data.addAll(results);
        refreshView();
    }

    private void refreshView() {
        resultTableView.refresh();
        isbnTextField.reset();
        titleTextField.reset();
        firstNameTextField.reset();
        lastNameTextField.reset();
    }

}
