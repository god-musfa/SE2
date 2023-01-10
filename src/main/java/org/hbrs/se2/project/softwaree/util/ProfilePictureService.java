package org.hbrs.se2.project.softwaree.util;


import org.hbrs.se2.project.softwaree.entities.User;
import org.hbrs.se2.project.softwaree.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Base64;
import java.util.HashSet;
import java.util.Optional;

/** Service to support profile picture operations **/
public class ProfilePictureService {
    @Autowired
    UserRepository userRepo;

    private static String FILENAME_REGEX = "(^\\S{1,255})\\.(jpg)|(png)|(bmp)$";
    private static int MAX_FILENAME_LEN = 512;
    public static int MAX_FILESIZE = 1024 * 1024;

    private static final String[] ALLOWED_MIMETYPES = {
            "*.jpg",
            "image/jpeg",
            "*.png",
            "image/png",
            "*.bmp",
            "image/bmp"
    };

    /** Check a picture for guideline conformity **/
    public static boolean checkPicture(String filename, Long fileSize, String mimeType) {
        // Check filename:
        if (!(filename.toLowerCase().matches(FILENAME_REGEX) && filename.length() < MAX_FILENAME_LEN)) {
            return false;
        }

        // Check MIME-type:
        HashSet<String> allowed_mimetypes = new HashSet<>();
        allowed_mimetypes.add("");

        if (Arrays.asList(ALLOWED_MIMETYPES).stream()
                .noneMatch(mimeType::equals)) {
            return false;
        }

        // Check fileSize
        if (fileSize > MAX_FILESIZE || fileSize < 0) {
            return false;
        }

        return true;
    }

    /** Convert byte array to base64 string **/
    public static String toBase64(byte[] inputBytes) {
        return Base64.getEncoder().encodeToString(inputBytes);
    }


}
