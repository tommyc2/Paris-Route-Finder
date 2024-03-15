package com.example.parisroutefinder.utils;

import javafx.scene.image.Image;

import java.util.List;

public class Utilities {

    public static double toTwoDecimalPlaces(double number) {
        return (int) (number * 100) / 100.0;
    }

    public static String getImageDimensions(Image image){
        double width = image.getWidth();
        double height = image.getHeight();
        return "Width: "+width + ", Height: "+height;
    }

    /**
     * This method returns Y if the booleanToConvert value is true. Returns N otherwise.
     *
     * @param booleanToConvert The boolean value that will be used to determine Y/N
     * @return Returns Y if the booleanToConvert value is true. Returns N otherwise.
     */
    public static char booleanToYN(boolean booleanToConvert) {
        return booleanToConvert ? 'Y' : 'N';
    }

    /**
     * This method returns true if the charToConvert value is Y or y. Returns false in all other cases.
     *
     * @param charToConvert The char value that will be used to determine true/false.
     * @return Returns true if the charToConvert value is Y or y. Returns false otherwise.
     */
    public static boolean YNtoBoolean(char charToConvert) {
        return ((charToConvert == 'y') || (charToConvert == 'Y'));
    }


    /**
     * This method returns true if the numberToCheck is between min and max (both inclusive)
     *
     * @param numberToCheck The number whose range is being checked.
     * @param min The minimum range number to check against (inclusive)
     * @param max The maximum range number to check against (inclusive)
     * @return Returns true if the numberToCheck is between min and max (both inclusive), false otherwise.
     */
    public static boolean validRange(int numberToCheck, int min, int max) {
        return ((numberToCheck >= min) && (numberToCheck <= max));
    }

    public static boolean validRange(float numbertoCheck, float min, float max, float delta) {
        return ((numbertoCheck >= (min-delta)) && (numbertoCheck <= (max+delta)));

    }

    public static String truncateString(String stringToTruncate, int length){
        if (stringToTruncate != null) {
            if (stringToTruncate.length() <= length) {
                return stringToTruncate;
            } else {
                return stringToTruncate.substring(0, length);
            }
        }
        else
            return null;
    }

    public static boolean validStringlength(String strToCheck, int maxLength){
        if (strToCheck != null ){
            return strToCheck.length() <= maxLength;
        }
        return false;
    }

    public static boolean isValidIndex(List list, int indexToCheck){
        return ((indexToCheck >= 0) && (indexToCheck < list.size()));
    }
}