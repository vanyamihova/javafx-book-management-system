package eu.vanyamihova.model;

import java.sql.Date;
import java.util.Objects;

/**
 * Represents Book model with Builder pattern.
 *
 * @author Vanya Mihova <vanya.mihova89@gmail.com>
 * @since 2019-05-17
 */
public final class Book extends BaseModel {

    public static Book.Builder builder() {
        return new Builder(0);
    }

    public static Book.Builder builder(Integer index) {
        return new Builder(index);
    }

    private String isbn;
    private String title;
    private Integer pages;
    private Date published;
    private String summary;
    private Author author;
    private Genre genre;

    private Book(Builder builder) {
        super(builder);
        this.commit(builder);
    }

    public String getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    public Integer getPages() {
        return pages;
    }

    public Date getPublished() {
        return published;
    }

    public String getSummary() {
        return summary;
    }

    public Author getAuthor() {
        return author;
    }

    public Genre getGenre() {
        return genre;
    }

    public Builder draft() {
        return new Builder(this);
    }

    private void commit(Builder builder) {
        this.isbn = builder.isbn;
        this.title = builder.title;
        this.pages = builder.pages;
        this.summary = builder.summary;
        this.published = builder.published;
        this.author = builder.author;
        this.genre = builder.genre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(isbn, book.isbn) &&
                Objects.equals(title, book.title) &&
                Objects.equals(pages, book.pages) &&
                Objects.equals(published, book.published) &&
                Objects.equals(summary, book.summary) &&
                Objects.equals(author, book.author) &&
                Objects.equals(genre, book.genre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(isbn, title, pages, published, summary, author, genre);
    }

    public static class Builder extends BaseBuilder implements Buildable<Book> {

        private Book book;
        private String isbn;
        private String title;
        private Integer pages;
        private Date published;
        private String summary;
        private Author author;
        private Genre genre;

        Builder(Integer index) {
            super(index);
        }

        Builder(Book book) {
            super(book.getIndex());
            this.book = book;
            this.isbn = book.isbn;
            this.title = book.title;
            this.pages = book.pages;
            this.summary = book.summary;
            this.published = book.published;
            this.author = book.author;
            this.genre = book.genre;
        }

        public Builder isbn(String isbn) {
            this.isbn = isbn;
            return this;
        }

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder pages(Integer pages) {
            this.pages = pages;
            return this;
        }

        public Builder summary(String summary) {
            this.summary = summary;
            return this;
        }

        public Builder published(Date published) {
            this.published = published;
            return this;
        }

        public Builder author(Author author) {
            this.author = author;
            return this;
        }

        public Builder genre(Genre genre) {
            this.genre = genre;
            return this;
        }

        @Override
        public Book build() {
            return new Book(this);
        }

        public void commit() {
            book.commit(this);
        }

    }
}
