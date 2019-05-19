package eu.vanyamihova.launch;

/**
 * {@code Stage} Wrapper to load custom {@link Scene}.
 *
 * @author Vanya Mihova <vanya.mihova89@gmail.com>
 * @since 2019-05-16
 */
public final class Stage extends javafx.stage.Stage {

    public Stage() {
        setScene(new Scene());
    }

}
