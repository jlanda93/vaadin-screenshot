
/*
 * Copyright (C) 2015 Elihu, LLC. All rights reserved.
 *
 * $Id$
 */

package org.vaadin.addons.screenshot;

public enum ScreenshotMimeType {
    PNG("image/png"), JPEG("image/jpeg");

    private final String mimeType;

    ScreenshotMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    public String getMimeType() {
        return this.mimeType;
    }

    public static ScreenshotMimeType fromMimeType(String mimeType) {
        for (ScreenshotMimeType type : values()) {
            if (type.getMimeType().equals(mimeType)) {
                return type;
            }
        }
        return null;
    }
}
