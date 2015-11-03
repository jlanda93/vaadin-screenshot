package org.vaadin.addons.screenshot.demo;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.ExternalResource;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Image;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

import javax.servlet.annotation.WebServlet;

import org.vaadin.addons.screenshot.Screenshot;
import org.vaadin.addons.screenshot.ScreenshotImage;
import org.vaadin.addons.screenshot.ScreenshotListener;

@Theme("demo")
@Title("Html2Canvas Screenshot Demo")
@SuppressWarnings("serial")
public class DemoUI extends UI
{

    @WebServlet(value = "/*", asyncSupported = true)
    @VaadinServletConfiguration(productionMode = false, ui = DemoUI.class, widgetset = "org.vaadin.addons.screenshot.demo.DemoWidgetSet")
    public static class Servlet extends VaadinServlet {
    }

    @Override
    protected void init(VaadinRequest request) {

        // Initialize our new UI component
        final Screenshot component = new Screenshot();
        component.addScreenshotListener(new ScreenshotListener() {
            public void screenshotComplete(ScreenshotImage image) {
                showImageInWindow(image);
            }
        });

        // Show it in the middle of the screen
        final VerticalLayout layout = new VerticalLayout();
        layout.setStyleName("demoContentLayout");
        layout.setSizeFull();
        layout.addComponent(component);

        final Button button = new Button("Take Screenshot", new Button.ClickListener() {
            public void buttonClick(Button.ClickEvent event) {
                component.takeScreenshot();
            }
        });
        layout.addComponent(button);
        layout.setComponentAlignment(button, Alignment.TOP_CENTER);
        setContent(layout);

    }

    private void showImageInWindow(ScreenshotImage image) {
        Window window = new Window("Here's your screenshot", new Image(null, new ExternalResource(image.getDataURL())));
        window.setWidth("80%");
        window.setHeight("80%");
        addWindow(window);
    }

}
