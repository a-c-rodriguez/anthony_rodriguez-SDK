package org.anthonyrodriguez.TheOne.Api.helpers;

import java.util.Arrays;

/**
 * Helper class for static methods.
 */
public class Utilities {

    public static <T> T[] concatWithArrayCopy(T[] array1, T[] array2) {
        T[] result = Arrays.copyOf(array1, array1.length + array2.length);
        System.arraycopy(array2, 0, result, array1.length, array2.length);
        return result;
    }
}