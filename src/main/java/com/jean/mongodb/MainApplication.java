package com.jean.mongodb;

import com.jean.mongodb.utils.PropertiesUtil;
import javafx.application.Preloader;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

@SpringBootApplication
public class MainApplication extends AbstractJavaFxApplicationSupport {

    @Autowired
    private Environment environment;


    @Override
    public void start(Stage stage) throws Exception {
        notifyPreloader(new Preloader.StateChangeNotification(Preloader.StateChangeNotification.Type.BEFORE_START));
        FXMLLoader loader = new FXMLLoader();
        loader.setControllerFactory(param -> applicationContext.getBean(param));
        Parent root = loader.load(getClass().getResourceAsStream("/fxml/Scene.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/Styles.css");
        String name = environment.getProperty("spring.application.name");
        String version = environment.getProperty("spring.application.version");
        stage.setTitle(name + " " + version);
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/image/mongodb-logo.png")));
        stage.setScene(scene);
        stage.show();
        stage.setOnCloseRequest(event -> {
            Dialog<ButtonType> dialog = new Dialog<>();
            dialog.setContentText("\r\n\r\n是否退出？\r\n\r\n");
            dialog.setTitle("退出提示");
            dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
            dialog.showAndWait().ifPresent(res -> {
                if (res != ButtonType.OK) {
                    event.consume();
                }
            });
        });
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launchApp(MainApplication.class, args);
    }

}
