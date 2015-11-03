
/*
 * Copyright (C) 2015 Elihu, LLC. All rights reserved.
 *
 * $Id$
 */

package org.vaadin.addons.screenshot.client;

import com.vaadin.shared.communication.ClientRpc;

public interface ScreenshotClientRpc extends ClientRpc {

    void screenshot();

}
