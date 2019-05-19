package eu.vanyamihova.model;

import java.util.Objects;

/**
 * Represents Author model with Builder pattern.
 *
 * @author Vanya Mihova <vanya.mihova89@gmail.com>
 * @since 2019-05-16
 */
public final class Author extends BaseModel {

    public static Author.Builder builder() {
        return new Builder(0);
    }

    public static Author.Builder builder(Integer index) {
        return new Builder(index);
    }

    private String firstName;
    private String lastName;

    private Author(Author.Builder builder) {
        super(builder);
        this.commit(builder);
    }

    public void reset() {
        super.reset();
        this.firstName = null;
        this.lastName = null;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public Builder draft() {
        return new Builder(this);
    }

    private void commit(Builder builder) {
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Author author = (Author) o;
        return firstName.equals(author.firstName) &&
                lastName.equals(author.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName);
    }

    public static class Builder extends BaseBuilder implements Buildable<Author> {

        private Author author;
        private String firstName;
        private String lastName;

        Builder(Integer index) {
            super(index);
        }

        Builder(Author author) {
            super(author.getIndex());
            this.author = author;
            this.firstName = author.firstName;
            this.lastName = author.lastName;
        }

        public Builder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        @Override
        public Author build() {
            return new Author(this);
        }

        public void commit() {
            author.commit(this);
        }

    }
}
