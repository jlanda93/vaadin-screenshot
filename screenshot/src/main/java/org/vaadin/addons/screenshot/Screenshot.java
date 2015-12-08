
/*
 * Copyright (C) 2015 Elihu, LLC. All rights reserved.
 *
 * $Id$
 */

package org.vaadin.addons.screenshot;

import com.vaadin.annotations.JavaScript;
import com.vaadin.ui.AbstractComponent;
import com.vaadin.ui.Component;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.codec.binary.Base64;
import org.vaadin.addons.screenshot.client.ScreenshotClientRpc;
import org.vaadin.addons.screenshot.client.ScreenshotServerRpc;
import org.vaadin.addons.screenshot.client.ScreenshotState;

/**
 * Server-side widget for taking and providing screenshots
 */
@JavaScript({"html2canvas.js"})
public class Screenshot extends AbstractComponent {

    private static final long serialVersionUID = 1L;

    private final transient Set<ScreenshotListener> listeners = new HashSet<ScreenshotListener>();

    public Screenshot() {
        this.registerRpc(new ScreenshotServerRpc() {
            private static final long serialVersionUID = 1L;
            @Override
            public void screenshotResult(String dataURL) {
                Screenshot.this.parseAndNotify(dataURL);
            }
        });
        this.setWidth("0px");
        this.setHeight("0px");
        this.setImmediate(true);
    }

    private Screenshot(Builder builder) {
        this();
        this.getState().allowTaint = builder.allowTaint;
        this.getState().background = builder.background;
        this.getState().screenshotHeight = builder.height;
        this.getState().letterRendering = builder.letterRendering;
        this.getState().logging = builder.logging;
        this.getState().proxy = builder.proxy;
        this.getState().taintTest = builder.taintTest;
        this.getState().timeout = builder.timeout;
        this.getState().screenshotWidth = builder.width;
        this.getState().useCORS = builder.useCORS;
        this.getState().mimeType = builder.mimeType.getMimeType();
        this.getState().targetComponent = builder.targetComponent;
    }

    public void addScreenshotListener(ScreenshotListener listener) {
        synchronized (this.listeners) {
            this.listeners.add(listener);
        }
    }

    public void removeScreenshotListener(ScreenshotListener listener) {
        synchronized (this.listeners) {
            this.listeners.remove(listener);
        }
    }

    public void takeScreenshot() {
        this.getRpcProxy(ScreenshotClientRpc.class).screenshot();
    }

    private void parseAndNotify(String dataURL) {
        ScreenshotImage image = this.parse(dataURL);
        this.notifyListeners(image);
    }

    private void notifyListeners(ScreenshotImage image) {
        Set<ScreenshotListener> theListeners;
        synchronized (this.listeners) {
            theListeners = new HashSet<ScreenshotListener>(this.listeners);
        }
        for (ScreenshotListener listener : theListeners) {
            listener.screenshotComplete(image);
        }
    }

    private ScreenshotImage parse(String dataURL) {
        String base64 = dataURL.substring(dataURL.indexOf(',') + 1);
        byte[] imageData = Base64.decodeBase64(base64);
        return new ScreenshotImage(dataURL, imageData, ScreenshotMimeType.fromMimeType(this.getState().mimeType));
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    // We must override getState() to cast the state to ScreenshotState
    @Override
    protected ScreenshotState getState() {
        return (ScreenshotState)super.getState();
    }

    public boolean isAllowTaint() {
        return this.getState().allowTaint;
    }

    public String getBackground() {
        return this.getState().background;
    }

    public int getScreenshotHeight() {
        return this.getState().screenshotHeight;
    }

    public boolean isLetterRendering() {
        return this.getState().letterRendering;
    }

    public boolean isLogging() {
        return this.getState().logging;
    }

    public String getProxy() {
        return this.getState().proxy;
    }

    public boolean isTaintTest() {
        return this.getState().taintTest;
    }

    public int getTimeout() {
        return this.getState().timeout;
    }

    public int getScreenshotWidth() {
        return this.getState().screenshotWidth;
    }

    public boolean isUseCORS() {
        return this.getState().useCORS;
    }

    public void setTargetComponent(Component component) {
        getState().targetComponent = component;
    }

    public Component getTargetComponent() {
        return (Component) getState().targetComponent;
    }

    public ScreenshotMimeType getMimeType() {
        return ScreenshotMimeType.fromMimeType(this.getState().mimeType);
    }

    public static final class Builder {

        private boolean allowTaint;
        private String background = "#fff";
        private int height;
        private boolean letterRendering;
        private boolean logging;
        private String proxy;
        private boolean taintTest = true;
        private int timeout;
        private int width;
        private boolean useCORS;
        private ScreenshotMimeType mimeType = ScreenshotMimeType.PNG;
        private Component targetComponent;

        private Builder() {
        }

        public Builder withAllowTaint(boolean allowTaint) {
            this.allowTaint = allowTaint;
            return this;
        }

        public Builder withBackground(String background) {
            this.background = background;
            return this;
        }

        public Builder withHeight(int height) {
            this.height = height;
            return this;
        }

        public Builder withLetterRendering(boolean letterRendering) {
            this.letterRendering = letterRendering;
            return this;
        }

        public Builder withLogging(boolean logging) {
            this.logging = logging;
            return this;
        }

        public Builder withProxy(String proxy) {
            this.proxy = proxy;
            return this;
        }

        public Builder withTaintTest(boolean taintTest) {
            this.taintTest = taintTest;
            return this;
        }

        public Builder withTimeout(int timeout) {
            this.timeout = timeout;
            return this;
        }

        public Builder withWidth(int width) {
            this.width = width;
            return this;
        }

        public Builder withUseCORS(boolean useCORS) {
            this.useCORS = useCORS;
            return this;
        }

        public Builder withMimeType(ScreenshotMimeType mimeType) {
            this.mimeType = mimeType;
            return this;
        }

        public Builder ofTargetComponent(Component target) {
            this.targetComponent = target;
            return this;
        }

        public Screenshot build() {
            return new Screenshot(this);
        }
    }
}
