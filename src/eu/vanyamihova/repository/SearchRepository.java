package eu.vanyamihova.repository;

import eu.vanyamihova.database.DatabaseConnection;
import eu.vanyamihova.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static eu.vanyamihova.repository.AuthorRepository.*;
import static eu.vanyamihova.repository.BookRepository.*;
import static eu.vanyamihova.repository.GenreRepository.*;

/**
 * Repository for search query. It represents complicate
 * query for finding all needed reports with different
 * criteria.
 *
 * @author Vanya Mihova <vanya.mihova89@gmail.com>
 * @since 2019-05-17
 */
public class SearchRepository {

    private Connection connection = DatabaseConnection.getConnection();
    private RelationGenerator relationGenerator = new RelationGenerator();

    public List<Relation> findBy(String isbn, String title, String firstName, String lastName, String genre) {
        List<Relation> relations = new ArrayList<>();
        String sql = "select * from " + BOOK_TABLE_NAME + " b " +
                "left join " + GENRE_TABLE_NAME + " g on b." + GENRE_ID + " = g." + GENRE_ID + " " +
                "left join " + AUTHOR_TABLE_NAME + " a on b." + AUTHOR_ID + " = a." + AUTHOR_ID + " " +
                "where 1 = 1";

        if (isbn != null && !isbn.isEmpty()) {
            sql += " and b." + BOOK_ISBN + " = '" + isbn + "'";
        }

        if (title != null && !title.isEmpty()) {
            sql += " and b." + BOOK_TITLE + " like '%" + title + "%'";
        }

        if (firstName != null && !firstName.isEmpty()) {
            sql += " and a." + AUTHOR_FIRST_NAME + " like '%" + firstName + "%'";
        }

        if (lastName != null && !lastName.isEmpty()) {
            sql += " and a." + AUTHOR_LAST_NAME + " like '%" + lastName + "%'";
        }

        if (genre != null && !genre.isEmpty()) {
            sql += " and g." + GENRE_LABEL + " = '" + genre + "'";
        }

        try {
            PreparedStatement state = connection.prepareStatement(sql);
            ResultSet result = state.executeQuery();
            while (result.next()) {
                Relation relation = readResultSet(result);
                relations.add(relation);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return relations;
    }

    private Relation readResultSet(ResultSet resultSet) throws SQLException {
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

        Book book = Book.builder(resultSet.getInt(BOOK_ID))
                .isbn(resultSet.getString(BOOK_ISBN))
                .title(resultSet.getString(BOOK_TITLE))
                .summary(resultSet.getString(BOOK_SUMMARY))
                .pages(resultSet.getInt(BOOK_PAGES))
                .published(resultSet.getDate(BOOK_PUBLISHED))
                .author(author)
                .genre(genre)
                .build();

        return relationGenerator.wrap(book, author, genre);
    }
}
