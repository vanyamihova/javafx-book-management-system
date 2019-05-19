package eu.vanyamihova.launch;

/**
 * Scene Wrapper to configure dimensions for the window.
 *
 * @author Vanya Mihova <vanya.mihova89@gmail.com>
 * @since 2019-05-16
 */
final class Scene extends javafx.scene.Scene {

    Scene() {
        super(new StackPane(), WindowConfiguration.WIDTH, WindowConfiguration.HEIGHT);
    }

}
