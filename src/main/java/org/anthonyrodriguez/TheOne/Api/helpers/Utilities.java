package org.anthonyrodriguez.TheOne.Api.helpers;

import org.apache.http.NameValuePair;
import org.apache.http.util.Args;

import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.Arrays;

/**
 * Helper class for static methods.
 */
public class Utilities {
    public static final char QP_SEP_A = '&';

    public static <T> T[] concatWithArrayCopy(T[] array1, T[] array2) {
        T[] result = Arrays.copyOf(array1, array1.length + array2.length);
        System.arraycopy(array2, 0, result, array1.length, array2.length);
        return result;
    }

    public static String buildQueryString(
            final Iterable<? extends NameValueOperator> parameters,
            final char parameterSeparator,
            final Charset charset) throws Exception {
        Args.notNull(parameters, "Parameters");
        final StringBuilder result = new StringBuilder();
        for (final NameValueOperator parameter : parameters) {
            final String encodedName = parameter.toString();
            if (result.length() > 0) {
                result.append(parameterSeparator);
            }
            result.append(encodedName);
        }
        return result.toString();
    }
}
