package eu.vanyamihova.model;

/**
 * Represents common domain model with all domain models in it.
 * It is used for collecting and visualizing the data in the UI
 * elements.
 * Potentially it could represents relations between the models.
 *
 * @author Vanya Mihova <vanya.mihova89@gmail.com>
 * @since 2019-05-17
 */
public final class Relation {

    private Book book;
    private Author author;
    private Genre genre;

    Relation(Book book, Author author, Genre genre) {
        this.book = book;
        this.author = author;
        this.genre = genre;
    }

    public Book getBook() {
        return book;
    }

    public Author getAuthor() {
        return author;
    }

    public Genre getGenre() {
        return genre;
    }
}
