package eu.vanyamihova.ui.tabpane.author;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import eu.vanyamihova.model.Author;
import eu.vanyamihova.model.Relation;
import eu.vanyamihova.repository.AuthorRepository;
import eu.vanyamihova.ui.DeleteButton;
import eu.vanyamihova.ui.SaveButton;
import eu.vanyamihova.ui.table.author.AuthorTableView;
import eu.vanyamihova.ui.tabpane.BaseTabContent;
import eu.vanyamihova.ui.textfield.FirstNameTextField;
import eu.vanyamihova.ui.textfield.LastNameTextField;

import java.util.List;

/**
 * @author Vanya Mihova <vanya.mihova89@gmail.com>
 * @since 2019-05-16
 */
final class AuthorTabContent extends BaseTabContent {

    private Author selectedAuthor;
    private AuthorTableView authorTableView;
    private ObservableList<Relation> data;
    private final SaveButton saveButton = new SaveButton();
    private final DeleteButton deleteButton = new DeleteButton();
    private final FirstNameTextField firstNameTextField = new FirstNameTextField();
    private final LastNameTextField lastNameTextField = new LastNameTextField();
    private final AuthorRepository authorRepository = new AuthorRepository();

    AuthorTabContent() {
        initData();
        refresh();
        HBox editorBox = createEditorBox();

        VBox box = new VBox();
        box.setFillWidth(true);
        box.getChildren().add(editorBox);
        box.getChildren().add(authorTableView);
        getChildren().add(box);
    }

    @Override
    public void refresh() {
        List<Relation> authors = authorRepository.findAll();
        data = FXCollections.observableList(authors);
        authorTableView.setItems(data);
    }

    private void initData() {
        authorTableView = new AuthorTableView(author -> enableEditableMode(author));
    }

    private HBox createEditorBox() {
        saveButton.setOnMouseClicked(event -> addAuthor());
        deleteButton.setOnMouseClicked(event -> deleteAuthor());

        HBox box = new HBox();
        box.setPadding(new Insets(10, 10, 10, 10));
        box.setSpacing(10);
        box.getChildren().add(firstNameTextField);
        box.getChildren().add(lastNameTextField);
        box.getChildren().add(saveButton);
        box.getChildren().add(deleteButton);
        return box;
    }

    private void addAuthor() {
        if (!firstNameTextField.isValid() || !lastNameTextField.isValid()) {
            // TODO: show error message!
            return;
        }

        if (selectedAuthor == null) {
            selectedAuthor = Author.builder().build();
        }

        selectedAuthor.draft()
                .firstName(firstNameTextField.getText())
                .lastName(lastNameTextField.getText())
                .commit();
        authorRepository.save(selectedAuthor);

        refreshData();
        refreshView();
    }

    private void deleteAuthor() {
        authorRepository.delete(selectedAuthor);
        refreshData();
        refreshView();
    }

    private void refreshData() {
        List<Relation> authors = authorRepository.findAll();
        data.clear();
        data.addAll(authors);
    }

    private void refreshView() {
        authorTableView.refresh();
        firstNameTextField.reset();
        lastNameTextField.reset();
        selectedAuthor = null;
    }

    private void enableEditableMode(Author author) {
        selectedAuthor = author;
        firstNameTextField.setText(author.getFirstName());
        lastNameTextField.setText(author.getLastName());
    }
}
