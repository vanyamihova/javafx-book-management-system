package eu.vanyamihova.repository;

import eu.vanyamihova.database.DatabaseConnection;
import eu.vanyamihova.model.Genre;
import eu.vanyamihova.model.Relation;
import eu.vanyamihova.model.RelationGenerator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Repository for Genre table in the database.
 * All CRUD operations are implemented here.
 *
 * @author Vanya Mihova <vanya.mihova89@gmail.com>
 * @since 2019-05-18
 */
public final class GenreRepository {

    static final String GENRE_TABLE_NAME = "genre";
    static final String GENRE_ID = "genre_id";
    static final String GENRE_LABEL = "label";

    private Connection connection = DatabaseConnection.getConnection();
    private RelationGenerator relationGenerator = new RelationGenerator();

    public List<Relation> findAll() {
        List<Genre> genres = new ArrayList<>();
        String sql = "select * from genre";
        try {
            PreparedStatement state = connection.prepareStatement(sql);
            ResultSet result = state.executeQuery();
            while (result.next()) {
                Genre genre = readResultSet(result);
                genres.add(genre);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return relationGenerator.wrap(genres);
    }

    public void save(Genre genre) {
        if (!genre.hasId()) {
            insert(genre);
            return;
        }
        update(genre);
    }

    public void delete(Genre genre) {
        String sql = "delete from " + GENRE_TABLE_NAME + " where " + GENRE_ID + " = " + genre.getIndex();
        try {
            PreparedStatement state = connection.prepareStatement(sql);
            state.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void insert(Genre genre) {
        String sql = "insert into " + GENRE_TABLE_NAME + " (" + GENRE_LABEL + ") values ('" + genre.getLabel() + "')";
        try {
            PreparedStatement state = connection.prepareStatement(sql);
            state.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void update(Genre genre) {
        String sql = "update " + GENRE_TABLE_NAME + " set " + GENRE_LABEL + " = '" + genre.getLabel() + "' where " + GENRE_ID + " = " + genre.getIndex();
        try {
            PreparedStatement state = connection.prepareStatement(sql);
            state.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Genre readResultSet(ResultSet resultSet) throws SQLException {
        if (resultSet == null) {
            return null;
        }
        return Genre.builder(resultSet.getInt(GENRE_ID))
                .label(resultSet.getString(GENRE_LABEL))
                .build();
    }

}
