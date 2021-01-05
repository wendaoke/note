/*
 * SPDX-License-Identifier: MIT
 *
 * Copyright (c) 2015-2020 Andres Almiray.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.very.view;

import com.very.App;
import com.very.util.XMLEditor;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.StackPane;
import org.apache.commons.io.IOUtils;

import java.net.URL;

public class DemoTabPane extends StackPane {
    public DemoTabPane() throws Exception {
        URL location = App.class.getClassLoader().getResource("fxml/sampler.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(location);
        TabPane tabPane = fxmlLoader.load();

        tabPane.getTabs().add(new DemoTab("Buttons", "fxml/buttons.fxml"));
        tabPane.getTabs().add(new DemoTab("Labels", "fxml/labels.fxml"));
        tabPane.getTabs().add(new DemoTab("Alerts", "fxml/alerts.fxml"));
        tabPane.getTabs().add(new DemoTab("Panels", "fxml/panels.fxml"));
        tabPane.getTabs().add(new DemoTab("Headings", "fxml/text.fxml"));
        tabPane.getTabs().add(new DemoTab("Progress Bars", "fxml/progressbars.fxml"));
        tabPane.getTabs().add(new DemoTab("Tooltips ", "fxml/tooltips.fxml"));
        tabPane.getTabs().add(new DemoTab("Text ", "fxml/text2.fxml"));
        tabPane.getTabs().add(new DemoTab("Paragraph ", "fxml/paragraph.fxml"));
        tabPane.getTabs().add(new DemoTab("Button Groups ", "fxml/button_groups.fxml"));
        tabPane.getTabs().add(new DemoTab("SplitMenuButtons", "fxml/split_menu_buttons.fxml"));

        getChildren().add(tabPane);
    }

    private static class DemoTab extends Tab {
        private DemoTab(String title, String sourceFile) throws Exception {
            super(title);
            setClosable(true);

            TabPane content = new TabPane();
            setContent(content);
            content.setSide(Side.BOTTOM);

            Tab widgets = new Tab("Widgets");
            widgets.setClosable(false);
            URL location = App.class.getClassLoader().getResource(sourceFile);
            FXMLLoader fxmlLoader = new FXMLLoader(location);
            Node node = fxmlLoader.load();
            widgets.setContent(node);

            Tab source = new Tab("Source");
            source.setClosable(false);
            XMLEditor editor = new XMLEditor();
            editor.setEditable(false);

            String text = IOUtils.toString(App.class.getClassLoader().getResourceAsStream(sourceFile));
            editor.setText(text);
            source.setContent(editor);

            content.getTabs().addAll(widgets, source);
        }
    }
}
