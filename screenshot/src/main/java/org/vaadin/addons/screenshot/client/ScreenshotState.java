
/*
 * Copyright (C) 2015 Elihu, LLC. All rights reserved.
 *
 * $Id$
 */

package org.vaadin.addons.screenshot.client;

import com.vaadin.shared.AbstractComponentState;
import com.vaadin.shared.Connector;

public class ScreenshotState extends AbstractComponentState {

    private static final long serialVersionUID = 1L;

    /**
     * Whether to allow cross-origin images to taint the canvas
     */
    public boolean allowTaint;

    /**
     * Canvas background color, if none is specified in DOM. Set undefined for transparent
     */
    public String background = "#fff";

    /**
     * Define the screenshotHeight of the canvas in pixels. If null, renders with full screenshotHeight of the window.
      */
    public int screenshotHeight;

    /**
     * Whether to render each letter separately. Necessary if letter-spacing is used.
     */
    public boolean letterRendering;

    /**
     * Whether to log events in the console.
     */
    public boolean logging;

    /**
     * Url to the proxy which is to be used for loading cross-origin images. If left empty, cross-origin images won't be loaded.
     */
    public String proxy;

    /**
     * Whether to test each image if it taints the canvas before drawing them
     */
    public boolean taintTest = true;

    /**
     * Timeout for loading images, in milliseconds. Setting it to 0 will result in no timeout.
     */
    public int timeout;

    /**
     * Define the screenshotWidth of the canvas in pixels. If null, renders with full screenshotWidth of the window.
     */
    public int screenshotWidth;

    /**
     * Whether to attempt to load cross-origin images as CORS served, before reverting back to proxy
     */
    public boolean useCORS;

    /**
     * Mime type of image data URL
     */
    public String mimeType = "image/png";

    /**
     * Take the screenshot of an specific component insted the Browser's document
     */
    public Connector targetComponent;

}
