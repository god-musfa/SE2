package org.hbrs.se2.project.softwaree.util;

public class Globals {
    public static String CURRENT_USER = "current_User";
    public static final String DEFAULT_PROFILE_PICTURE = "/images/profile_pictures/default.png";

    public static class Pages {
        public static final String SHOW_CARS = "show";
        public static final String ENTER_CAR = "enter";

        public static final String LOGIN_VIEW = "login";
        public static final String MAIN_VIEW = "";

        public static final String REGISTER_STUDENT = "register/student";
        public static final String REGISTER_COMPANY = "register/company";
        public static final String REGISTER = "register";

        public static final String EDIT_PROFILE = "editProfile";
        public static final String SHOW_ADDRESS = "address";

        public static final String SHOW_JOBS = "jobs";
        public static final String SHOW_JOB_DETAILS = "job";
        public static final String JOB_OFFER = "jobOffer";
        public static final String VIEW_PROFILE = "profile";
    }

    public static class PageTitles {
        public static final String PAGETITLE_STELLENANZEIGE = "Stellenanzeigenliste";
        public static final String PAGETITLE_STELLENANZEIGE_DETAILS = "Stellenangebot Details";
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
