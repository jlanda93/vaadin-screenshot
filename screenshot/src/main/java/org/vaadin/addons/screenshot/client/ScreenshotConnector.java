
/*
 * Copyright (C) 2015 Elihu, LLC. All rights reserved.
 *
 * $Id$
 */

package org.vaadin.addons.screenshot.client;

import com.vaadin.client.communication.RpcProxy;
import com.vaadin.client.communication.StateChangeEvent;
import com.vaadin.client.ui.AbstractComponentConnector;
import com.vaadin.shared.ui.Connect;

import org.vaadin.addons.screenshot.Screenshot;

@Connect(Screenshot.class)
public class ScreenshotConnector extends AbstractComponentConnector {

    private static final long serialVersionUID = -4263494838106009131L;

    private final ScreenshotServerRpc rpc = RpcProxy.create(ScreenshotServerRpc.class, this);

    public ScreenshotConnector() {
        this.registerRpc(ScreenshotClientRpc.class, new ScreenshotClientRpc() {
            @Override
            public void screenshot() {
                ScreenshotConnector.this.takeScreenshot(ScreenshotConnector.this, ScreenshotConnector.this.getState());
            }
        });
    }

    @Override
    public ScreenshotWidget getWidget() {
        return (ScreenshotWidget)super.getWidget();
    }

    @Override
    public ScreenshotState getState() {
        return (ScreenshotState)super.getState();
    }

    @Override
    public void onStateChanged(StateChangeEvent stateChangeEvent) {
        super.onStateChanged(stateChangeEvent);
    }

    private final native void takeScreenshot(ScreenshotConnector widget, ScreenshotState state) /*-{
        try {
            var options = {
                allowTaint: state.allowTaint,
                letterRendering: state.letterRendering,
                logging: state.logging,
                taintTest: state.taintTest,
                useCORS: state.useCORS,
                onrendered: function(canvas) {
                    var dataURL = canvas.toDataURL(state.mimeType);
                    widget.@org.vaadin.addons.screenshot.client.ScreenshotConnector::setResult(Ljava/lang/String;)(dataURL);
                }
            };
            if (state.background != null) {
                options.background = state.background;
            }
            if (state.proxy != null) {
                options.proxy = state.proxy;
            }
            if (state.screenshotHeight > 0) {
                options.height = state.screenshotHeight;
            }
            if (state.screenshotWidth > 0) {
                options.width = state.screenshotWidth;
            }
            if (state.timeout> 0) {
                options.timeout = state.timeout;
            }
            $wnd.html2canvas($wnd.document.body, options);
        } catch (e) {
            console.log(e);
        }
    }-*/;

    private void setResult(String dataURL) {
        this.rpc.screenshotResult(dataURL);
    }
}
