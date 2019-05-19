package eu.vanyamihova.repository;

import eu.vanyamihova.database.DatabaseConnection;
import eu.vanyamihova.model.Author;
import eu.vanyamihova.model.Book;
import eu.vanyamihova.model.Genre;
import eu.vanyamihova.model.Relation;
import eu.vanyamihova.model.RelationGenerator;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static eu.vanyamihova.repository.AuthorRepository.*;
import static eu.vanyamihova.repository.GenreRepository.*;

/**
 * Repository for Book table in the database.
 * All CRUD operations are implemented here.
 *
 * @author Vanya Mihova <vanya.mihova89@gmail.com>
 * @since 2019-05-17
 */
public final class BookRepository {

    static final String BOOK_TABLE_NAME = "book";
    static final String BOOK_ID = "book_id";
    static final String BOOK_TITLE = "title";
    static final String BOOK_ISBN = "isbn";
    static final String BOOK_SUMMARY = "summary";
    static final String BOOK_PAGES = "pages";
    static final String BOOK_PUBLISHED = "published";

    private Connection connection = DatabaseConnection.getConnection();
    private RelationGenerator relationGenerator = new RelationGenerator();

    public List<Relation> findAll() {
        List<Book> books = new ArrayList<>();
        String sql = "select * from " + BOOK_TABLE_NAME + " b " +
                "left join " + GENRE_TABLE_NAME + " g on b." + GENRE_ID + " = g." + GENRE_ID + " " +
                "left join " + AUTHOR_TABLE_NAME + " a on b." + AUTHOR_ID + " = a." + AUTHOR_ID;
        try {
            PreparedStatement state = connection.prepareStatement(sql);
            ResultSet result = state.executeQuery();
            while (result.next()) {
                Book book = readResultSet(result);
                books.add(book);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return relationGenerator.wrap(books);
    }

    public void save(Book book) {
        if (!book.hasId()) {
            insert(book);
            return;
        }
        update(book);
    }

    public void delete(Book book) {
        String sql = "delete from " + BOOK_TABLE_NAME + " where " + BOOK_ID + " = " + book.getIndex();
        try {
            PreparedStatement state = connection.prepareStatement(sql);
            state.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void insert(Book book) {
        String sql = "insert into " + BOOK_TABLE_NAME + " (" + BOOK_ISBN + ", " + BOOK_TITLE + ", " + BOOK_PAGES + ", " + BOOK_PUBLISHED + ", " + BOOK_SUMMARY + ", " + AUTHOR_ID + ", " + GENRE_ID + ") " +
                "values (" +
                "'" + book.getIsbn() + "', " +
                "'" + book.getTitle() + "', " +
                "'" + book.getPages() + "', " +
                "'" + book.getPublished() + "', " +
                "'" + book.getSummary() + "', " +
                "'" + book.getAuthor().getIndex() + "', " +
                "'" + book.getGenre().getIndex() + "')";
        try {
            PreparedStatement state = connection.prepareStatement(sql);
            state.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void update(Book book) {
        String sql = "update " + BOOK_TABLE_NAME + " set " +
                BOOK_ISBN + " = '" + book.getIsbn() + "', " +
                BOOK_TITLE + " = '" + book.getTitle() + "', " +
                BOOK_PAGES + " = '" + book.getPages() + "', " +
                BOOK_PUBLISHED + " = '" + book.getPublished() + "', " +
                BOOK_SUMMARY + " = '" + book.getSummary() + "', " +
                AUTHOR_ID + " = '" + book.getAuthor().getIndex() + "', " +
                GENRE_ID + " = '" + book.getGenre().getIndex() + "' " +
                "where " + BOOK_ID + " = " + book.getIndex();
        try {
            PreparedStatement state = connection.prepareStatement(sql);
            state.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Book readResultSet(ResultSet resultSet) throws SQLException {
        if (resultSet == null) {
            return null;
        }
        Author author = Author.builder(resultSet.getInt(AUTHOR_ID))
                .firstName(resultSet.getString(AUTHOR_FIRST_NAME))
                .lastName(resultSet.getString(AUTHOR_LAST_NAME))
                .build();

        Genre genre = Genre.builder(resultSet.getInt(GENRE_ID))
                .label(resultSet.getString(GENRE_LABEL))
                .build();

        return Book.builder(resultSet.getInt(BOOK_ID))
                .isbn(resultSet.getString(BOOK_ISBN))
                .title(resultSet.getString(BOOK_TITLE))
                .summary(resultSet.getString(BOOK_SUMMARY))
                .pages(resultSet.getInt(BOOK_PAGES))
                .published(resultSet.getDate(BOOK_PUBLISHED))
                .author(author)
                .genre(genre)
                .build();
    }

}
