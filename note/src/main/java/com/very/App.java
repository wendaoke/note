package com.very;
import com.very.view.DemoTabPane;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.kordamp.bootstrapfx.BootstrapFX;

public class App extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Scene scene = new Scene(new DemoTabPane());

        scene.getStylesheets().addAll(
                BootstrapFX.bootstrapFXStylesheet(),
                "org/kordamp/bootstrapfx/sampler/sampler.css",
                "org/kordamp/bootstrapfx/sampler/xml-highlighting.css");

        stage.setTitle("BootstrapFX Sampler");
        stage.setScene(scene);
        stage.setWidth(1024);
        stage.show();
    }
}