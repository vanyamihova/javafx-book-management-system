package eu.vanyamihova.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Generates Relations between domain models, depending of
 * incoming data. The creation of the Relation could be
 * managed only from this generator.
 *
 * @author Vanya Mihova <vanya.mihova89@gmail.com>
 * @since 2019-05-17
 */
public final class RelationGenerator {

    public List<Relation> wrap(List list) {
        List<Relation> relations = new ArrayList<>();
        List<BaseModel> models = (List<BaseModel>) list;

        for (BaseModel model : models) {
            Relation relation = wrapModel(model);
            if (relation == null) {
                continue;
            }
            relations.add(relation);
        }

        return relations;
    }

    public Relation wrap(Book book, Author author, Genre genre) {
        return new Relation(book, author, genre);
    }

    public Relation wrap(Author author) {
        return wrapAuthor(author);
    }

    public Relation wrap(Book book) {
        return wrapBook(book);
    }

    public Relation wrap(Genre genre) {
        return wrapGenre(genre);
    }

    private Relation wrapModel(BaseModel model) {
        if (model instanceof Book) {
            return wrapBook((Book) model);
        }

        if (model instanceof Author) {
            return wrapAuthor((Author) model);
        }

        if (model instanceof Genre) {
            return wrapGenre((Genre) model);
        }
        return null;
    }

    private Relation wrapBook(Book book) {
        return new Relation(book, null, null);
    }

    private Relation wrapAuthor(Author author) {
        return new Relation(null, author, null);
    }

    private Relation wrapGenre(Genre genre) {
        return new Relation(null, null, genre);
    }

}
