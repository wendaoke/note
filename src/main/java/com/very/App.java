package com.very;
import com.very.configuration.AppConfiguration;
import com.very.view.DemoTabPane;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.kordamp.bootstrapfx.BootstrapFX;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App extends Application {
   private static final ApplicationContext context = new AnnotationConfigApplicationContext(AppConfiguration.class);
    @Override
    public void start(Stage stage) throws Exception {

        Scene scene = new Scene(new DemoTabPane());
        scene.getStylesheets().addAll(
                BootstrapFX.bootstrapFXStylesheet(),
                "org/kordamp/bootstrapfx/sampler/sampler.css",
                "org/kordamp/bootstrapfx/sampler/xml-highlighting.css");
        stage.getIcons().add(new Image(App.class.getClassLoader().getResourceAsStream("img/note.png")));

        stage.setTitle("记事本");
        stage.setScene(scene);
        stage.setWidth(1024);
        stage.show();
    }
}