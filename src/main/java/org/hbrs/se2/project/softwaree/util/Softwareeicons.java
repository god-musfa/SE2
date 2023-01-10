package org.hbrs.se2.project.softwaree.util;

import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.icon.IconFactory;
import java.util.Locale;

/**@author dheil2s
 *
 * IconsetManagement to allow custom icons in our application.
 */
@JsModule("./icons/softwareeicons.js")
public enum Softwareeicons implements IconFactory {
    INSTAGRAM, LINKEDIN;

    public Icon create() {
        return new Icon(this.name().toLowerCase(Locale.ENGLISH).replace('_', '-').replaceAll("^-", ""));
    }

    public static final class Icon extends com.vaadin.flow.component.icon.Icon {
        Icon(String icon) {
            super("softwareeicons", icon);
        }
    }


}