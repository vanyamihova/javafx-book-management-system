package eu.vanyamihova.repository;

import eu.vanyamihova.database.DatabaseConnection;
import eu.vanyamihova.model.Author;
import eu.vanyamihova.model.Relation;
import eu.vanyamihova.model.RelationGenerator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Repository for Author table in the database.
 * All CRUD operations are implemented here.
 *
 * @author Vanya Mihova <vanya.mihova89@gmail.com>
 * @since 2019-05-16
 */
public final class AuthorRepository {

    static final String AUTHOR_TABLE_NAME = "author";
    static final String AUTHOR_ID = "author_id";
    static final String AUTHOR_FIRST_NAME = "first_name";
    static final String AUTHOR_LAST_NAME = "last_name";

    private Connection connection = DatabaseConnection.getConnection();
    private RelationGenerator relationGenerator = new RelationGenerator();

    public List<Relation> findAll() {
        List<Author> authors = new ArrayList<>();
        String sql = "select * from " + AUTHOR_TABLE_NAME;
        try {
            PreparedStatement state = connection.prepareStatement(sql);
            ResultSet result = state.executeQuery();
            while (result.next()) {
                Author author = readResultSet(result);
                authors.add(author);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return relationGenerator.wrap(authors);
    }

    public void save(Author author) {
        if (!author.hasId()) {
            insert(author);
            return;
        }
        update(author);
    }

    public void delete(Author author) {
        String sql = "delete from " + AUTHOR_TABLE_NAME + " where " + AUTHOR_ID + " = " + author.getIndex();
        try {
            PreparedStatement state = connection.prepareStatement(sql);
            state.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void insert(Author author) {
        String sql = "insert into " + AUTHOR_TABLE_NAME + " (" + AUTHOR_FIRST_NAME + ", " + AUTHOR_LAST_NAME + ") values ('" + author.getFirstName() + "', '" + author.getLastName() + "')";
        try {
            PreparedStatement state = connection.prepareStatement(sql);
            state.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void update(Author author) {
        String sql = "update " + AUTHOR_TABLE_NAME + " set " +
                AUTHOR_FIRST_NAME + " = '" + author.getFirstName() + "', " +
                AUTHOR_LAST_NAME + " = '" + author.getLastName() + "' " +
                "where " + AUTHOR_ID + " = " + author.getIndex();
        try {
            PreparedStatement state = connection.prepareStatement(sql);
            state.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Author readResultSet(ResultSet resultSet) throws SQLException {
        if (resultSet == null) {
            return null;
        }
        return Author.builder((Integer) resultSet.getObject(AUTHOR_ID))
                .firstName((String) resultSet.getObject(AUTHOR_FIRST_NAME))
                .lastName((String) resultSet.getObject(AUTHOR_LAST_NAME))
                .build();
    }

}
