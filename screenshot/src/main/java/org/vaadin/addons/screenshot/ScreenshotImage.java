
/*
 * Copyright (C) 2015 Elihu, LLC. All rights reserved.
 *
 * $Id$
 */

package org.vaadin.addons.screenshot;

public class ScreenshotImage {

    private final String dataURL;
    private final byte[] imageData;
    private final ScreenshotMimeType mimeType;

    public ScreenshotImage(String dataURL, byte[] imageData, ScreenshotMimeType mimeType) {
        this.dataURL = dataURL;
        this.imageData = imageData;
        this.mimeType = mimeType;
    }

    public String getDataURL() {
        return this.dataURL;
    }

    public byte[] getImageData() {
        return this.imageData;
    }

    public ScreenshotMimeType getMimeType() {
        return this.mimeType;
    }
}
