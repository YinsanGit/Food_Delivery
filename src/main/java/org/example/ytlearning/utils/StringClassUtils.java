package org.example.ytlearning.utils;

public class StringClassUtils {
    private StringClassUtils() {

    }

    public static String getFileExtension(String fileName) {
        int index = fileName.lastIndexOf('.');
        if(index < 0){
            return null;
        }
        return fileName.substring(index+1);
    }

}
