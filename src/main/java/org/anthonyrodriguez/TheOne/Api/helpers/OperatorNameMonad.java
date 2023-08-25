package org.anthonyrodriguez.TheOne.Api.helpers;

import java.net.URLEncoder;
import java.nio.charset.Charset;

/***
 * Extension of OperatorNameValuePair that allows for only an
 * operator and name. This allows params that don't need a value
 * (i.e. check for the existence of a param: /movie?name or
 *  non-existence of a param: /movie?!name)
 */
public class OperatorNameMonad extends OperatorNameValuePair {

    private static final String EMPTY_VALUE = "";

    public OperatorNameMonad(String name, String operator) {
        super(name, EMPTY_VALUE, operator);
        this.operator = operator;
    }

    public String toEncodedString(Charset charset) {
        String name = getName();
        String operator = getOperator();
        final int len = name.length() + operator.length();
        final StringBuilder buffer = new StringBuilder(len);
        buffer.append(operator);
        buffer.append(URLEncoder.encode(name, charset));
        return buffer.toString();
    }

    @Override
    public String toString() {
        return getOperator() + getName();
    }


}
