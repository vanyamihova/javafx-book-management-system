package eu.vanyamihova.model;

/**
 * Marks a {@code Builder} as buildable.
 *
 * @author Vanya Mihova <vanya.mihova89@gmail.com>
 * @since 2019-05-17
 */
public interface Buildable<T> {

    T build();

}
