
/*
 * Copyright (C) 2015 Elihu, LLC. All rights reserved.
 *
 * $Id$
 */

package org.vaadin.addons.screenshot.client;

import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.Widget;

public class ScreenshotWidget extends Widget {

    public ScreenshotWidget() {
        Element element = DOM.createDiv();
        setElement(element);
    }

}
