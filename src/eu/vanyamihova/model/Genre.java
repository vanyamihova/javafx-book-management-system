package eu.vanyamihova.model;

import java.util.Objects;

/**
 * Represents Genre model with Builder pattern.
 *
 * @author Vanya Mihova <vanya.mihova89@gmail.com>
 * @since 2019-05-18
 */
public final class Genre extends BaseModel {

    public static Genre.Builder builder() {
        return new Genre.Builder(0);
    }

    public static Genre.Builder builder(Integer index) {
        return new Genre.Builder(index);
    }

    private String label;

    private Genre(Genre.Builder builder) {
        super(builder);
        this.commit(builder);
    }

    public void reset() {
        super.reset();
        this.label = null;
    }

    public String getLabel() {
        return label;
    }

    public Genre.Builder draft() {
        return new Genre.Builder(this);
    }

    private void commit(Genre.Builder builder) {
        this.label = builder.label;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Genre genre = (Genre) o;
        return Objects.equals(label, genre.label);
    }

    @Override
    public int hashCode() {
        return Objects.hash(label);
    }

    public static class Builder extends BaseBuilder implements Buildable<Genre> {

        private Genre genre;
        private String label;

        Builder(Integer index) {
            super(index);
        }

        Builder(Genre genre) {
            super(genre.getIndex());
            this.genre = genre;
            this.label = genre.label;
        }

        public Genre.Builder label(String label) {
            this.label = label;
            return this;
        }

        @Override
        public Genre build() {
            return new Genre(this);
        }

        public void commit() {
            genre.commit(this);
        }

    }
}
