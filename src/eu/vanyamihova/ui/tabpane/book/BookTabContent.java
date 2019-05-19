package eu.vanyamihova.ui.tabpane.book;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import eu.vanyamihova.model.Book;
import eu.vanyamihova.model.Relation;
import eu.vanyamihova.repository.AuthorRepository;
import eu.vanyamihova.repository.BookRepository;
import eu.vanyamihova.repository.GenreRepository;
import eu.vanyamihova.ui.DeleteButton;
import eu.vanyamihova.ui.SaveButton;
import eu.vanyamihova.ui.choicebox.AuthorChoiceBox;
import eu.vanyamihova.ui.choicebox.GenreChoiceBox;
import eu.vanyamihova.ui.table.book.BookTableView;
import eu.vanyamihova.ui.tabpane.BaseTabContent;
import eu.vanyamihova.ui.textarea.SummaryTextArea;
import eu.vanyamihova.ui.textfield.IsbnTextField;
import eu.vanyamihova.ui.textfield.PagesTextField;
import eu.vanyamihova.ui.textfield.PublishedTextField;
import eu.vanyamihova.ui.textfield.TitleTextField;

import java.sql.Date;
import java.util.List;

/**
 * @author Vanya Mihova <vanya.mihova89@gmail.com>
 * @since 2019-05-17
 */
final class BookTabContent extends BaseTabContent {

    private Book selectedBook;
    private ObservableList<Relation> data;
    private final SaveButton saveButton = new SaveButton();
    private final DeleteButton deleteButton = new DeleteButton();
    private final IsbnTextField isbnTextField = new IsbnTextField();
    private final TitleTextField titleTextField = new TitleTextField();
    private final PagesTextField pagesTextField = new PagesTextField();
    private final PublishedTextField publishedTextField = new PublishedTextField();
    private final SummaryTextArea summaryTextArea = new SummaryTextArea();
    private final BookTableView bookTableView = new BookTableView();
    private final GenreChoiceBox genreChoiceBox = new GenreChoiceBox();
    private final AuthorChoiceBox authorChoiceBox = new AuthorChoiceBox();
    private final BookRepository bookRepository = new BookRepository();
    private final AuthorRepository authorRepository = new AuthorRepository();
    private final GenreRepository genreRepository = new GenreRepository();

    BookTabContent() {
        initData();
        refresh();

        HBox firstLineEditorBox = createFirstLineEditorBox();
        HBox secondLineEditorBox = createSecondLineEditorBox();
        HBox thirdLineEditorBox = createThirdLineEditorBox();
        HBox forthLineEditorBox = createForthLineEditorBox();

        VBox box = new VBox();
        box.setFillWidth(true);
        box.getChildren().add(firstLineEditorBox);
        box.getChildren().add(secondLineEditorBox);
        box.getChildren().add(thirdLineEditorBox);
        box.getChildren().add(forthLineEditorBox);
        box.getChildren().add(bookTableView);
        getChildren().add(box);
    }

    @Override
    public void refresh() {
        refreshView();

        List<Relation> books = bookRepository.findAll();
        data = FXCollections.observableList(books);
        bookTableView.setItems(data);

        List<Relation> authors = authorRepository.findAll();
        authorChoiceBox.setItems(authors);

        List<Relation> genres = genreRepository.findAll();
        genreChoiceBox.setItems(genres);
    }

    private void initData() {
        bookTableView.setBookTableEventHandler(book -> enableEditableMode(book));
        authorChoiceBox.setChoiceBoxListener(author -> {
            selectedBook.draft()
                    .author(author)
                    .commit();
        });
    }

    private HBox createHbox() {
        HBox box = new HBox();
        box.setPadding(new Insets(10, 10, 10, 10));
        box.setSpacing(10);
        return box;
    }

    private HBox createFirstLineEditorBox() {
        HBox box = createHbox();
        box.getChildren().add(isbnTextField);
        box.getChildren().add(titleTextField);
        box.getChildren().add(publishedTextField);
        return box;
    }

    private HBox createSecondLineEditorBox() {
        HBox box = createHbox();
        box.getChildren().add(pagesTextField);
        box.getChildren().add(authorChoiceBox);
        box.getChildren().add(genreChoiceBox);
        return box;
    }

    private HBox createThirdLineEditorBox() {
        HBox box = createHbox();
        box.getChildren().add(summaryTextArea);
        return box;
    }

    private HBox createForthLineEditorBox() {
        saveButton.setOnMouseClicked(event -> addBook());
        deleteButton.setOnMouseClicked(event -> deleteBook());

        HBox box = createHbox();
        box.getChildren().add(saveButton);
        box.getChildren().add(deleteButton);
        return box;
    }

    private void addBook() {
        if (!isbnTextField.isValid() || !titleTextField.isValid() || !pagesTextField.isValid() ||
                !publishedTextField.isValid() || !summaryTextArea.isValid()) {
            // TODO: show error message!
            return;
        }
        selectedBook.draft()
                .isbn(isbnTextField.getText())
                .title(titleTextField.getText())
                .pages(Integer.valueOf(pagesTextField.getText()))
                .published(Date.valueOf(publishedTextField.getText()))
                .summary(summaryTextArea.getText())
                .genre(genreChoiceBox.getSelectedChoice())
                .commit();
        bookRepository.save(selectedBook);
        refreshData();
        refreshView();
    }

    private void deleteBook() {
        bookRepository.delete(selectedBook);
        refreshData();
        refreshView();
    }

    private void refreshData() {
        List<Relation> books = bookRepository.findAll();
        data.clear();
        data.addAll(books);
    }

    private void refreshView() {
        bookTableView.refresh();
        isbnTextField.reset();
        titleTextField.reset();
        pagesTextField.reset();
        publishedTextField.reset();
        summaryTextArea.reset();
        authorChoiceBox.reset();
        genreChoiceBox.reset();
        selectedBook = Book.builder().build();
    }

    private void enableEditableMode(Book book) {
        selectedBook = book;
        isbnTextField.setText(book.getIsbn());
        titleTextField.setText(book.getTitle());
        pagesTextField.setText(book.getPages().toString());
        publishedTextField.setText(book.getPublished().toString());
        summaryTextArea.setText(book.getSummary());
        authorChoiceBox.select(book.getAuthor());
        genreChoiceBox.select(book.getGenre());
    }
}
