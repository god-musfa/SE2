package org.hbrs.se2.project.softwaree.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Random;


public class ProfilePictureService {

    private static String FILENAME_REGEX = "";
    private static int MAX_FILENAME_LEN = 512;
    private static int MAX_FILESIZE = 1024 * 1024;
    private static String PROFILE_PICTURE_PATH = "src/main/resources/META-INF/resources/images/profile_pictures/";
    private static String[] ALLOWED_MIMETYPES = {
            "jpg",
            "jpeg",
            "png",
            "bmp"
    };


    public static boolean checkPicture(String filename, Integer fileSize, String mimeType) {
        // Check filename:
        if (!(filename.toLowerCase().matches(FILENAME_REGEX) && filename.length() < MAX_FILENAME_LEN)) {
            return false;
        }

        // Check MIME-type:
        if ( !(Arrays.asList(ALLOWED_MIMETYPES).stream()
                .map(String::toLowerCase)
                .anyMatch(mimeType::equals)) ) {
            return false;
        }

        // Check fileSize
        if (fileSize > MAX_FILESIZE || fileSize < 0) {
            return false;
        }

        return true;
    }

    public static String hashName(String name) {
        String rawString = String.format("%s.%d", name, getRandomValue());
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(
                    rawString.getBytes(StandardCharsets.UTF_8)
            );
            return toHexString(hashBytes);
        } catch (NoSuchAlgorithmException ex) {
            return "";
        }
    }

    public static boolean saveImage(String filename, InputStream fileStream) {
        try {
            byte [] fileBuffer = fileStream.readAllBytes();
            File targetFile = new File(PROFILE_PICTURE_PATH + filename);
            FileOutputStream outputStream = new FileOutputStream(targetFile);
            outputStream.write(fileBuffer);
            outputStream.close();
            return true;
        } catch (IOException ioException) {
            return false;
        }
    }

    public static boolean deleteImage(String filename) {
        try {
            File targetFile = new File(PROFILE_PICTURE_PATH + filename);
            targetFile.delete();
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    private static int getRandomValue() {
        return new Random().nextInt();
    }

    private static String toHexString(byte[] inputBytes) {
        BigInteger inputInt = new BigInteger(1, inputBytes);
        StringBuilder hexString = new StringBuilder(inputInt.toString(16));
        while (hexString.length() < 64) {
            hexString.insert(0, '0');
        }
        return hexString.toString();
    }

}
