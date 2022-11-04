package org.hbrs.se2.project.hellocar.util;

import com.vaadin.flow.component.Component;

public class Globals {
    public static String CURRENT_USER = "current_User";

    public static class Pages {
        public static final String SHOW_CARS = "show";
        public static final String ENTER_CAR = "enter";

        public static final String LOGIN_VIEW = "login";
        public static final String MAIN_VIEW = "";

        public static final String EDIT_PROFILE = "editProfile";
    }

    public static class Roles {
        public static final String ADMIN = "admin";
        public static final String USER = "user";

    }

    public static class Errors {
        public static final String NOUSERFOUND = "nouser";
        public static final String SQLERROR = "sql";
        public static final String DATABASE = "database";
    }

    public static class ScreenSizes {
        public static final String SMARTPHONE_PORTRAIT = "320px";
        public static final String SMARTPHONE_LANDSCAPE = "480px";
        public static final String TABLET_PORTRAIT = "768px";
        public static final String TABLET_LANDSCAPE = "1024px";
        public static final String WORKSTATION = "1200px";
    }

}
